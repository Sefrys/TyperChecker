package com.sefrys.TyperChecker;

import com.sefrys.DataStructure.Hash.HashTable;
import com.sefrys.TyperChecker.Spellchecker.SpellChecker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Ireneusz Zagan on 17.05.18, 22:09
 * Contact: sefrys@gmail.com
 */
public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        HashTable map = new HashTable();

        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("dictionary.txt")).getFile());
        Path dictionaryPath = file.toPath();
        List<String> wordList = Files.readAllLines(dictionaryPath);

        for (String word : wordList) {
            map.add(word);
        }

        SpellChecker spellChecker = new SpellChecker(map);
        runMenu(spellChecker);
    }

    private static void runMenu(SpellChecker spellChecker) {
        System.out.println("\nPress '1' to enter spellchecker. Press '0' to exit");

        String choice = scanner.next();

        switch (choice) {
            case "1": {
                printOutSuggestions(spellChecker);
                runMenu(spellChecker);
                break;
            }
            case "0": {
                scanner.close();
                System.exit(0);
                break;
            }
            default: {
                runMenu(spellChecker);
            }
        }
    }

    private static void printOutSuggestions(SpellChecker spellChecker) {
        System.out.print("Enter value to spellChecker: ");
        String value = scanner.next();
        Set<String> suggestions = spellChecker.gatherSuggestions(value);

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions");
        } else {
            System.out.println("Found: " + suggestions.size() + " suggestion(s)");
            for (String suggestion : suggestions) {
                System.out.print(suggestion + ", ");
            }
        }
    }
}
