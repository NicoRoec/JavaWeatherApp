package app;

import javax.swing.*;
import java.awt.*;

public class WeatherTextComponent extends WeatherComponent {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Font font;

    public WeatherTextComponent(String text, int x, int y, int width, int height, Font font) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = font;
        initialize();
    }

    @Override
    public void initialize() {
        component = new JLabel(text);
        component.setBounds(x, y, width, height);
        component.setFont(font);
        component.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
