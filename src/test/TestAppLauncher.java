package src.test;

import org.junit.jupiter.api.Test;
import src.AppLauncher;

import static org.junit.jupiter.api.Assertions.*;

public class TestAppLauncher {

    @org.junit.Test
    @Test
    public void testMain() {
        AppLauncher.main(new String[]{});
        assertNotNull(AppLauncher.class);
    }
}

