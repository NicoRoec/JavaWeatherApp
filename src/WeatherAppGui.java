package src;

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

        addGuiComponents();
    }

    private void addGuiComponents(){
        // search field
        JTextField searchTextField = new JTextField();

        // set location and size of component
        searchTextField.setBounds(15, 15, 351, 45);

        // change font
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchTextField);

        // weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/clear2.png", 220, 220));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);

        // temperature text
        JLabel temperatureText = new JLabel("10 °C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        // center text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        // weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        // humidity img
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity2.png", 74, 66));
        humidityImage.setBounds(15, 500, 74,66);
        add(humidityImage);

        // humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        // wind speed
        JLabel windSpeedImage = new JLabel(loadImage("src/assets/windspeed2.png", 74, 66));
        windSpeedImage.setBounds(220, 500, 74, 66);
        add(windSpeedImage);

        // windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
        windspeedText.setBounds(310, 500, 90, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windspeedText);

        // search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png", 47, 45));

        //change cursor to hand cursor when hover
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
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
                switch(weatherCondition){
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear2.png", 220, 220));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy2.png", 220, 220));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain2.png", 220, 220));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow2.png", 220, 220));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + "°C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b>" + humidity + "%</html>");

                // update windspeed
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windspeed</b>" + windspeed + "km/h</html>");
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
