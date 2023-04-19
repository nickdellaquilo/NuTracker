#------------------------------------------------------------------------
# How to run this from a Kotlin file:

#val interpreter = Python.getInstance().getInterpreter()
#val driCode = interpreter.getModule("DRI")
#val dri = driCode.callAttr("calculate_dri", age, gender, weight, height)

# Then access the values it returns:

#al calories = dri["calories"].asInt()
#val protein = dri["protein"].asInt()
#val fat = dri["fat"].asInt()
#val carbohydrates = dri["carbohydrates"].asInt()
#val calcium = dri["calcium"].asInt()
#val iron = dri["iron"].asInt()
#val vitaminA = dri["vitamin_a"].asInt()
#val vitaminC = dri["vitamin_c"].asInt()#
#------------------------------------------------------------------------

import chocopy

@chocopy.export
def calculate_dri(age, gender, weight, height):
    # Calculate the basal metabolic rate (BMR) using the Mifflin-St Jeor equation
    if gender.lower() == 'male':
        bmr = 10 * weight + 6.25 * height - 5 * age + 5
    elif gender.lower() == 'female':
        bmr = 10 * weight + 6.25 * height - 5 * age - 161
    else:
        raise ValueError("Invalid gender")

    # Calculate the total daily energy expenditure (TDEE) based on activity level
    activity_level = {
        'sedentary': 1.2,
        'lightly active': 1.375,
        'moderately active': 1.55,
        'very active': 1.725,
        'extra active': 1.9
    }
    tdee = bmr * activity_level['sedentary']

    # Calculate the recommended intake of macronutrients based on TDEE
    protein = weight  # Recommended protein intake is 1 gram per pound of body weight
    fat = tdee * 0.3 / 9  # Recommended fat intake is 30% of total calories, which is 9 calories per gram
    carbohydrates = (tdee - protein * 4 - fat * 9) / 4  # Recommended carbohydrates intake is the remainder of total calories

    # Calculate the recommended intake of micronutrients based on age and gender
    if age < 1:
        raise ValueError("Age must be at least 1")
    elif 1 <= age <= 3:
        calcium = 700
        iron = 7
        vitamin_a = 300
        vitamin_c = 15
    elif 4 <= age <= 8:
        calcium = 1000
        iron = 10
        vitamin_a = 400
        vitamin_c = 25
    elif 9 <= age <= 13:
        calcium = 1300
        iron = 8
        vitamin_a = 600
        vitamin_c = 45
    elif 14 <= age <= 18:
        if gender.lower() == 'male':
            calcium = 1300
            iron = 11
            vitamin_a = 900
            vitamin_c = 75
        elif gender.lower() == 'female':
            calcium = 1300
            iron = 15
            vitamin_a = 700
            vitamin_c = 65
    elif 19 <= age <= 50:
        if gender.lower() == 'male':
            calcium = 1000
            iron = 8
            vitamin_a = 900
            vitamin_c = 90
        elif gender.lower() == 'female':
            calcium = 1000
            iron = 18
            vitamin_a = 700
            vitamin_c = 75
    elif age >= 51:
        calcium = 1200
        iron = 8
        vitamin_a = 700
        vitamin_c = 75

    # Create a dictionary of the recommended nutrient intake values
    dri = {
        'calories': int(tdee),
        'protein': int(protein),
        'fat': int(fat),
        'carbohydrates': int(carbohydrates),
        'calcium': calcium,
        'iron': iron,
        'vitamin_a': vitamin_a,
        'vitamin_c': vitamin_c
    }

    return dri
