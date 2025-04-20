package org.example;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.function.Function;

public class GlobalKeyListener implements NativeKeyListener {

    public OnKeyTyped onKeyTyped;

    public GlobalKeyListener(OnKeyTyped onKeyTyped) {
        this.onKeyTyped = onKeyTyped;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        onKeyTyped.onAction();
        NativeKeyListener.super.nativeKeyTyped(nativeEvent);
    }

    public interface OnKeyTyped {
        void onAction();
    }
}
