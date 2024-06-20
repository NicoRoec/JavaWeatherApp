package app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse `WeatherAppGui` stellt die grafische Benutzeroberfläche (GUI) für die Wetteranwendung dar.
 * Sie ermöglicht es dem Benutzer, nach dem Wetter an einem bestimmten Ort zu suchen und die aktuellen Wetterdaten anzuzeigen.
 *
 * @author Nico
 * @version 1
 */
public class WeatherAppGui extends JFrame {
    private Font bebasFont;

    public JTextField searchTextField;
    public JLabel weatherConditionImage;
    public JLabel temperatureText;
    public JLabel weatherConditionDesc;
    public JLabel humidityText;
    public JLabel windspeedText;
    public JButton searchButton;

    /**
     * Konstruktor der Klasse `WeatherAppGui`. Lädt die Schriftarten und initialisiert die GUI-Komponenten.
     */
    public WeatherAppGui() {
        loadFonts();
        initializeComponents();
    }

    /**
     * Lädt die benutzerdefinierten Schriftarten.
     */
    private void loadFonts() {
        try (InputStream fontStream = getClass().getClassLoader().getResourceAsStream("assets/fonts/BebasNeue-Regular.ttf")) {
            if (fontStream != null) {
                bebasFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(bebasFont);
            } else {
                System.err.println("Font file not found. Using default font.");
                bebasFont = new Font("SansSerif", Font.PLAIN, 16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bebasFont = new Font("SansSerif", Font.PLAIN, 16);
        }
    }

    /**
     * Initialisiert die GUI-Komponenten der Wetteranwendung.
     */
    private void initializeComponents() {
        setTitle("Weather App");
        setSize(450, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLocationRelativeTo(null);

        List<WeatherComponent> components = new ArrayList<>();

        WeatherComponent earthIconComponent = new WeatherImageComponent("assets/gifs/earth.gif", 310, 310, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = (ImageIcon) weatherConditionImage.getIcon();
                String description = icon.getDescription();

                if (description != null && description.equals("assets/gifs/earth.gif")) {
                    String city = getUserLocation();
                    if (city != null) {
                        searchTextField.setText(city);
                        searchButton.doClick();
                    }
                }
            }
        });
        weatherConditionImage = earthIconComponent.getComponent();
        weatherConditionImage.setBounds(0, 55, 450, 300);
        components.add(earthIconComponent);

        temperatureText = new JLabel("");
        WeatherComponent temperatureComponent = new WeatherTextComponent("", 0, 350, 450, 54, bebasFont.deriveFont(48f));
        temperatureText = temperatureComponent.getComponent();
        components.add(temperatureComponent);

        weatherConditionDesc = new JLabel("");
        WeatherComponent conditionDescComponent = new WeatherTextComponent("", 0, 405, 450, 36, bebasFont.deriveFont(32f));
        weatherConditionDesc = conditionDescComponent.getComponent();
        components.add(conditionDescComponent);

        WeatherComponent humidityImageComponent = new WeatherImageComponent("assets/images/humidity2.png", 50, 50, null);
        humidityImageComponent.getComponent().setBounds(20, 480, 50, 50);
        components.add(humidityImageComponent);

        humidityText = new JLabel("<html><b>Luftfeuchtigkeit</b><br> -</html>");
        WeatherComponent humidityComponent = new WeatherTextComponent("<html><b>Luftfeuchtigkeit</b><br> -</html>", 75, 480, 135, 50, bebasFont.deriveFont(16f));
        humidityText = humidityComponent.getComponent();
        components.add(humidityComponent);

        WeatherComponent windSpeedImageComponent = new WeatherImageComponent("assets/images/windspeed2.png", 50, 50, null);
        windSpeedImageComponent.getComponent().setBounds(255, 480, 50, 50);
        components.add(windSpeedImageComponent);

        windspeedText = new JLabel("<html><b>Windgeschw.</b><br> -</html>");
        WeatherComponent windSpeedComponent = new WeatherTextComponent("<html><b>Windgeschw.</b><br> -</html>", 315, 480, 120, 55, bebasFont.deriveFont(16f));
        windspeedText = windSpeedComponent.getComponent();
        components.add(windSpeedComponent);

        WeatherComponent chatbotComponent = new WeatherImageComponent("assets/gifs/chatbot.gif", 60, 60, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new WeatherChatBot().setVisible(true);
            }
        });
        chatbotComponent.getComponent().setBounds(190, 545, 50, 50);
        components.add(chatbotComponent);

