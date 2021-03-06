package com.sefrys.TyperChecker.Spellchecker;

import com.sefrys.DataStructure.Hash.HashCollection;
import com.sefrys.DataStructure.Hash.HashTable;

import java.util.*;

/**
 * Created by Ireneusz Zagan on 17.05.18, 21:34
 * Contact: sefrys@gmail.com
 */
public class SpellChecker extends HashTable implements SpellingChecker {

    private HashCollection dictionary;

    public SpellChecker(HashTable map) {
        dictionary = map;
    }

    /**
     * Runs every spellchecker algorithm to find and
     * combine a list of unique suggestions
     * @param value - word to be checked
     * @return - Set of unique suggestions
     */
    @Override
    public Set<String> gatherSuggestions(String value) {
        Set<String> suggestions = new HashSet<>() ;
        suggestions.addAll(adjacentLetterSwap(value));
        suggestions.addAll(adjacentLetterInsert(value));
        suggestions.addAll(letterDeletion(value));
        suggestions.addAll(letterReplacement(value));
        suggestions.addAll(wordSplitter(value));

        return suggestions;
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

    /**
     * Inserts a character once at every position in the word, and
     * for each modified word, checks if such mutated word exists in the dictionary.
     * If it exists, add word to list of suggestions.
     * @param value - word to insert letters in
     * @return - list of suggested similar words.
     */
    @Override
    public List<String> adjacentLetterInsert(String value) {
        List<String> suggestions = new ArrayList<>();
        ArrayList<Character> characters = split(value);

        for (char character = 97; character < 123; character++) {
            for (int i = 0; i != value.length() + 1; i++) {
                ArrayList<Character> tempCharacters = new ArrayList<>(characters);
                String inserted = insertAdjacent(tempCharacters, i, character);
                if (dictionary.lookup(inserted)) suggestions.add(inserted);
            }
        }
        return suggestions;
    }

    /**
     * Deletes a character once at every position in the word, and
     * for each modified word, checks if such mutated word exists in the dictionary.
     * If it exists, add word to list of suggestions.
     * @param value - word to delete letters in.
     * @return - list of suggested similar words.
     */
    @Override
    public List<String> letterDeletion(String value) {
        List<String> suggestions = new ArrayList<>();
        ArrayList<Character> characters = split(value);

        for (int i = 0; i != value.length(); i++) {
            ArrayList<Character> tempCharacters = new ArrayList<>(characters);
            String deleted = deleteLetter(tempCharacters, i);
            if (dictionary.lookup(deleted)) suggestions.add(deleted);
        }
        return suggestions;
    }

    /**
     * Replaces a letter in the word at each position, and
     * for each modified word, checks if such mutated word exists in the dictionary.
     * If it exists, add word to list of suggestions.
     * @param value - word to replace letters in.
     * @return - list of suggested similar words.
     */
    @Override
    public List<String> letterReplacement(String value) {
        List<String> suggestions = new ArrayList<>();
        ArrayList<Character> characters = split(value);

        for (char character = 97; character < 123; character++) {
            for (int i = 0; i != value.length(); i++) {
                ArrayList<Character> tempCharacters = new ArrayList<>(characters);
                String replaced = replaceLetter(tempCharacters, i, character);
                if (dictionary.lookup(replaced)) suggestions.add(replaced);
            }
        }
        return suggestions;
    }

    /**
     * Splits the word at a certain position into a pair.
     * If both of resulting words exist in the dictionary,
     * add the words to list of suggestions
     * @param value - word to split up
     * @return - list of suggestions
     */
    @Override
    public List<String> wordSplitter(String value) {
        List<String> suggestions = new ArrayList<>();
        ArrayList<Character> characters = split(value);

        for (int i = 0; i != value.length(); i++) {
            ArrayList<Character> tempCharacters = new ArrayList<>(characters);
            String[] spaced = spaceSplit(tempCharacters, i);
            if (dictionary.lookup(spaced[0]) && dictionary.lookup(spaced[1])) {
                suggestions.addAll(Arrays.asList(spaced));
            }
        }
        return suggestions;
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
     * Deletes a letter at a position in the word
     * @param characters - ArrayList of characters
     * @param position - index of the character to be deleted
     * @return - String word with removed character
     */
    private String deleteLetter(ArrayList<Character> characters, int position) {
        characters.remove(position);
        return rebuildWord(characters);
    }

    /**
     * Replaces a letter at a position in the word
     * @param characters - ArrayList of characters
     * @param position - index of the character to be replaced
     * @param character - replacing character
     * @return - String word with replaced character
     */
    private String replaceLetter(ArrayList<Character> characters, int position, char character) {
        characters.set(position, character);
        return rebuildWord(characters);
    }

    /**
     * Splits the word into a pair
     * @param characters - list of characters
     * @param position - index offset at which a space is inserted
     * @return - Array of Strings words, split by space
     */
    private String[] spaceSplit(ArrayList<Character> characters, int position) {
        characters.add(position, ' ');
        return rebuildWord(characters).split(" ");
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
