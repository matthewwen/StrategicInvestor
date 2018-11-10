from flask import Flask, request, jsonify
import traceback

app = Flask(__name__)


# TODO: waht does my end point look like?
@app.route("/test", methods=['GET', 'POST'])
def test():
    try:
        data = request.get_json
        response = {"message": "testing java-python connection"}
        return jsonify(response)
    except:
        return jsonify(error=404, exception=traceback.format_exc())


@app.route("/risk_arbitrage", methods=['GET', 'POST'])
def run():
    try:
        data = request.get_json
        acquirer, acquiree = data["acquirer"], data["acquiree"]

        response = {"message": "testing java-python connection"}
        return jsonify(response)
    except:
        return jsonify(error=404, exception=traceback.format_exc())


if __name__ == "__main__":
    # app.run(debug=True, threaded=True)
    app.run(host='0.0.0.0', debug=True, threaded=True)
