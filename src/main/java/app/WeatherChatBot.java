package app;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Die Klasse `WeatherChatBot` stellt die grafische Benutzeroberfläche (GUI) für den Wetter-Chatbot dar.
 * Der Chatbot interagiert mit dem Benutzer, um Wetterinformationen für einen bestimmten Ort abzurufen und anzuzeigen.
 *
 * @author Nico
 * @version 1
 */
public class WeatherChatBot extends JFrame {
    public JTextArea chatArea;
    public JTextField userInputField;
    public JButton sendButton;
    private String[] questions = {
            "Wo befinden Sie sich gerade?",
            "Möchten Sie die aktuelle Temperatur wissen? (ja/nein)",
            "Möchten Sie die Luftfeuchtigkeit wissen? (ja/nein)",
            "Möchten Sie die Windgeschwindigkeit wissen? (ja/nein)",
            "Möchten Sie den Wetterzustand wissen? (ja/nein)"
    };

    private int questionIndex = 0;
    private String userLocation = "";

    /**
     * Konstruktor für die `WeatherChatBot`-Klasse.
     * Initialisiert das Chatbot-Fenster und seine Komponenten.
     */
    public WeatherChatBot() {
        setTitle("Weather ChatBot");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        // Bild hinzufügen
        JLabel chatbotImage = new JLabel();
        chatbotImage.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon icon = loadImage("assets/gifs/chatbot.gif", 60, 60);
            chatbotImage.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(chatbotImage, BorderLayout.NORTH);

        // Chat-Bereich
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        add(chatScrollPane, BorderLayout.CENTER);

        // Benutzer-Eingabebereich
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        userInputField = new JTextField();
        sendButton = new JButton("Senden");
        inputPanel.add(userInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Event-Handling für den Senden-Button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        // Event-Handling für das Eingabefeld (Enter-Taste)
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        // Erste Frage stellen
        askQuestion();
    }

    /**
     * Verarbeitet die Benutzereingabe und zeigt sie im Chatbereich an.
     */
    private void handleUserInput() {
        String userInput = userInputField.getText().trim();
        if (!userInput.isEmpty()) {
            chatArea.append("Du: " + userInput + "\n");
            processUserInput(userInput);
            userInputField.setText("");
        }
    }

    /**
     * Stellt die nächste Frage aus der Fragenliste.
     */
    public void askQuestion() {
        if (questionIndex < questions.length) {
            chatArea.append("Bot: " + questions[questionIndex] + "\n");
        } else if (questionIndex == questions.length) {
            chatArea.append("Bot: Möchten Sie den Chatbot beenden? Wenn nein, wird erneut ein Standort abgefragt. (ja/nein)\n");
        }
    }

    /**
     * Verarbeitet die Antwort des Benutzers auf die aktuelle Frage.
     *
     * @param userInput die Antwort des Benutzers
     */
    private void processUserInput(String userInput) {
        if (questionIndex == 0) {
            userLocation = userInput;
            chatArea.append("Bot: Standort gesetzt zu " + userLocation + ".\n");
            questionIndex++;
            askQuestion();
        } else if (questionIndex > 0 && questionIndex < questions.length) {
            if (userInput.equalsIgnoreCase("ja")) {
                JSONObject weatherData = WeatherApp.getWeatherData(userLocation);
                if (weatherData != null) {
                    switch (questionIndex) {
                        case 1:
                            double temperature = (double) weatherData.get("temperature");
                            chatArea.append("Bot: Die aktuelle Temperatur beträgt " + temperature + "°C.\n");
                            break;
                        case 2:
                            long humidity = (long) weatherData.get("humidity");
                            chatArea.append("Bot: Die Luftfeuchtigkeit beträgt " + humidity + "%.\n");
                            break;
                        case 3:
                            double windspeed = (double) weatherData.get("windspeed");
                            chatArea.append("Bot: Die Windgeschwindigkeit beträgt " + windspeed + " km/h.\n");
                            break;
                        case 4:
                            String weatherCondition = (String) weatherData.get("weather_condition");
                            chatArea.append("Bot: Der aktuelle Wetterzustand ist " + weatherCondition + ".\n");
                            break;
                    }
                } else {
                    chatArea.append("Bot: Konnte Wetterdaten nicht abrufen. Bitte versuchen Sie es später erneut.\n");
                }
                questionIndex++;
            } else if (userInput.equalsIgnoreCase("nein")) {
                questionIndex++;
            } else {
                chatArea.append("Bot: Bitte antworten Sie mit 'ja' oder 'nein'.\n");
            }
            askQuestion();
        } else if (questionIndex == questions.length) {
            if (userInput.equalsIgnoreCase("ja")) {
                chatArea.append("Bot: Chatbot wird geschlossen. Auf Wiedersehen!\n");
                dispose();
            } else if (userInput.equalsIgnoreCase("nein")) {
                questionIndex = 0;
                chatArea.append("Bot: Bitte geben Sie einen neuen Standort ein.\n");
                askQuestion();
            } else {
                chatArea.append("Bot: Bitte antworten Sie mit 'ja' oder 'nein'.\n");
            }
        }
    }

    /**
     * Lädt ein Bild von den angegebenen Pfad und skaliert es.
     *
     * @param path  der Pfad zum Bild
     * @param width  die Breite des Bildes
     * @param height die Höhe des Bildes
     * @return das skalierte ImageIcon
     * @throws IOException wenn das Bild nicht gefunden werden kann
     */
    private ImageIcon loadImage(String path, int width, int height) throws IOException {
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(path);
        if (imageStream == null) {
            throw new IOException("Image file not found: " + path);
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = imageStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return new ImageIcon(new ImageIcon(buffer.toByteArray()).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
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
                new WeatherChatBot().setVisible(true);
            }
        });
    }
}
