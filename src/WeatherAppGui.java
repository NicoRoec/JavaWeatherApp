//package src;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;

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

        addGuiComponents();
    }

    private void addGuiComponents(){
        // search field
        JTextField searchTextField = new JTextField();

        // set location and size of search field
        searchTextField.setBounds(80, 15, 280, 50);

        // change font of search field
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        // weather image
        JLabel weatherConditionImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/clear2.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        weatherConditionImage.setBounds(0, 75, 450, 250);
        add(weatherConditionImage);

        // temperature text
        JLabel temperatureText = new JLabel("10 °C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        // center text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("Clear");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity img
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity2.png", 50, 50));
        humidityImage.setBounds(15, 500, 50,50);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(70, 500, 80, 50);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // wind speed
        JLabel windSpeedImage = new JLabel(loadImage("src/assets/windspeed2.png", 50, 50));
        windSpeedImage.setBounds(170, 500, 50, 50);
        add(windSpeedImage);

        // windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
        windspeedText.setBounds(230, 500, 90, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windspeedText);

        // chatbot img
        JLabel chatbotImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/chatbot.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        chatbotImage.setBounds(350, 500, 50, 50);
        add(chatbotImage);

        // next page
        JLabel nextPageImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/swipe.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        nextPageImage.setBounds(200, 560, 60, 60);
        add(nextPageImage);

        // search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png", 40, 40));

        //change cursor to hand cursor when hover
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(351, 15, 50, 50);
        searchButton.addActionListener(new ActionListener() {
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

                // depending on the condition, we will  update an img that corresponds with the condition
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(new ImageIcon(new ImageIcon("src/assets/clear2.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(new ImageIcon(new ImageIcon("src/assets/cloudy2.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(new ImageIcon(new ImageIcon("src/assets/rain2.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(new ImageIcon(new ImageIcon("src/assets/snow2.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + "°C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b><br>" + humidity + "%</html>");

                // update windspeed
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windspeed</b><br>" + windspeed + "km/h</html>");
            }
        });
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
