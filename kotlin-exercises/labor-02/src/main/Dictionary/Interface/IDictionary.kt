package main.Dictionary.Interface

interface IDictionary {

    companion object{
        const val DICTIONARY_FILE ="dictionary.txt"
    }

    fun add(key: String) : Boolean
    fun find(word: String): Boolean
    fun size(): Int

}