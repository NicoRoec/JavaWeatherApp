package app;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse `WeatherTextComponent` stellt eine Textkomponente für die Wetteranwendung dar.
 * Sie erweitert die abstrakte Klasse `WeatherComponent` und initialisiert ein JLabel mit Text.
 *
 * @author Nico
 * @version 1
 */
public class WeatherTextComponent extends WeatherComponent {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Font font;

    /**
     * Konstruktor für `WeatherTextComponent`.
     *
     * @param text der Text, der angezeigt werden soll
     * @param x die x-Koordinate des Textes
     * @param y die y-Koordinate des Textes
     * @param width die Breite des Textes
     * @param height die Höhe des Textes
     * @param font die Schriftart des Textes
     */
    public WeatherTextComponent(String text, int x, int y, int width, int height, Font font) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = font;
        initialize();
    }

    /**
     * Initialisiert die Textkomponente.
     * Setzt den Text, die Position, die Größe und die Schriftart des JLabels.
     */
    @Override
    public void initialize() {
        component = new JLabel(text);
        component.setBounds(x, y, width, height);
        component.setFont(font);
        component.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
