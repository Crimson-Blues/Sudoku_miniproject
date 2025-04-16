package org.example.miniproyecto2.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CellHelpAnimator {

    private final TextField textField;
    private Timeline blinkTimeline;
    private final String baseStyle;

    public CellHelpAnimator(TextField textField) {
        this.textField = textField;
        this.baseStyle = textField.getStyle(); // Save the original style
        blinkTimeline = null;
    }

    public void Blink() {
        if (blinkTimeline != null) return; // Already running


        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GOLD);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setRadius(15);
        dropShadow.setSpread(0.7); // Makes it more like a glow than a fuzzy shadow

        textField.setEffect(dropShadow);

        blinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.9), new KeyValue(dropShadow.colorProperty(), Color.TRANSPARENT)),
                new KeyFrame(Duration.seconds(0.9), new KeyValue(dropShadow.colorProperty(), Color.GOLD))
        );

        blinkTimeline.setCycleCount(3);
        blinkTimeline.setAutoReverse(true);

        blinkTimeline.setOnFinished(e -> {
            textField.setEffect(null);
            blinkTimeline = null;
        });

        blinkTimeline.play();
    }

}