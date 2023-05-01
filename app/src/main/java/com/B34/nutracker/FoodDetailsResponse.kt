package com.B34.nutracker

import com.google.gson.annotations.SerializedName

data class FoodDetailsResponse(
    @SerializedName("fdcId") val fdcId: Int,
    @SerializedName("description") val description: String,
    @SerializedName("foodNutrients") val foodNutrients: List<FoodNutrient>,
) {
    data class FoodNutrient(
        @SerializedName("nutrientId") val nutrientId: Int,
        @SerializedName("nutrientName") val nutrientName: String,
        @SerializedName("unitName") val unitName: String,
        @SerializedName("value") val value: Double
    )
}
