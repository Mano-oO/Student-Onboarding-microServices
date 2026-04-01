from flask import Flask, jsonify, render_template
import subprocess
import threading

app = Flask(__name__)

def start_watcher():
    subprocess.run(["python", "watcher.py"])

watcher_thread = threading.Thread(target=start_watcher, daemon=True)
watcher_thread.start()


@app.route("/")
def home():
    return render_template("index.html")

@app.route("/generate", methods=["POST"])
def generate():
    try:
        subprocess.run(["python", "generator.py"])
        return jsonify({"status": "CSV generated successfully"})
    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == "__main__":
    app.run(debug=True, use_reloader=False)