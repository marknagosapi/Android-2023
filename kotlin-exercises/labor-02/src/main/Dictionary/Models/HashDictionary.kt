package main.Dictionary.Models

import main.Dictionary.Interface.IDictionary
import java.io.File

object HashDictionary : IDictionary {

    private val words: HashSet<String> = hashSetOf()

    init{

        try{
            File(IDictionary.DICTIONARY_FILE).forEachLine { line ->
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
        return words.contains(word)
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