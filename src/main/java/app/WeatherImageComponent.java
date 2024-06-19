package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Die Klasse `WeatherImageComponent` stellt eine Bildkomponente für die Wetteranwendung dar.
 * Sie erweitert die abstrakte Klasse `WeatherComponent` und initialisiert ein JLabel mit einem Bild.
 *
 * @author Nico
 * @version 1
 */
public class WeatherImageComponent extends WeatherComponent {
    private String imagePath;
    private int width;
    private int height;
    private MouseAdapter mouseAdapter;

    /**
     * Konstruktor für `WeatherImageComponent`.
     *
     * @param imagePath der Pfad zum Bild
     * @param width die Breite des Bildes
     * @param height die Höhe des Bildes
     * @param mouseAdapter der MouseAdapter für das Bild
     */
    public WeatherImageComponent(String imagePath, int width, int height, MouseAdapter mouseAdapter) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.mouseAdapter = mouseAdapter;
        initialize();
    }

    /**
     * Initialisiert die Bildkomponente.
     * Skaliert das Bild auf die angegebene Größe und fügt einen MouseAdapter hinzu, falls vorhanden.
     */
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
