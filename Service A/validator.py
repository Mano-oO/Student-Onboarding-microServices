import pandas as pd
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

EmailF = r'^[\w\.-]+@[\w\.-]+\.\w+$'
PhoneF= r'^\d{10}$'
VALID_GENDERS = ["Male", "Female", "Other"]
VALID_BLOOD = ["A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"]

def validateq():
    if not os.path.exists(INPUT_FILE):
        print("Input file not found!")
        return

    df = pd.read_csv(INPUT_FILE)
    df["errors"] = ""
    df.loc[df["first_name"].isna() | (df["first_name"].astype(str).str.strip() == ""), "errors"] += "Invalid first_name, "
    df.loc[~df["email"].astype(str).str.match(EmailF), "errors"] += "Invalid email, "
    df.loc[~df["phone_number"].astype(str).str.match(PhoneF), "errors"] += "Invalid phone, "
    def check_dob(d):
        try:
            return pd.to_datetime(d) <= pd.Timestamp.now()
        except:
            return False
    df.loc[~df["date_of_birth"].apply(check_dob), "errors"] += "Invalid DOB, "
    df.loc[~df["gender"].isin(VALID_GENDERS), "errors"] += "Invalid gender, "
    df.loc[~df["blood_group"].isin(VALID_BLOOD), "errors"] += "Invalid blood group, "
    def check_cgpa(x):
        try:
            return 5.0 <= float(x) <= 10.0
        except:
            return False

    df.loc[~df["cgpa"].apply(check_cgpa), "errors"] += "Invalid CGPA, "
    df["errors"] = df["errors"].str.rstrip(", ")

    valid_df = df[df["errors"] == ""]
    invalid_df = df[df["errors"] != ""]

    valid_df.drop(columns=["errors"]).to_csv(VALID_FILE, index=False)
    invalid_df.to_csv(INVALID_FILE, index=False)

    print("Validation Complete ")
    print(f"Valid records: {len(valid_df)}")
    print(f"Invalid records: {len(invalid_df)}")
    
    if len(valid_df) > 0:
        print("Sending valid data to Service B...")
        subprocess.run(["python", "Sender.py", VALID_FILE])

if __name__ == "__main__":
    validateq()