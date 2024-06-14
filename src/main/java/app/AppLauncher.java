package app;

import javax.swing.*;

/**
 * Die Klasse `app.AppLauncher` dient als Einstiegspunkt für die Wetteranwendung.
 * Sie startet die grafische Benutzeroberfläche (GUI) der Anwendung.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class AppLauncher {
    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args die Befehlszeilenargumente
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Zeigt die Wetter-App-GUI an
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
