package app;

import javax.swing.*;

/**
 * Die abstrakte Klasse `WeatherComponent` dient als Basisklasse für alle GUI-Komponenten in der Wetteranwendung.
 * Jede spezifische Wetterkomponente muss diese Klasse erweitern und die Methode `initialize` implementieren.
 *
 * @author Nico
 * @version 1
 */
public abstract class WeatherComponent {
    protected JLabel component;

    /**
     * Initialisiert die spezifische Wetterkomponente.
     * Diese Methode muss von allen Unterklassen implementiert werden.
     */
    public abstract void initialize();

    /**
     * Gibt die GUI-Komponente zurück.
     *
     * @return die GUI-Komponente als JLabel
     */
    public JLabel getComponent() {
        return component;
    }
}
