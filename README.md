
```markdown
```
# Wetter-App

Dies ist eine Java-basierte Wetter-App, die aktuelle Wetterdaten für einen bestimmten Standort anzeigt. Die App besteht aus einer grafischen Benutzeroberfläche (GUI) und einem Chatbot, der den Benutzer durch eine Reihe von Fragen führt, um Wetterinformationen bereitzustellen.
```
```
## Inhaltsverzeichnis
- [Installation](#installation)
- [Verwendung](#verwendung)
- [Projektstruktur](#projektstruktur)
- [Tests](#tests)
- [Technologien](#technologien)
- [Autoren](#autoren)
- [Anforderungen und Beispiele](#anforderungen-und-beispiele)
```
```
## Installation

1. **Voraussetzungen**:
    - Java Development Kit (JDK) 8 oder höher
    - Maven (für das Build-Management)

2. **Projekt-Setup**:
    - Klonen Sie das Repository:
      ```bash
      git clone <repository-url>
      cd weather-app
      ```

    - Bauen Sie das Projekt mit Maven:
      ```bash
      mvn clean install
      ```
```
```
## Verwendung

### GUI-Anwendung

1. **Starten der Anwendung**:
    - Führen Sie die Hauptklasse `app.AppLauncher` aus:
      ```bash
      mvn exec:java -Dexec.mainClass="app.AppLauncher"
      ```
    - Dies öffnet das Hauptfenster der Wetter-App-GUI, in dem Sie den Standort eingeben können, um Wetterdaten abzurufen.

2. **Benutzeroberfläche**:
    - Geben Sie den Namen der Stadt in das Suchfeld ein und klicken Sie auf den Suchbutton oder drücken Sie Enter, um die aktuellen Wetterdaten für diesen Standort abzurufen.
    - Die Wetterinformationen werden im GUI-Fenster angezeigt.
   - **Standortsuche**: Wenn Sie am Anfang auf das Globus-Symbol klicken, sucht die App automatisch nach Ihrem "aktuellen Standort" (
    Hier ist zu beachten, dass die Kostenlose Standort API nicht genau funktioniert!
    In anderen Worten ist der angegebene Standort nicht genau der Standort des Nutzers.
    Um das Problem zu beheben, könnte man auf eine nicht kostenfreie API umsteigen.
    Doch darauf wollte ich mit meinem Studenten Lohn darauf verzichten.)
       und zeigt die entsprechenden Wetterdaten an.
   - Außerdem wird zur Tageszeit eine Sonne und zur Nachtzeit ein Mond angezeigt. (z.B. Freiburg und Tokyo)
```
```
### Chatbot-Anwendung

1. **Starten des Chatbots**:
    - Führen Sie die Hauptklasse `app.WeatherChatBot` aus:
      ```bash
      mvn exec:java -Dexec.mainClass="app.WeatherChatBot"
      ```
    - Dies öffnet das Chatbot-Fenster, in dem der Benutzer nach seinem Standort und den gewünschten Wetterinformationen gefragt wird.
    - Eben so kann durch Klicken des Roboters unten rechts der Chatbot geöffnet werden.

2. **Chatbot-Bedienung**:
    - Geben Sie den Standort ein, wenn der Chatbot danach fragt.
    - Beantworten Sie die Fragen des Chatbots mit "ja" oder "nein", um die gewünschten Wetterinformationen zu erhalten.
```
```
## Tests

1. **Alle Tests ausführen**:
    - Führen Sie die Tests mit Maven aus:
      ```bash
      mvn test
      ```
    - Dies führt alle Unit-Tests aus und zeigt die Ergebnisse an.

2. **Einzelne Tests ausführen**:
    - Sie können auch einzelne Testklassen ausführen. Beispielsweise:
      ```bash
      mvn -Dtest=TestWeatherApp test
      ```
```
```
## Technologien

- **Java**: Programmiersprache
- **Swing**: GUI-Toolkit für Java
- **JSON Simple**: Bibliothek zum Parsen und Erzeugen von JSON-Daten
- **JUnit**: Test-Framework für Java
- **Maven**: Build-Management-Tool
```
```
## Projektstruktur

- `AppLauncher.java`: Der Einstiegspunkt der Anwendung.
- `WeatherApp.java`: Enthält Methoden zum Abrufen und Verarbeiten von Wetterdaten von einer API.
- `WeatherAppGui.java`: Die Hauptgrafikoberfläche für die Wetteranwendung.
- `WeatherChatBot.java`: Ein einfacher Chatbot, der mit dem Benutzer interagiert, um Wetterinformationen bereitzustellen.
- `WeatherComponent.java`: Eine abstrakte Klasse für wetterbezogene GUI-Komponenten.
- `WeatherImageComponent.java`: Eine Klasse, die `WeatherComponent` erweitert, um Bildkomponenten zu behandeln.
- `WeatherTextComponent.java`: Eine Klasse, die `WeatherComponent` erweitert, um Textkomponenten zu behandeln.
```
```
## Anforderungen und Beispiele

### UML-Klassendiagramm
- [x] **UML-Klassendiagramm erstellt**
    - Finden Sie als JavaWeatherAppUML.drawio
  
    
### Namenskonventionen
- [x] **Klassen-, Methoden- und Variablennamen folgen den Java-Namenskonventionen**
  ```java
  public class WeatherAppGui {
      private JTextField searchTextField;
      private JLabel weatherConditionImage;
      // ...
  }
  ```

### Schleifen (for, while, do while)
- [x] **Verwendung von Schleifen (for, while, do while) in den Klassen**
  ```java
  for (int i = 0; i < 10; i++) {
      System.out.println("Index: " + i);
  }
  ```

### Bedingte Anweisungen (If-Else, Case)
- [x] **Verwendung von bedingten Anweisungen (if-else, switch-case) in den Klassen**
  ```java
  if (weatherData != null) {
      String temperature = String.format("%.1f°C", (double) weatherData.get("temperature"));
      // ...
  } else {
      System.out.println("Keine Wetterdaten verfügbar.");
  }
  ```

### Einsatz komplexer Datenstrukturen (z.B. multidimensionale Arrays, etc.)
- [x] **Einsatz komplexer Datenstrukturen wie Listen und JSON-Objekten (z.B. in `WeatherApp`)**
  ```java
  JSONArray locationData = new JSONArray();
  JSONObject location = new JSONObject();
  location.put("latitude", 52.5200);
  location.put("longitude", 13.4050);
  locationData.add(location);
  ```

### Klassen
- [x] **Verwendung von Klassen zur Strukturierung des Codes**
  ```java
  public class WeatherAppGui extends JFrame {
      // ...
  }
  ```

### Zugriffskontrolle (private, protected, public)
- [x] **Verwendung von Zugriffskontrollen (private, protected, public)**
  ```java
  public class WeatherAppGui {
      private JTextField searchTextField;
      protected void initializeComponents() {
          // ...
      }
      public static void main(String[] args) {
          // ...
      }
  }
  ```

### Vererbung
- [x] **Verwendung von Vererbung (z.B. `WeatherImageComponent` und `WeatherTextComponent` erben von `WeatherComponent`)**
  ```java
  public abstract class WeatherComponent {
      protected JLabel component;
      public abstract void initialize();
      public JLabel getComponent() {
          return component;
      }
  }

  public class WeatherImageComponent extends WeatherComponent {
      @Override
      public void initialize() {
          // ...
      }
  }
  ```

### Abstrakte Klassen, bzw. Interfaces
- [x] **Verwendung von abstrakten Klassen (z.B. `WeatherComponent`)**
  ```java
  public abstract class WeatherComponent {
      protected JLabel component;
      public abstract void initialize();
  }
  ```

### Paketstruktur
- [x] **Verwendung einer Paketstruktur (z.B. `package app;`)**
  ```java
  package app;
  public class WeatherAppGui {
      // ...
  }
  ```

### Exception Handling
- [x] **Verwendung von Exception Handling (try-catch Blöcke in verschiedenen Methoden)**
  ```java
  private String getUserLocation() {
      try {
          String apiUrl = "http://ip-api.com/json";
          HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
          connection.setRequestMethod("GET");
          connection.connect();
          // ...
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }
  ```

### Javadoc Dokumentation
- [x] **Verwendung von Javadoc zur Dokumentation der Klassen und Methoden**
  ```java
  /**
   * Holt den Standort des Benutzers basierend auf seiner IP-Adresse.
   *
   * @return der Standort des Benutzers als String
   */
  private String getUserLocation() {
      // ...
  }
  ```

### Unit-Tests (z.B. mit JUnit, TestNG)
- [x] **Erstellung von Unit-Tests mit JUnit (Testklassen wurden bereitgestellt)**
  ```java
  public class WeatherAppTest {
      @Test
      public void testGetWeatherData() {
          JSONObject weatherData = WeatherApp.getWeatherData("Berlin");
          assertNotNull(weatherData);
          assertTrue(weatherData.containsKey("temperature"));
          // ...
      }
  }
  ```

### Build

-Tools (Ant, Maven, Gradle)
- [x] **Verwendung von Maven als Build-Tool (pom.xml bereitgestellt)**
  ```xml
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.8.1</version>
      <scope>test</scope>
  </dependency>
  ```

### Einbindung externer Java Bibliotheken
- [x] **Einbindung externer Bibliotheken (z.B. JSON Simple in der pom.xml)**
  ```xml
  <dependency>
      <groupId>com.googlecode.json-simple</groupId>
      <artifactId>json-simple</artifactId>
      <version>1.1.1</version>
  </dependency>
  ```
```
```
## Anwendung ausführen

Um die Anwendung auszuführen, verwenden Sie den folgenden Befehl:

```sh
mvn clean install
mvn exec:java -Dexec.mainClass="app.AppLauncher"
```
```
```
## Tests ausführen

Um die Tests auszuführen, verwenden Sie den folgenden Befehl:

```sh
mvn test
```

Dies wird das Projekt kompilieren und alle Tests im Verzeichnis `src/test/java` ausführen.
```
```
## Fazit

Alle Anforderungen für dieses Projekt wurden erfüllt, einschließlich der Erstellung eines UML-Klassendiagramms, der Einhaltung von Namenskonventionen, der Verwendung von Schleifen, bedingten Anweisungen, komplexen Datenstrukturen, Klassen, Zugriffskontrollen, Vererbung, abstrakten Klassen, Paketstrukturen, Exception Handling, Javadoc-Dokumentation, Unit-Tests, Build-Tools und externen Java-Bibliotheken.

Es ist erwähnenswert, dass wenn Timo Völker und Brandon Evans an diesem Projekt mitgewirkt hätten, wir möglicherweise einen verbesserten Chatbot mit KI-Fähigkeiten sowie eine zusätzliche Seite mit Wettervorhersagen, Statistiken und weiteren Informationen hätten implementieren können. Leider war dies in dieser Iteration nicht umsetzbar. Dennoch bin ich mit dem erreichten Ergebnis zufrieden und hoffe, dass es Ihren Erwartungen entspricht.
```
```
## Autoren

- Nico Röcker
```
```