package gui.controller;

import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class VolumeSlider {
    private final Slider slider;
    private final MediaPlayer mediaPlayer;

    public VolumeSlider(Slider slider, MediaPlayer mediaPlayer) {
        this.slider = slider;
        this.mediaPlayer = mediaPlayer;

        initialize();
    }

    private void initialize() {
        if (slider != null && mediaPlayer != null) {
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.doubleValue();
                mediaPlayer.setVolume(volume);
            });
        }
    }
}
