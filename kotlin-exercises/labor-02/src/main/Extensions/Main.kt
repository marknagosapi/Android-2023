package main.Extensions

fun main(args: Array<String>) {

    val name = "Nago Mark"
    println(name.monogram())
    val words = listOf("apple","pear","plum","strawberry")
    println(words.joinBySeparator("#"))

    println(words.longestWord())


}

fun String.monogram() = this.split(" ").map { word -> word.first() }.joinToString("")
fun List<String>.joinBySeparator(separator: String) = this.joinToString(separator)
fun List<String>.longestWord() = this.maxBy { it.length }
