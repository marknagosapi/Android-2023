package main.Data

import java.time.LocalDate
import java.util.*

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth
)

fun Date.isLeapYear() = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
