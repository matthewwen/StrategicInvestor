from flask import Flask, request, jsonify
import traceback

app = Flask(__name__)



@app.route("/queries", methods=['GET', 'POST'])
def run():
    return

if __name__ == "__main__":
    # app.run(debug=True, threaded=True)
    app.run(host='0.0.0.0', debug=True, threaded=True)
