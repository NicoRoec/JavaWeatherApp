import app.AppLauncher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppLauncherTest {

    @Test
    public void testMain() {
        assertDoesNotThrow(() -> {
            AppLauncher.main(new String[]{});
        });
    }
}
