package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class WeatherImageComponent extends WeatherComponent {
    private String imagePath;
    private int width;
    private int height;
    private MouseAdapter mouseAdapter;

    public WeatherImageComponent(String imagePath, int width, int height, MouseAdapter mouseAdapter) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.mouseAdapter = mouseAdapter;
        initialize();
    }

    @Override
    public void initialize() {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        icon.setDescription(imagePath);
        component = new JLabel(icon);
        if (mouseAdapter != null) {
            component.addMouseListener(mouseAdapter);
        }
    }
}
