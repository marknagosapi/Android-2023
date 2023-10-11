package main

import main.Dictionary.Interface.IDictionary
import main.Dictionary.Models.DictionaryType
import main.Dictionary.Models.ListDictionary
import main.Dictionary.Providers.DictionaryProvider

fun main(args: Array<String>) {

    val dict = DictionaryProvider.createDictionary(DictionaryType.TREE_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while (true) {
        print("What to find? ")
        word = readLine()
        if (word.equals("quit")) {
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}