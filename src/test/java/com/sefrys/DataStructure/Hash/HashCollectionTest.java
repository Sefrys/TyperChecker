package com.sefrys.DataStructure.Hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ireneusz Zagan on 17.05.18, 21:23
 * Contact: sefrys@gmail.com
 */
public class HashCollectionTest {

    private HashTable map;

    @BeforeEach
    void createHashTable() {
        map = new HashTable();
    }

    @Test
    void testHashTableAdd() {
        map.add("Hello");
        map.add("Hell");
        map.add("Helium");
    }

    @Test
    void testHashTableLookUp() {
        map.add("Hello");
        map.add("Hell");
        map.add("Helium");

        assertTrue(map.lookup("Hello"));
        assertTrue(map.lookup("Hell"));
        assertTrue(map.lookup("Helium"));
    }

    @Test
    void testHashTableLookUpNotExistent() {
        map.add("Heck");

        assertFalse(map.lookup("Hello"));
    }

    @Test
    void testHashTableRemove() {
        map.add("Hello");
        map.add("Hell");
        map.add("Helium");
        map.remove("Hell");

        assertFalse(map.lookup("Hell"));
    }

    @Test
    void testHashTableRemoveFromNotExistingList() {
        map.remove("Test");
    }

    @Test
    void testHashTableRemoveTwice() {
        map.add("Help");
        map.remove("Help");
        map.remove("Help");
    }

    @Test
    void testHashTableAddLongWordOverturningInt() {
        map.add("thisIsAReallyLongWordLikeSuperVeryLog");
    }

    @Test
    void testLookUpWithFullDictionary() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("dictionary.txt")).getFile());
        Path dictionaryPath = file.toPath();
        List<String> wordList = Files.readAllLines(dictionaryPath);

        for (String word : wordList) {
            map.add(word);
        }

        assertTrue(map.lookup("algorithm"));
    }

}
