package src.main.java;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Die Klasse `WeatherAppGui` stellt die grafische Benutzeroberfläche für die Wetteranwendung dar.
 * Sie zeigt die aktuellen Wetterdaten basierend auf der Benutzereingabe oder dem Standort des Benutzers an.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
    private Font bebasFont;

    public JTextField searchTextField;
    private JLabel weatherConditionImage;
    public JLabel temperatureText;
    public JLabel weatherConditionDesc;
    public JLabel humidityText;
    public JLabel windspeedText;
    private JButton searchButton;

    /**
     * Konstruktor für die WeatherAppGui-Klasse.
     * Initialisiert das GUI-Fenster und seine Komponenten.
     */
    public WeatherAppGui(){
        super("THE Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        // Load Bebas Font
        try {
            bebasFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/BebasNeue-Regular.ttf")).deriveFont(32f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(bebasFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            bebasFont = new Font("Dialog", Font.PLAIN, 32);
        }

        addGuiComponents();
    }

    /**
     * Fügt die GUI-Komponenten zum Fenster hinzu und positioniert sie.
     */
    private void addGuiComponents(){
        searchTextField = new JTextField();
        searchTextField.setBounds(75, 15, 255, 35);
        searchTextField.setFont(bebasFont.deriveFont(18f));
        searchTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(0, 7, 0, 0)
        ));
        add(searchTextField);

        ImageIcon earthIcon = new ImageIcon(new ImageIcon("src/assets/earth.gif").getImage().getScaledInstance(310, 310, Image.SCALE_DEFAULT));
        earthIcon.setDescription("src/assets/earth.gif");

        weatherConditionImage = new JLabel(earthIcon);
        weatherConditionImage.setBounds(0, 55, 450, 300);
        weatherConditionImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = (ImageIcon) weatherConditionImage.getIcon();
                String description = icon.getDescription();

                if (description != null && description.equals("src/assets/earth.gif")) {
                    String city = getUserLocation();
                    if (city != null) {
                        searchTextField.setText(city);
                        searchButton.doClick();
                    }
                }
            }
        });
        add(weatherConditionImage);

        temperatureText = new JLabel("");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(bebasFont.deriveFont(48f));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        weatherConditionDesc = new JLabel("");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(bebasFont.deriveFont(32f));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity2.png", 50, 50));
        humidityImage.setBounds(40, 480, 50, 50);
        add(humidityImage);

        humidityText = new JLabel("<html><b>Luftfeuchtigkeit</b><br> -</html>");
        humidityText.setBounds(95, 480, 135, 50);
        humidityText.setFont(bebasFont.deriveFont(16f));
        add(humidityText);

        JLabel windSpeedImage = new JLabel(loadImage("src/assets/windspeed2.png", 50, 50));
        windSpeedImage.setBounds(275, 480, 50, 50);
        add(windSpeedImage);

        windspeedText = new JLabel("<html><b>Windgeschw.</b><br> -</html>");
        windspeedText.setBounds(335, 480, 120, 55);
        windspeedText.setFont(bebasFont.deriveFont(16f));
        add(windspeedText);

        JLabel chatbotImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/chatbot.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        chatbotImage.setBounds(375, 545, 50, 50);
        chatbotImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new WeatherChatBot().setVisible(true);
            }
        });
        add(chatbotImage);

        JLabel nextPageImage = new JLabel(new ImageIcon(new ImageIcon("src/assets/swipe.gif").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        nextPageImage.setBounds(200, 540, 60, 60);
        add(nextPageImage);

        searchButton = new JButton(loadImage("src/assets/search.png", 25, 25));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(330, 15, 45, 35);
        Border searchButtonBorder = new MatteBorder(2, 0, 2, 2, Color.BLACK);
        searchButton.setBorder(searchButtonBorder);

        ActionListener searchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTextField.getText();
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                weatherData = WeatherApp.getWeatherData(userInput);

                String weatherCondition = (String) weatherData.get("weather_condition");
                String dayOrNight = (String) weatherData.get("is_day");

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

                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + "°C");

                weatherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Luftfeuchtigkeit</b><br>" + humidity + "%</html>");

                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windgeschw.</b><br>" + windspeed + "km/h</html>");
            }
        };

        searchButton.addActionListener(searchAction);
        searchTextField.addActionListener(searchAction);

        add(searchButton);
    }

    /**
     * Lädt ein Bild aus dem angegebenen Pfad und skaliert es auf die gewünschte Größe.
     *
     * @param resourcePath der Pfad zur Bilddatei
     * @param width die gewünschte Breite des Bildes
     * @param height die gewünschte Höhe des Bildes
     * @return das geladene und skalierte Bild als ImageIcon
     */
    private ImageIcon loadImage(String resourcePath, int width, int height){
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));
            Image resizedImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Resource konnte nicht gefunden werden.");
            return null;
        }
    }

    /**
     * Holt den Standort des Benutzers basierend auf seiner IP-Adresse.
     * Hier ist zu beachten, dass die Kostenlose Standort API nicht genau funktioniert!
     * In anderen Worten ist der angegebene Standort nicht genau der Standort des Nutzers.
     * Um das Problem zu beheben, könnte man auf eine nicht kostenfreie API umsteigen.
     * Doch darauf wollten wir mit unserem Studenten Lohn darauf verzichten.
     *
     * @return der Standort des Benutzers als String
     */
    private String getUserLocation() {
        try {
            String apiUrl = "http://ip-api.com/json";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JSONObject response = (JSONObject) new JSONParser().parse(reader);
                return (String) response.get("city");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args die Befehlszeilenargumente
     */
    public static void main(String[] args) {
        new WeatherAppGui().setVisible(true);
    }
}
