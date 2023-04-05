import requests

# Define the API endpoint and parameters
api_endpoint = 'https://api.spoonacular.com/recipes/search'
api_key = 'YOUR_API_KEY_HERE' # replace with your own API key
query = 'chicken' # replace with your desired search query

# Define the API parameters
params = {
    'apiKey': api_key,
    'query': query
}

# Send the API request and get the response
response = requests.get(api_endpoint, params=params)

# Extract the recipe information from the response
if response.status_code == 200:
    recipe_data = response.json()
    for recipe in recipe_data['results']:
        print(f"Recipe: {recipe['title']}")
        print(f"Servings: {recipe['servings']}")
        print(f"Preparation time: {recipe['readyInMinutes']} minutes")
        print(f"Link: {recipe['sourceUrl']}")
        print()
else:
    print(f"Error: {response.status_code} - {response.text}")
