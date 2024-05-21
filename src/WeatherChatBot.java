package src;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField userInputField;
    private JButton sendButton;
    private String[] questions = {
            "Wo befinden Sie sich gerade?",
            "Möchten Sie die aktuelle Temperatur wissen? (ja/nein)",
            "Möchten Sie die Luftfeuchtigkeit wissen? (ja/nein)",
            "Möchten Sie die Windgeschwindigkeit wissen? (ja/nein)",
            "Möchten Sie den Wetterzustand wissen? (ja/nein)"
    };

    private int questionIndex = 0;
    private String userLocation = "";

    public WeatherChatBot() {
        // Fenster-Konfiguration
        setTitle("Weather ChatBot");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

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

    private void handleUserInput() {
        String userInput = userInputField.getText().trim();
        if (!userInput.isEmpty()) {
            chatArea.append("Du: " + userInput + "\n");
            processUserInput(userInput);
            userInputField.setText("");
        }
    }

    private void askQuestion() {
        if (questionIndex < questions.length) {
            chatArea.append("Bot: " + questions[questionIndex] + "\n");
        } else if (questionIndex == questions.length) {
            chatArea.append("Bot: Möchten Sie den Chatbot beenden? Wenn nein, wird erneut ein Standort abgefragt. (ja/nein)\n");
        }
    }

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
                dispose(); // Fenster schließen
            } else if (userInput.equalsIgnoreCase("nein")) {
                questionIndex = 0; // Zurück zur ersten Frage
                chatArea.append("Bot: Bitte geben Sie einen neuen Standort ein.\n");
                askQuestion();
            } else {
                chatArea.append("Bot: Bitte antworten Sie mit 'ja' oder 'nein'.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherChatBot().setVisible(true);
            }
        });
    }
}
