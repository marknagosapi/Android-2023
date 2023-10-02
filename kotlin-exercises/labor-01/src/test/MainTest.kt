package test

import java.util.*

fun main(){
    exercise8()
}

fun workWithLists(){
    println("Exercise 2. - Work with lists")
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    for(day in daysOfWeek){
        println(day)
    }

    //Use the list filter to print the days starting with the letter T
    val daysStartingWithT = daysOfWeek.filter { it.startsWith("T") }
    print(daysStartingWithT)
    //Use a list filter to print the days containing the letter ‘e’
    val daysContainingE = daysOfWeek.filter { it.contains("e") }
    print(daysContainingE)
    //Use a list filter to print all the days of length 6 (e.g. Friday)
    val daysOfLength6 = daysOfWeek.filter { it.length == 6 }
    print(daysOfLength6)
}

// print the addition of two numbers
fun  sumOfTwoNumbers( number1: Int, number2: Int){
    println("Exercise 1. - Sum of two numbers")
    println("$number1 + $number2 = ${number1 + number2}")
}

fun exercise3(){
    println("Exercise 3. - Prime numbers")
    for (i in 2..100) {
        if (isPrime(i)) {
            println(i)
        }
    }
}

fun exercise4(){

    println("Exercise 4. - Message coding")

    val encodedMessage = messageCoding("Hello World=", ::encode)
    val decodedMessage = messageCoding(encodedMessage, ::decode)

    println("The encoded message is: $encodedMessage")
    println("The decoded message is: $decodedMessage")

    println("Enode bGFza2FsZXZlcyDinKg = ${messageCoding("bGFza2FsZXZlcyDinKg=", ::decode)}")


}

fun exercise5(){

    println("Exercise 5. - Even numbers")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val evenNumbers = evenNumbers(numbers)
    println(evenNumbers)

}

fun exercise6(){

    println("Exercise 6. - Map Function")
    val listOfNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val doubledList = listOfNumbers.map { it * 2 }
    println("The Doubled List is: $doubledList")
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    val capitalizedWeek = daysOfWeek.map{ it.uppercase() }
    println("The Capitalized Week is : $capitalizedWeek")
    val firstCharacterOfEachDay = daysOfWeek.map{ it.first() }
    println("The First Character of Each Day is : $firstCharacterOfEachDay")
    val lengthOfDays = daysOfWeek.map{ it.length }
    println("The Length of Days is : $lengthOfDays (not actual :p every day is 24h)")
    val avaregeLengthOfDays = daysOfWeek.map{it.length}.average()
    println("The Average Length of Days is : $avaregeLengthOfDays")

}

fun exercise7(){

    println("Exercise 7. - Mutable List Functions")
    val daysOfWeek = mutableListOf<String>()
    daysOfWeek.add("Monday")
    daysOfWeek.add("Tuesday")
    daysOfWeek.add("Wednesday")
    daysOfWeek.add("Thursday")
    daysOfWeek.add("Friday")
    daysOfWeek.add("Saturday")
    daysOfWeek.add("Sunday")

    daysOfWeek.removeIf { it.contains('n') }

    println(daysOfWeek)

    //print each element of the array with index
    daysOfWeek.forEachIndexed { index, day ->
        println("Day $index is $day")
    }

    daysOfWeek.sort()

    println(daysOfWeek)

}

fun exercise8(){
    println("Exercise 8. - Array Functianlities")
    val size = 10

    val random = Random(System.currentTimeMillis())

    // Create an array of integers and fill it with random numbers
    val randomIntArray = IntArray(size) { random.nextInt(1, 101) } // Ge
    for (i in randomIntArray){
        println(i)
    }

    randomIntArray.sort()
    println("Sorted Ascending Order Array:")
    for (i in randomIntArray){
        println(i)
    }

    val isEven = randomIntArray.any { it % 2 == 0 }
    if(isEven){
        println("The Array Contains Even Numbers!")
    }

    val isAllEven = randomIntArray.all { it % 2 == 0 }
    if(isAllEven){
        println("The Array Contains All Even Numbers!")
    }

    var sum = 0.0
    randomIntArray.forEach {
            sum += it
    }

    if(randomIntArray.isNotEmpty()){
        val average = sum/randomIntArray.size
        println("The Average Of The Array is: $average")
    } else {
        println("The Array Is Empty!")
    }
}


fun evenNumbers(list: List<Int>): List<Int>  = list.filter{ it % 2 == 0}

fun messageCoding(msg:String, func: (String) -> String) = func(msg)

fun encode(input: String): String {
    return Base64.getEncoder().encodeToString(input.toByteArray())
}

fun decode(input: String): String {
    return String(Base64.getDecoder().decode(input))
}

fun isPrime(number: Int): Boolean {
    for (i in 2..number / 2) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}