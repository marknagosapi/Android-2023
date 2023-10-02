package test

import org.junit.Test
import java.util.*
import kotlin.collections.HashMap
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// Function that return a List Of Anagrams grouped together as a List.
fun groupAnagrams(wordList: Array<String>): List<List<String>> {

    val resultMap = HashMap<String, MutableList<String>>()

    for(str in wordList){

        //lowercase the str
        val lowerCaseStr = str.lowercase()
        val characterList = lowerCaseStr.toList()

        val sortedCharacterList = characterList.sorted()
        val sortedWord = sortedCharacterList.joinToString("")

        if(!resultMap.containsKey(sortedWord)){

            resultMap[sortedWord] = mutableListOf()
        }
        if(resultMap[sortedWord] != null){
            resultMap[sortedWord]!!.add(lowerCaseStr)
        }
    }
    return resultMap.values.toList()
}

class AnagramsGrouperTest {
    @Test
    fun threeGroupsAllLowerCase() {
        val anagrams = groupAnagrams(listOf("eat", "tea", "tan", "ate", "nat",
            "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }
    @Test
    fun threeGroupsSomeUpperCase() {
        val anagrams = groupAnagrams(listOf("eat", "tEa", "Tan", "atE", "NAT",
            "bat").toTypedArray())
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }
    @Test
    fun validOneGroup() {
        val anagrams = groupAnagrams(listOf("eat").toTypedArray())
        assertEquals(1, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat")))
    }
    @Test
    fun noGroup() {
        val anagrams = groupAnagrams(emptyList<String>().toTypedArray())
        assertEquals(0, anagrams.size)
    }
}