package com.B34.nutracker

data class FoodResponse(val foods: List<FoodItem>) {
    data class FoodItem(val fdcId: Int, val description: String)
}