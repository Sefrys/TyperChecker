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

    @Override
    public void add(String value) {
        int idx = hash(value, table.length);
        if (table[idx] == null) table[idx] = new LinkedList<String>();
        table[idx].add(value);

        if (table[idx].size() > LINKED_LIST_SIZE) table = rehashMap();
    }

    public void remove(String value) {

    }

    public boolean lookup(String value) {
        return false;
    }
}
