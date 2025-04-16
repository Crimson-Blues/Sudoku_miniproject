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
 * This class is responsible for animating the error effect on a {@link TextField}.
 * It adds a blinking drop shadow to indicate an invalid cell input.
 * <p>The blinking effect will loop continuously until it is stopped.</p>
 */
public class CellErrorAnimator {
    /**
     * The {@link TextField} that will have the error effect applied.
     */
    private final TextField textField;
    /**
     * The timeline that controls the blinking animation.
     */
    private Timeline blinkTimeline;
    /**
     * The base style of the {@link TextField}, saved to restore the original style after the animation.
     */
    private final String baseStyle;
    /**
     * Constructs a new {@code CellErrorAnimator} for the given {@link TextField}.
     *
     * @param textField the {@link TextField} to which the error effect will be applied
     */
    public CellErrorAnimator(TextField textField) {
        this.textField = textField;
        this.baseStyle = textField.getStyle(); // Save the original style
        blinkTimeline = null;
    }

    /**
     * Starts the blinking animation to indicate an error on the associated {@link TextField}.
     * <p>This method will apply a glowing drop shadow effect to the {@link TextField} and make it blink.</p>
     * <p>If the animation is already running, it does nothing.</p>
     */
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
    /**
     * Stops the blinking animation and restores the original style of the {@link TextField}.
     * <p>If the animation is not running, this method does nothing.</p>
     */
    public void stopBlinking() {
        if (blinkTimeline != null) {
            blinkTimeline.stop();
            blinkTimeline = null;
            textField.setEffect(null); // Restore original style
        }
    }
}
