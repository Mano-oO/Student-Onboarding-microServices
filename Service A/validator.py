import csv
import re
from datetime import datetime
import os
import sys
import subprocess  

if len(sys.argv) > 1:
    INPUT_FILE = sys.argv[1]
else:
    print("No input file provided!")
    exit()

filename = os.path.basename(INPUT_FILE).replace(".csv", "")


VALID_FILE = f"Final/{filename}_valid.csv"
INVALID_FILE = f"Final/{filename}_invalid.csv"


os.makedirs("Final", exist_ok=True)


EMAIL_REGEX = r'^[\w\.-]+@[\w\.-]+\.\w+$'
PHONE_REGEX = r'^\d{10}$'

VALID_GENDERS = ["Male", "Female", "Other"]
VALID_BLOOD = ["A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"]

def is_valid_date(dob):
    try:
        dob_obj = datetime.strptime(dob, "%Y-%m-%d")
        return dob_obj <= datetime.now()
    except:
        return False


def validate_row(row):
    errors = []

    if not row["first_name"] or str(row["first_name"]).strip() == "":
        errors.append("Invalid first_name")

    if not re.match(EMAIL_REGEX, str(row["email"])):
        errors.append("Invalid email")

    if not re.match(PHONE_REGEX, str(row["phone_number"])):
        errors.append("Invalid phone")

    if not is_valid_date(str(row["date_of_birth"])):
        errors.append("Invalid DOB")

    if row["gender"] not in VALID_GENDERS:
        errors.append("Invalid gender")

    if row["blood_group"] not in VALID_BLOOD:
        errors.append("Invalid blood group")

    try:
        cgpa = float(row["cgpa"])
        if not (5.0 <= cgpa <= 10.0):
            errors.append("Invalid CGPA")
    except:
        errors.append("Invalid CGPA")

    return errors


def run_validation():
    if not os.path.exists(INPUT_FILE):
        print("Input file not found!")
        return

    valid_count = 0
    invalid_count = 0

    with open(INPUT_FILE, mode="r", encoding="utf-8") as inputfile, \
         open(VALID_FILE, mode="w", newline="", encoding="utf-8") as validf, \
         open(INVALID_FILE, mode="w", newline="", encoding="utf-8") as invalidf:

        
        reader = csv.DictReader(inputfile)
        valid_writer = None
        invalid_writer = None
        for row in reader:
            errors = validate_row(row)
            if not errors:
                if valid_writer is None:
                    valid_writer = csv.DictWriter(validf, fieldnames=row.keys())
                    valid_writer.writeheader()

                valid_writer.writerow(row)
                valid_count += 1

            else:
                row["errors"] = ", ".join(errors)

                if invalid_writer is None:
                    invalid_writer = csv.DictWriter(invalidf, fieldnames=row.keys())
                    invalid_writer.writeheader()

                invalid_writer.writerow(row)
                invalid_count += 1

    print("Validation Complete ")
    print(f"Valid records: {valid_count}")
    print(f"Invalid records: {invalid_count}")
    if valid_count > 0:
        print("Sending valid data to Service B...")
        subprocess.run(["python", "sender.py", VALID_FILE])


if __name__ == "__main__":
    run_validation()