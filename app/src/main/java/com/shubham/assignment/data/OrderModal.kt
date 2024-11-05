package com.shubham.assignment.data

data class OrderModal(
    val language: List<String> = mutableListOf("Gujarati"),
    val interest_id: List<Int> = mutableListOf(17,18),
    val brand_id: Int = 9 ,
    val force_refresh: Boolean = true,
    val start_pos: Int = 0,
    val end_pos: Int = 1000
)