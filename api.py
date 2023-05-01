# -*- coding: utf-8 -*-
"""
@author: Olivia Carr
"""
import requests
from flask import Flask

apikey = "mfyhLSmEtoXiBT4LN0dV2cI9et5G3Oc7hsDWL9dE"
search = ""

app = Flask(__name__)

@app.route('/foods/search', methods=['GET'])
def api1(search):
    url = "https://api.nal.usda.gov/fdc/v1/search?api_key=" + apikey + "&query=" + search
    fdcid = []
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        data = data["foods"]
        fdcid = data[0]["fdcId"]
    return fdcid

@app.route('/food/{fdcId}', methods=['GET'])
def api2(fdcid):
    url = "https://api.nal.usda.gov/fdc/v1/" + str(fdcid) + "?api_key=" + apikey
    nutritionaldata = []
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        nutritionaldata = data["foodNutrients"]
    return nutritionaldata

answer1 = api1("apple")
answer2 = api2(answer1)
