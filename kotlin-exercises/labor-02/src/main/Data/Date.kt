package main.Data
import java.time.LocalDate

data class Date (


    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth

) : Comparable<Date>, Comparator<Date> {
    override fun compareTo(other: Date): Int {
        return this.year.compareTo(other.year)
    }

    override fun compare(o1: Date?, o2: Date?): Int {

        if(o1 is Date && o2 is Date){
            return o1.month.compareTo(o2.month)
        }
        return 0;
    }
}

fun Date.isLeapYear() = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
fun Date.isValidDate() = day in 1..31 && month in 1..12 && year > 0

