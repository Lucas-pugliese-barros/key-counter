package org.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KeyCounterModel {
    private int keyCount = 0;
    private boolean paused = false;
    private final StringProperty keyCountText = new SimpleStringProperty("Keys Typed: 0");

    public void incrementKeyCount() {
        if (!paused) {
            keyCount++;
            keyCountText.set("Keys Typed: " + keyCount);
        }
    }

    public void resetKeyCount() {
        keyCount = 0;
        keyCountText.set("Keys Typed: 0");
    }

    public void togglePause() {
        paused = !paused;
        keyCountText.set("Keys Typed: " + keyCount + (paused ? " (Paused)" : ""));
    }

    public boolean isPaused() {
        return paused;
    }

    public StringProperty keyCountTextProperty() {
        return keyCountText;
    }

    public int getKeyCount() {
        return keyCount;
    }
}