from flask import Flask, jsonify, render_template, request
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
    data = request.get_json()
    rows = data.get("rows", 100)

    subprocess.run(["python", "generator.py", str(rows)])

    return jsonify({"status": f"CSV with {rows} rows generated successfully!"})

'''@app.route("/generate", methods=["POST"])
def generate():
    try:
        subprocess.run(["python", "generator.py"])
        return jsonify({"status": "CSV generated successfully"})
    except Exception as e:
        return jsonify({"error": str(e)}), 500'''


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True, use_reloader=False)