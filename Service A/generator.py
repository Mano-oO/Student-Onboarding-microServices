from faker import Faker
import csv
import random
from datetime import datetime, timedelta
import os

fake = Faker()
Dept = ["AI", "Cloud", "IT", "Marketing", "QA", "HR"]
genders = ["Male", "Female"]
blood_groups = ["A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"]

data = []

for i in range(100):
    first_name = fake.first_name()
    last_name = fake.last_name()
    dob = fake.date_of_birth(minimum_age=18, maximum_age=60)
    dob_str = dob.strftime("%d")

    email = f"{first_name.lower()}.{last_name.lower()}{dob_str}@gmail.com"
    phone = fake.msisdn()[0:10]
    gender = random.choice(genders)
    course = random.choice(Dept)
    blood = random.choice(blood_groups)
    cgpa = round(random.uniform(5.0, 10.0), 2)

    
    choice = random.choice(["valid", "valid", "invalid"])

    if choice == "invalid":
        case_type = random.choice([
            "bad_email",
            "bad_email2",
            "empty_name",
            "future_dob",
            "invalid_phone",
            "low_cgpa",
            "high_cgpa",
            "missing_field"
        ])

        if case_type == "bad_email":
            email = first_name + random.choice(["gmail.com", "yahoo", "mail"])

        elif case_type == "bad_email2":
            email = last_name + "@gmail" + random.choice(["", ".", "comcom"])

        elif case_type == "empty_name":
            first_name = random.choice(["", " ", None])

        elif case_type == "future_dob":
            future_days = random.randint(100, 2000)
            dob = datetime.now() + timedelta(days=future_days)

        elif case_type == "invalid_phone":
            phone = "".join([str(random.randint(0, 9)) for _ in range(random.randint(3, 8))])

        elif case_type == "low_cgpa":
            cgpa = round(random.uniform(0.0, 4.9), 2)

        elif case_type == "high_cgpa":
            cgpa = round(random.uniform(10.1, 15.0), 2)

        elif case_type == "missing_field":
            blood = random.choice(["", None])

   
    if isinstance(dob, datetime):
        dob = dob.strftime("%Y-%m-%d")

    record = {
        "first_name": first_name,
        "last_name": last_name,
        "date_of_birth": dob,
        "gender": gender,
        "phone_number": phone,
        "email": email,
        "course_name": course,
        "blood_group": blood,
        "cgpa": cgpa
    }

    data.append(record)


os.makedirs("Data", exist_ok=True)



i = 1
while os.path.exists(f"Data/CSV_file{i}.csv"):
    i += 1

fpath = f"Data/CSV_file{i}.csv"

with open(fpath, mode="w", newline="", encoding="utf-8") as file:
    writer = csv.DictWriter(
        file,
        fieldnames=[
            "first_name", "last_name", "date_of_birth", "gender",
            "phone_number", "email", "course_name", "blood_group", "cgpa"
        ]
    )
    writer.writeheader()
    writer.writerows(data)

print("CSV file with 100 records created successfully!")