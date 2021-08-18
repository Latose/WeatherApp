package sample;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button getData;

    @FXML
    private Text info;

    @FXML
    private Text weather;

    @FXML
    private TextField city;

    @FXML
    private Text temp;

    @FXML
    private Text temp_feel;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_pressure;

    @FXML
    void initialize() {
      getData.setOnAction(actionEvent -> {
          String getUserCity = city.getText().trim();
          if (!getUserCity.equals("")) {
              String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + ",ros&APPID=1f6a10932cb7feb2f35eab4ab56fb4b2");
              if (!output.isEmpty()) {
                  JSONObject obj = new JSONObject(output);
                  temp.setText("Температура : " + obj.getJSONObject("main").getDouble("temp") + "°k");
                  temp_feel.setText("Ощущается : " + obj.getJSONObject("main").getDouble("feels_like") + "°k");
                  temp_max.setText("Максимум : " + obj.getJSONObject("main").getDouble("temp_max")+ "°k");
                  temp_min.setText("Минимум : " + obj.getJSONObject("main").getDouble("temp_min") + "°k");
                  temp_pressure.setText("Давление : " + obj.getJSONObject("main").getDouble("pressure")+ " pa");
              }
          }
      });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такого города нет");
        }
        return content.toString();
    }
}

