package com.sefrys.DataStructure.Hash;

import java.util.LinkedList;

/**
 * Created by Ireneusz Zagan on 17.05.18, 21:07
 * Contact: sefrys@gmail.com
 */
public class HashTable extends HashCollection{

    public HashTable() {
        super();
    }

    /**
     * Adds a String value to LinkedList on HashTable by value's hashed index
     * If a LinkedList does not exist on index, create a new LinkedList, and then add.
     * If the index's LinkedList size is greater than 128, rebuild the HashTable with double its size.
     * @param value - the String value being added.
     */
    @Override
    public void add(String value) {
        int idx = hash(value, table.length);
        if (table[idx] == null) table[idx] = new LinkedList<>();
        table[idx].add(value);

        if (table[idx].size() > LINKED_LIST_SIZE) table = rehashMap();
    }

    /**
     * Removed a String value from LinkedList on HashTable by value's hashed index, and its value.
     * If there is no LinkedList under the index, do nothing
     * @param value - String value being removed.
     */
    @Override
    public void remove(String value) {
        int idx = hash(value, table.length);
        if (table[idx] != null) table[idx].remove(value);
    }

    /**
     * Finds the LinkedList the value is under by its hash number. O(1) complexity.
     * Followed by LinkedList iteration of up to 128 elements. O(n) complexity
     * @param value - String value being looked up
     * @return boolean value dependent on if the value was found.
     */
    @Override
    public boolean lookup(String value) {
        int idx = hash(value, table.length);
        if (table[idx] == null) return false;
        for (String s : table[idx]) {
            if (s.equals(value)) return true;
        }
        return false;
    }
}
