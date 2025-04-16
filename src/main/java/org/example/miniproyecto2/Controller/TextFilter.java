package org.example.miniproyecto2.Controller;

import javafx.scene.control.TextFormatter;

public class TextFilter {

    public static TextFormatter<String> getFilter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || newText.matches("[1-6]")) {
                return change;
            }

            // If more than 1 char, take the last one
            if (newText.length() > 1) {
                String lastChar = newText.substring(0,1);

                if (lastChar.matches("[1-6]")) {
                    // Force the change to just contain the last valid char
                    change.setText(lastChar);
                    change.setRange(0, change.getControlText().length()); // Replace all text
                    return change;
                } else {
                    return null; // Last char isn't valid
                }
            }
            return null;
        });
    }
}
