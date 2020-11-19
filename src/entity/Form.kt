package com.github.wnuk.entity

import java.util.*

data class Form(
    val id: Long,
    val who: String,
    val desc: String,
    val data: Date
)