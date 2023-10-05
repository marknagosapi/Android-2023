package main.Dictionary.Providers

import com.sun.source.tree.Tree
import main.Dictionary.Interface.IDictionary
import main.Dictionary.Models.DictionaryType
import main.Dictionary.Models.HashDictionary
import main.Dictionary.Models.ListDictionary
import main.Dictionary.Models.TreeDictionary

class DictionaryProvider {

    companion object{

        fun createDictionary(type: DictionaryType): IDictionary {
            return when(type){
                DictionaryType.LIST -> ListDictionary
                DictionaryType.HASH_SET -> HashDictionary
                DictionaryType.TREE_SET -> TreeDictionary
            }
        }
    }

}