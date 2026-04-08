import requests
import csv
import sys
import os

API_URL = "http://service-b:8080/students"
BATCH_SIZE = 100

def send_data(file_path):
    if not os.path.exists(file_path):
        print("File not found!")
        return

    batch = []

    with open(file_path, mode="r", encoding="utf-8") as file:
        
        reader = csv.reader(file)
        headers = next(reader) 

        for row in reader:
            
            json_obj = {headers[i]: row[i] for i in range(len(headers))}
            batch.append(json_obj)

            if len(batch) == BATCH_SIZE:
                send_batch(batch)
                batch = []

       
        if batch:
            send_batch(batch)

    print("✅ All data sent in batches")


def send_batch(batch):
    try:
        response = requests.post(API_URL, json=batch)
        if response.status_code == 200:
            print(f"Batch of {len(batch)} sent successfully")
        else:
            print("Failed:", response.status_code, response.text)

    except Exception as e:
        print("Error:", str(e))


if __name__ == "__main__":
    if len(sys.argv) > 1:
        send_data(sys.argv[1])
    else:
        print("No file provided!")