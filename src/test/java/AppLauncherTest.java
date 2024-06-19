import app.AppLauncher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Die Klasse `AppLauncherTest` testet die Funktionalität der `AppLauncher`-Klasse.
 * Sie stellt sicher, dass die Hauptmethode (`main`) der `AppLauncher`-Klasse ohne Ausnahmen ausgeführt wird.
 *
 * @author Nico
 * @version 1
 */
public class AppLauncherTest {

    /**
     * Testet die Hauptmethode der `AppLauncher`-Klasse.
     * Überprüft, dass keine Ausnahmen während der Ausführung der Methode auftreten.
     */
    @Test
    public void testMain() {
        assertDoesNotThrow(() -> {
            AppLauncher.main(new String[]{});
        });
    }
}
