import app.AppLauncher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse `TestAppLauncher` enthält Unit-Tests für die Klasse `app.AppLauncher`.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class TestAppLauncher {

    /**
     * Testet die `main`-Methode der Klasse `app.AppLauncher`.
     * Überprüft, ob die Klasse `app.AppLauncher` erfolgreich gestartet und nicht null ist.
     */
    @Test
    public void testMain() {
        AppLauncher.main(new String[]{});
        assertNotNull(AppLauncher.class);
    }
}
