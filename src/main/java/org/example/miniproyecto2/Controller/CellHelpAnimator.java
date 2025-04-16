package org.example.miniproyecto2.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class is responsible for animating a visual hint on a {@link TextField}.
 * It creates a short, golden blinking effect to suggest a helpful action or highlight a recommended cell.
 */
public class CellHelpAnimator {
    /**
     * The {@link TextField} where the help animation is applied.
     */
    private final TextField textField;
    /**
     * Timeline controlling the blinking animation.
     */
    private Timeline blinkTimeline;
    /**
     * Original CSS style of the {@link TextField}, saved for potential restoration.
     */
    private final String baseStyle;

    /**
     * Constructs a {@code CellHelpAnimator} for a given {@link TextField}.
     *
     * @param textField the {@link TextField} to animate with the help effect
     */
    public CellHelpAnimator(TextField textField) {
        this.textField = textField;
        this.baseStyle = textField.getStyle(); // Save the original style
        blinkTimeline = null;
    }

    /**
     * Triggers a short, golden blinking animation on the {@link TextField} to provide a visual help cue.
     * <p>This method has no effect if the animation is already running.</p>
     */
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