import requests
import base64

# Define the API endpoint and parameters
api_endpoint = 'https://api.spoonacular.com/food/images/classify'
api_key = 'YOUR_API_KEY_HERE' # replace with your own API key

# Load the image file and encode it as base64
with open('food_image.jpg', 'rb') as f:
    image_data = f.read()
image_base64 = base64.b64encode(image_data).decode('utf-8')

# Define the API parameters
params = {
    'apiKey': api_key,
    'image': image_base64
}

# Send the API request and get the response
response = requests.post(api_endpoint, params=params)

# Extract the food item information from the response
if response.status_code == 200:
    food_data = response.json()
    if len(food_data['annotations']) > 0:
        food_item = food_data['annotations'][0]['annotation']
        print(f"Food item identified: {food_item}")
    else:
        print("No food item identified.")
else:
    print(f"Error: {response.status_code} - {response.text}")
