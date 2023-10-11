package main.Extra

import java.util.*

class TextGenerator(private val inputText: String) {

    private val wordPairs: MutableList<Pair<String, String>> = mutableListOf()
    private val wordMap: MutableMap<Pair<String, String>, MutableList<String>> = mutableMapOf()

    init {
        learnWords()
    }

    private fun learnWords() {
        val words = inputText.split("\\s+".toRegex())
        if (words.size < 3) {
            throw IllegalArgumentException("Input text should contain at least three words.")
        }

        for (i in 0 until words.size - 2) {
            val prefix = Pair(words[i], words[i + 1])
            val postfix = words[i + 2]

            wordPairs.add(prefix)


            if (!wordMap.containsKey(prefix)) {
                wordMap[prefix] = mutableListOf()
            }
            wordMap[prefix]?.add(postfix)
        }
        println(wordMap)
    }

    fun generateText(): String {
        val random = Random()
        val result = mutableListOf<String>()

        // Start with the first two words of the input text
        result.add(wordPairs[0].first)
        result.add(wordPairs[0].second)

        var currentPrefix = wordPairs[0]

        while (wordMap.containsKey(currentPrefix)) {
            val possiblePostfixes = wordMap[currentPrefix]
            val randomIndex = random.nextInt(possiblePostfixes!!.size)
            val nextPostsix = possiblePostfixes[randomIndex]

            result.add(nextPostsix)

            if (result.size >= wordPairs.size) {
                break
            }

            currentPrefix = Pair(currentPrefix.second, nextPostsix)
        }

        return result.joinToString(" ")
    }
}

fun main() {
    val inputText = "Now is not the time for sleep, now is the time for party!"
    val generator = TextGenerator(inputText)
    println(generator.generateText())

}
