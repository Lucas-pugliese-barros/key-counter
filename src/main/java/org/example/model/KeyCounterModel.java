package org.example.model;

public class KeyCounterModel {
    private int keyCount = 0;

    public void incrementKeyCount() {
        keyCount++;
    }

    public int getKeyCount() {
        return keyCount;
    }
}