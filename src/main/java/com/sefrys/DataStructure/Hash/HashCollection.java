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
}
