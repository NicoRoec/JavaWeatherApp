package src;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
    private Font bebasFont;

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

        // prevent any resize of gui
        setResizable(false);

        // set background color of the content pane
        getContentPane().setBackground(Color.WHITE);

        // Load Bebas Font
        try {
            bebasFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/BebasNeue-Regular.ttf")).deriveFont(32f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(bebasFont); // Register the font
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            bebasFont = new Font("Dialog", Font.PLAIN, 32); // Fallback Font
        }

        addGuiComponents();
    }

    private void addGuiComponents(){
        // search field
        JTextField searchTextField = new JTextField();

        // set location and size of search field
        searchTextField.setBounds(75, 15, 255, 35);

        // change font of search field
        searchTextField.setFont(bebasFont.deriveFont(18f));

        // create a border for search field
        searchTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 7, 0, 0)
        ));

        add(searchTextField);

        // weather image
        JLabel weatherConditionImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/earth.gif").getImage().getScaledInstance(310, 310, Image.SCALE_DEFAULT)));
        weatherConditionImage.setBounds(0, 55, 450, 300);
        add(weatherConditionImage);

        // temperature text
        JLabel temperatureText = new JLabel("");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(bebasFont.deriveFont(48f));

        // center text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(bebasFont.deriveFont(32f));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity img
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity2.png", 50, 50));
        humidityImage.setBounds(40, 480, 50,50);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Luftfeuchtigkeit</b><br> -</html>");
        humidityText.setBounds(95, 480, 135, 50);
        humidityText.setFont(bebasFont.deriveFont(16f)); // Set Bebas font for humidity text
        add(humidityText);

        // wind speed
        JLabel windSpeedImage = new JLabel(loadImage("src/assets/windspeed2.png", 50, 50));
        windSpeedImage.setBounds(275, 480, 50, 50);
        add(windSpeedImage);

        // windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windgeschw.</b><br> -</html>");
        windspeedText.setBounds(335, 480, 120, 55);
        windspeedText.setFont(bebasFont.deriveFont(16f)); // Set Bebas font for windspeed text
        add(windspeedText);

        // chatbot img
        JLabel chatbotImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/chatbot.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        chatbotImage.setBounds(375, 545, 50, 50);
        add(chatbotImage);

        // next page
        JLabel nextPageImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/swipe.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        nextPageImage.setBounds(200, 540, 60, 60);
        add(nextPageImage);

        // search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png", 25, 25));

        //change cursor to hand cursor when hover
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(330, 15, 45, 35);
        // create a border for search button
        Border searchButtonBorder = new MatteBorder(2, 0, 2, 2, Color.BLACK);
        searchButton.setBorder(searchButtonBorder);

        // define the action to be performed
        ActionListener searchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchTextField.getText();

                // validate input - remove white space to ensure non-empty text
                if(userInput.replaceAll("//s", "").length() <= 0){
                    return;
                }

                // retrieve weather data
                weatherData = WeatherApp.getWeatherData(userInput);

                // update gui

                // update weather img
                String weatherCondition = (String) weatherData.get("weather_condition");
                String dayOrNight = (String) weatherData.get("is_day"); // retrieve the day/night status

                // depending on the condition and day/night, we will  update an img that corresponds with the condition
                String imagePath = "";
                switch (weatherCondition) {
                    case "Klarer Himmel":
                        imagePath = dayOrNight.equals("Tag") ? "src/assets/clear2.gif" : "src/assets/clearNight.gif";
                        break;
                    case "Bewölkt":
                        imagePath = dayOrNight.equals("Tag") ? "src/assets/cloudy2.gif" : "src/assets/cloudyNight.gif";
                        break;
                    case "Regen":
                        imagePath = "src/assets/rain2.gif";
                        break;
                    case "Schnee":
                        imagePath = "src/assets/snow2.gif";
                        break;
                }

                weatherConditionImage.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));


                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + "°C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Luftfeuchtigkeit</b><br>" + humidity + "%</html>");

                // update windspeed
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windgeschw.</b><br>" + windspeed + "km/h</html>");
            }
        };

        searchButton.addActionListener(searchAction);
        searchTextField.addActionListener(searchAction);

        add(searchButton);
    }

    private ImageIcon loadImage(String resourcePath, int width, int height){
        try{
            // read Img file from given path
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // resize image to fit specified dimensions
            Image resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // returns image icon that component can read it
            return new ImageIcon(resizedImg);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Resource konnte nicht gefunden werden.");
            return null;
        }
    }

    public static void main(String[] args) {
        new WeatherAppGui().setVisible(true);
    }
}
