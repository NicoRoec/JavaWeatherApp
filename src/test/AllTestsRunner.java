package src.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Die Klasse `AllTestsRunner` dient zum Ausführen aller Unit-Tests der Anwendung.
 * Sie führt die Tests der Klassen `TestAppLauncher`, `TestWeatherApp`, `TestWeatherAppGui` und `TestWeatherChatBot` aus und gibt die Ergebnisse aus.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class AllTestsRunner {
    /**
     * Der Einstiegspunkt der Anwendung.
     * Führt die Unit-Tests der Anwendung aus und gibt die Ergebnisse der Tests aus.
     *
     * @param args die Befehlszeilenargumente
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                TestAppLauncher.class,
                TestWeatherApp.class,
                TestWeatherAppGui.class,
                TestWeatherChatBot.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("All tests successful: " + result.wasSuccessful());
    }
}
