package com.example.trucklist.model

data class Vehicle(
    val plate: String,
    val mileage: Double,
    val tires: List<Tires>)