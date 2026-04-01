import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import subprocess
import os
from concurrent.futures import ThreadPoolExecutor

WATCH_FOLDER = "Data"

executor = ThreadPoolExecutor(max_workers=3)


processed_files = set()

class WatcherHandler(FileSystemEventHandler):
    def on_created(self, event):
        if event.is_directory:
            return

        if event.src_path.endswith(".csv"):

            if event.src_path in processed_files:
                return

            print(f"New CSV detected: {event.src_path}")

            time.sleep(2)

    
            if not os.path.exists(event.src_path):
                return

            
            previous_size = -1
            while True:
                try:
                    current_size = os.path.getsize(event.src_path)
                except:
                    return  

                if current_size == previous_size:
                    break

                previous_size = current_size
                time.sleep(1)

            processed_files.add(event.src_path)

            print(f"Submitting for validation: {event.src_path}")

            executor.submit(run_validator, event.src_path)


def run_validator(file_path):
    print(f"Processing: {file_path}")
    subprocess.run(["python", "validator.py", file_path])


if __name__ == "__main__":
    print("Watching folder for new CSV files...")

    event_handler = WatcherHandler()
    observer = Observer()
    observer.schedule(event_handler, WATCH_FOLDER, recursive=False)
    observer.start()

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        observer.stop()

    observer.join()