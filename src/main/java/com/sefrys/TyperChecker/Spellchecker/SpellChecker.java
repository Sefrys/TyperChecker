package com.sefrys.TyperChecker.Spellchecker;

import com.sefrys.DataStructure.Hash.HashCollection;
import com.sefrys.DataStructure.Hash.HashTable;

import java.util.ArrayList;
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

    /**
     * Swaps letters around in the word.
     * For each swapped word, if such word exists in the dictionary,
     * add the word to list of suggestions
     * @param value - word to swap letters around in
     * @return - list of suggested similar words.
     */
    @Override
    public List<String> adjacentLetterSwap(String value) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < value.length() - 1; i++) {
            String swapped = swapAdjacent(value, i);
            if (dictionary.lookup(swapped)) suggestions.add(swapped);
        }
        return suggestions;
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

    /**
     * Swaps adjacent letters in a word.
     * @param value - word to swap letters around in
     * @param position - position at which the swap occurs
     * @return - letter swapped String
     */
    private String swapAdjacent(String value, int position) {
        value = value.toLowerCase();
        char[] chars = value.toCharArray();

        char tmp = chars[position];
        chars[position] = chars[position + 1];
        chars[position + 1] = tmp;

        return new String(chars);

    }

    /**
     * Inserts a character into position in list of characters
     * @param characters - ArrayList of characters
     * @param position - index of the position the character is inserted at
     * @param character - character inserted at position
     * @return - String word with inserted character
     */
    private String insertAdjacent(ArrayList<Character> characters, int position, char character) {
        characters.add(position, character);
        return rebuildWord(characters);
    }

    /**
     * Splits the string into individual characters
     * @param value - word to split
     * @return ArrayList of characters
     */
    private ArrayList<Character> split(String value) {
        value = value.toLowerCase();
        ArrayList<Character> characters = new ArrayList<>();

        for (int i = 0; i < value.length(); i++) {
            characters.add(value.charAt(i));
        }
        return characters;
    }

    /**
     * Concatenates list of characters into a string
     * @param characters - list of characters
     * @return concatenated String
     */
    private String rebuildWord(ArrayList<Character> characters) {
        StringBuilder sb = new StringBuilder();
        for (Character s : characters) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
}
