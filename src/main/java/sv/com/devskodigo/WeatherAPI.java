package sv.com.devskodigo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class WeatherAPI {

    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
        return map;
    }

    public String getForecast() {
        String forecast;
        String API_KEY = "3d533f0959fa1f766ffbd587fccfbaca";
        String LOCATION = "La%20Paz,sv";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+API_KEY+"&units=metric";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new  BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null){
                result.append(line);
            }

            rd.close();

            Map<String, Object> resMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(resMap.get("main").toString());

            //System.out.println("\nAirport Oscar Arnulfo Romero - SLV: \n");
            //System.out.println("Current Temperature: "+ mainMap.get("temp")+ "ºC");
            //System.out.println("Current Humidity: "+ mainMap.get("humidity")+ "%");

            forecast = mainMap.get("temp").toString()+"°C, "+ mainMap.get("humidity")+"%";
            System.out.println(forecast);
            return forecast;

        }catch(Exception error){
            System.out.println("error.getMessage() = " + error.getMessage());
            return "Error";
        }
    }
    /*
    *
     * User
     * Flight
     * Aircraft
    * Airline
    *
    * ReportDetail
    * ReportSummary
     * City
     * Country
    * */
}
