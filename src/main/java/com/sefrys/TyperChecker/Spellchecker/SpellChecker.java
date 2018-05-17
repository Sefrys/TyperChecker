package com.sefrys.TyperChecker.Spellchecker;

import com.sefrys.DataStructure.Hash.HashCollection;
import com.sefrys.DataStructure.Hash.HashTable;

import java.util.List;
import java.util.Set;

/**
 * Created by Ireneusz Zagan on 17.05.18, 21:34
 * Contact: sefrys@gmail.com
 */
public class SpellChecker extends HashTable implements SpellingChecker {

    private HashCollection dictionary;

    public SpellChecker(HashTable map) {
        dictionary = map;
    }

    @Override
    public Set<String> gatherSuggestions(String value) {
        return null;
    }

    @Override
    public List<String> adjacentLetterSwap(String value) {
        return null;
    }

    @Override
    public List<String> adjacentLetterInsert(String value) {
        return null;
    }

    @Override
    public List<String> letterDeletion(String value) {
        return null;
    }

    @Override
    public List<String> letterReplacement(String value) {
        return null;
    }

    @Override
    public List<String> wordSplitter(String value) {
        return null;
    }

    private String swapAdjacent(String value, int position) {
        value = value.toLowerCase();
        char[] chars = value.toCharArray();

        char tmp = chars[position];
        chars[position] = chars[position + 1];
        chars[position + 1] = tmp;

        return new String(chars);

    }
}
