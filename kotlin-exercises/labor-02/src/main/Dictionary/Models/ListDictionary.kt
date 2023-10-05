package main.Dictionary.Models

import main.Dictionary.Interface.IDictionary
import main.Dictionary.Interface.IDictionary.Companion.DICTIONARY_FILE
import java.io.File

object ListDictionary : IDictionary {

    private val words: MutableList<String> = mutableListOf()

    init{

        try{
            File(DICTIONARY_FILE).forEachLine { line ->
                add(line)
            }
        } catch(e:Exception){
            println(e)
        }

    }

    override fun add(key: String): Boolean {
        return words.add(key)
    }

    override fun find(word: String): Boolean {
        return words.find { it == word } != null
    }

    override fun size(): Int {
        return words.count()
    }

    fun print(){
        words.forEach(){
            println(it)
        }
    }
}