import org.junit.jupiter.api.Test;
import src.main.java.AppLauncher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse `TestAppLauncher` enthält Unit-Tests für die Klasse `AppLauncher`.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class TestAppLauncher {

    /**
     * Testet die `main`-Methode der Klasse `AppLauncher`.
     * Überprüft, ob die Klasse `AppLauncher` erfolgreich gestartet und nicht null ist.
     */
    @Test
    public void testMain() {
        AppLauncher.main(new String[]{});
        assertNotNull(AppLauncher.class);
    }
}
