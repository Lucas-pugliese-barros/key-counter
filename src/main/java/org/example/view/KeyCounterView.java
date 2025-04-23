package org.example.view;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.example.GlobalKeyListener;
import org.example.controller.KeyCounterController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyCounterView {
    private static final Logger LOGGER = Logger.getLogger(KeyCounterView.class.getName());

    private final KeyCounterController controller;
    private final NativeKeyListener keyListener;

    public KeyCounterView(KeyCounterController controller) {
        this.controller = controller;
        this.keyListener = new GlobalKeyListener(this::onKeyTyped);

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(keyListener);
        } catch (NativeHookException e) {
            LOGGER.log(Level.SEVERE, "Failed to register native hook", e);
            throw new RuntimeException(e);
        }
    }

    private void onKeyTyped() {
        controller.handleKeyPress();
    }

    public void shutdown() {
        try {
            GlobalScreen.removeNativeKeyListener(keyListener);
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            LOGGER.log(Level.SEVERE, "Shutdown KeyListener", e);
        }
    }
}
