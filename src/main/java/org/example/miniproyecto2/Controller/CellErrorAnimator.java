package org.example.miniproyecto2.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CellErrorAnimator {

    private final TextField textField;
    private Timeline blinkTimeline;
    private final String baseStyle;

    public CellErrorAnimator(TextField textField) {
        this.textField = textField;
        this.baseStyle = textField.getStyle(); // Save the original style
        blinkTimeline = null;
    }

    public void startBlinking() {
        if (blinkTimeline != null) return; // Already running


        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.LIGHTCORAL);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(15);
        dropShadow.setSpread(0.7); // Makes it more like a glow than a fuzzy shadow

        textField.setEffect(dropShadow);

        blinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.9), new KeyValue(dropShadow.colorProperty(), Color.TRANSPARENT)),
                new KeyFrame(Duration.seconds(0.9), new KeyValue(dropShadow.colorProperty(), Color.LIGHTCORAL))
        );

        blinkTimeline.setCycleCount(Animation.INDEFINITE);
        blinkTimeline.setAutoReverse(true);
        blinkTimeline.play();
    }
    public void stopBlinking() {
        if (blinkTimeline != null) {
            blinkTimeline.stop();
            blinkTimeline = null;
            textField.setEffect(null); // Restore original style
        }
    }
}