        searchTextField = new JTextField();
        searchTextField.setBounds(55, 15, 270, 35);
        searchTextField.setFont(bebasFont.deriveFont(16f));
        Border searchTextFieldBorder = new MatteBorder(2, 2, 2, 0, Color.BLACK);
        searchTextField.setBorder(searchTextFieldBorder);
        add(searchTextField);

        searchButton = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("assets/images/search.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(325, 15, 45, 35);
        Border searchButtonBorder = new MatteBorder(2, 0, 2, 2, Color.BLACK);
        searchButton.setBorder(searchButtonBorder);
        add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = searchTextField.getText();
                updateWeatherData(city);
            }
        });

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
        });

        for (WeatherComponent component : components) {
            add(component.getComponent());
        }

        setVisible(true);
    }


    /**
     * Holt den Standort des Benutzers basierend auf seiner IP-Adresse.
     * Hier ist zu beachten, dass die Kostenlose Standort API nicht genau funktioniert!
     * In anderen Worten ist der angegebene Standort nicht genau der Standort des Nutzers.
     * Um das Problem zu beheben, könnte man auf eine nicht kostenfreie API umsteigen.
     * Doch darauf wollte ich mit meinem Studenten Lohn darauf verzichten.
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
     * Aktualisiert die Wetterdaten für eine gegebene Stadt und zeigt sie in der GUI an.
     *
     * @param city die Stadt, für die die Wetterdaten angezeigt werden sollen
     */
    private void updateWeatherData(String city) {
        JSONObject weatherData = WeatherApp.getWeatherData(city);

        if (weatherData != null) {
            String temperature = String.format("%.1f°C", (double) weatherData.get("temperature"));
            String condition = (String) weatherData.get("weather_condition");
            String humidity = weatherData.get("humidity") + "%";
            String windspeed = String.format("%.1f km/h", (double) weatherData.get("windspeed"));

            temperatureText.setText(temperature);
            weatherConditionDesc.setText(condition);
            humidityText.setText("<html><b>Luftfeuchtigkeit</b><br>" + humidity + "</html>");
            windspeedText.setText("<html><b>Windgeschw.</b><br>" + windspeed + "</html>");
        } else {
            temperatureText.setText("N/A");
            weatherConditionDesc.setText("N/A");
            humidityText.setText("<html><b>Luftfeuchtigkeit</b><br> N/A</html>");
            windspeedText.setText("<html><b>Windgeschw.</b><br> N/A</html>");
        }
    }

    private class WeatherImageComponent extends WeatherComponent {
        private String imagePath;
        private int width;
        private int height;
        private MouseAdapter mouseAdapter;

        /**
         * Konstruktor für `WeatherImageComponent`.
         *
         * @param imagePath der Pfad zum Bild
         * @param width die Breite des Bildes
         * @param height die Höhe des Bildes
         * @param mouseAdapter der MouseAdapter für das Bild
         */
        public WeatherImageComponent(String imagePath, int width, int height, MouseAdapter mouseAdapter) {
            this.imagePath = imagePath;
            this.width = width;
            this.height = height;
            this.mouseAdapter = mouseAdapter;
            initialize();
        }

        @Override
        public void initialize() {
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream(imagePath);
                if (imageStream == null) {
                    throw new IOException("Image file not found: " + imagePath);
                }
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = imageStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                ImageIcon icon = new ImageIcon(new ImageIcon(buffer.toByteArray()).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                icon.setDescription(imagePath);
                component = new JLabel(icon);
                if (mouseAdapter != null) {
                    component.addMouseListener(mouseAdapter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WeatherTextComponent extends WeatherComponent {
        private String text;
        private int x;
        private int y;
        private int width;
        private int height;
        private Font font;

        /**
         * Konstruktor für `WeatherTextComponent`.
         *
         * @param text der Text, der angezeigt werden soll
         * @param x die x-Koordinate des Textes
         * @param y die y-Koordinate des Textes
         * @param width die Breite des Textes
         * @param height die Höhe des Textes
         * @param font die Schriftart des Textes
         */
        public WeatherTextComponent(String text, int x, int y, int width, int height, Font font) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.font = font;
            initialize();
        }

        @Override
        public void initialize() {
            component = new JLabel(text);
            component.setBounds(x, y, width, height);
            component.setFont(font);
            component.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private abstract class WeatherComponent {
        protected JLabel component;

        /**
         * Initialisiert die Komponente.
         */
        public abstract void initialize();

        /**
         * Gibt die GUI-Komponente zurück.
         *
         * @return die GUI-Komponente
         */
        public JLabel getComponent() {
            return component;
        }
    }

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args die Befehlszeilenargumente
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
