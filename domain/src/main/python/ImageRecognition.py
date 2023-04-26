# Kotlin code example
# Import the Python module
# val interpreter = Python.getInstance().getInterpreter()
# val nutritionModule = interpreter.getModule("nutrition")
# 
# Capture or select an image
# val imageFile = captureOrSelectImage()
# 
# Convert the image to base64-encoded string
# val imageBytes = imageFile.readBytes()
# val encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP)
# 
# Call the search_food() function
# val foodName = nutritionModule.callAttr("search_food", encodedImage).toString()
# 
# Use the foodName value as needed

import chocopy
import requests
import base64

@chocopy.export
def search_food(image):
    # Encode the image file as base64
    with open(image, 'rb') as f:
        image_data = f.read()
    encoded_image = base64.b64encode(image_data).decode('utf-8')

    # Make a POST request to the Spoonacular API to search for food items based on the image
    url = "https://api.spoonacular.com/food/images/classify"
    payload = {"base64": encoded_image}
    params = {
        "apiKey": "3660f7fa5b3f4f5bbe7422534fcc3613", # Note: in a viable commercial version of this app, prompting the user to generate their own API key may be neccesary.
        "threshold": 0.8
    }
    response = requests.post(url, params=params, json=payload)

    # Parse the JSON response to get the top food item
    response_json = response.json()
    if response_json['status'] == 'failure':
        raise ValueError("Could not classify image")
    top_food_item = response_json['matches'][0]['name']

    return top_food_item
