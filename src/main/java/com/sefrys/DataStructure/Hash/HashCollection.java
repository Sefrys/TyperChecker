package com.sefrys.DataStructure.Hash;

import java.util.LinkedList;

/**
 * Created by Ireneusz Zagan on 17.05.18, 20:53
 * Contact: sefrys@gmail.com
 */
public abstract class HashCollection {

    protected LinkedList<String>[] table;
    protected final int INIT_SIZE = 128;
    protected final int LINKED_LIST_SIZE = 128;
    protected int size;

    protected HashCollection() {
        table = new LinkedList[INIT_SIZE];
        size = INIT_SIZE;
    }

    public abstract void add(String value);
    public abstract void remove(String value);
    public abstract boolean lookup(String value);

    /**
     * Hashes the string value into an integer value
     * for HashTable indexing. High value modulo operation
     * ensures significant int value varation between indices
     * @param s is the string to hash
     * @param tableSize is used for modulo operation to
     *                  limit possible indices within table's
     *                  index boundary
     * @return the hashed value. In case of int overturn, the
     * negative value is transformed into a positive.
     */
    protected int hash(String s, int tableSize) {
        int hash = 0;

        for (int i = 0; i < s.length(); ++i) {
            hash *= 37;
            hash += s.charAt(i);
        }

        hash %= tableSize;

        return hash > 0 ? hash : Math.abs(-hash);
    }

    /**
     * Method is called only when the linked list size on any index is too big (>128)
     * HashTable is remapped with double its previous size, to keep its fast linked list lookup
     * @return rebuilt HashTable with double its previous size.
     */
    protected LinkedList<String>[] rehashMap() {
        size *= 2;

        LinkedList<String>[] newTable = new LinkedList[size];

        for (LinkedList<String> list : table) {
            for (String value : list) {
                int idx = hash(value, newTable.length);
                if (newTable[idx] == null)  newTable[idx] = new LinkedList<>();
                newTable[idx].add(value);
            }
        }
        return newTable;
    }
}
