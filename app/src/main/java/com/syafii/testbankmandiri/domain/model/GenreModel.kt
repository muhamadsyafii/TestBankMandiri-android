package com.syafii.testbankmandiri.domain.model
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

data class GenreModel(
    val genre : List<GenreItemModel> = listOf()
){
    data class GenreItemModel(
        val id: String = "",
        val name: String = ""
    )
}