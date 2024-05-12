package src;
import javax.swing.*;

public class WeatherAppGui extends JFrame {
    public WeatherAppGui(){
        // title
        super("THE Weather App");

        // configure gui to end program's process when closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // gui size
        setSize(450, 650);

        // center gui
        setLocationRelativeTo(null);

        // making layout manager null to manually position components within the gui
        setLayout(null);

        //prevent any resize of gui
        setResizable(false);
    }
}
