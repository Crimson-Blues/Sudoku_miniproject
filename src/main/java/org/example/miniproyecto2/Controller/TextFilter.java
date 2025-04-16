package org.example.miniproyecto2.Controller;

import javafx.scene.control.TextFormatter;

/**
 * Utility class that provides a {@link javafx.scene.control.TextFormatter} to restrict user input
 * in a {@link javafx.scene.control.TextField} to only digits from 1 to 6.
 */
public class TextFilter {

    /**
     * Returns a {@link javafx.scene.control.TextFormatter} that filters input to allow only a single digit
     * from 1 to 6. Any invalid input or multiple characters are automatically corrected or rejected.
     *
     * <p>The filter works as follows:
     * <ul>
     *     <li>Allows empty input (to permit deletion).</li>
     *     <li>Allows single-character digits between 1 and 6.</li>
     *     <li>If more than one character is typed, it checks if the *first* character is valid and keeps only that.</li>
     *     <li>Rejects all other input (non-digits or digits outside the 1–6 range).</li>
     * </ul>
     *
     * @return a configured {@link javafx.scene.control.TextFormatter} to be applied on a {@link javafx.scene.control.TextField}.
     */
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
