# Define the user's age, sex, and nutrient intake data
age = 30
sex = 'Male'
weight = 70
height = 170
activity_level = 'Sedentary'
nutrient_data = {
    'Protein': 50, # g/day
    'Carbohydrates': 200, # g/day
    'Fat': 70, # g/day
    'Fiber': 25, # g/day
    'Vitamin C': 90, # mg/day
    'Calcium': 1000, # mg/day
    'Iron': 8, # mg/day
    'Sodium': 2300 # mg/day
}

# Define the DRI values based on NAM guidelines
dri = {
    'Protein': [0.8, 1.2], # g/kg/day for adult males and females, respectively
    'Carbohydrates': [130, None], # g/day for both males and females
    'Fat': [20, 35], # % of total daily energy intake for adult males and females, respectively
    'Fiber': [38, 25], # g/day for adult males and females, respectively
    'Vitamin C': [90, 75], # mg/day for adult males and females, respectively
    'Calcium': [1000, 1000], # mg/day for both males and females
    'Iron': [8, 18], # mg/day for adult males and females aged 19-50, respectively
    'Sodium': [2300, 2300] # mg/day for both males and females
}

# Calculate the DRI values for the user's age, sex, and activity level
if sex == 'Male':
    protein_dri = dri['Protein'][0] * weight
    fat_dri = dri['Fat'][0] / 100 * 2000 # assume 2000 kcal/day for sedentary males
    iron_dri = dri['Iron'][0]
else:
    protein_dri = dri['Protein'][1] * weight
    fat_dri = dri['Fat'][1] / 100 * 2000 # assume 2000 kcal/day for sedentary females
    iron_dri = dri['Iron'][1]
carb_dri = dri['Carbohydrates'][0]
fiber_dri = dri['Fiber'][0]
vitc_dri = dri['Vitamin C'][0]
calcium_dri = dri['Calcium'][0]
sodium_dri = dri['Sodium'][0]

# Print the user's nutrient intake data and DRI values

#print(f"Age: {age}")
#print(f"Sex: {sex}")
#print(f"Weight: {weight}")
#print(f"Height: {height}")
#print(f"Activity level: {activity_level}")
#print("Nutrient intake data:")
#for nutrient, value in nutrient_data.items():
#    print(f"{nutrient}: {value} g/day")
#print("Dietary Reference Intake (DRI) values:")
#print(f"Protein: {protein_dri:.2f} g/day")
#print(f"Carbohydrates: {carb_dri:.2f} g/day")
#print(f"Fat: {fat_dri:.2f} g/day")
#print(f"Fiber: {fiber_dri:.2f} g/day")
#print(f"Vitamin C: {vitc_d
