package bloc.whattowear.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Austin on 1/7/2016.
 */
public class APIRequest {


    private static String urlString = "http://api.openweathermap.org/data/2.5/weather?q=Hopkins,%20us&appid=2de143494c0b295cca9337e1e96b00e0";

    public String getWeatherData(String location){
        HttpURLConnection connection = null;
        InputStream inputstream = null;

        try{
            //get request
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //reading response
            StringBuffer buffer = new StringBuffer();
            inputstream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));
            String line = null;
            while((line = br.readLine()) != null){
                buffer.append(line + "\r\n");
            }
            inputstream.close();
            connection.disconnect();
            return buffer.toString();

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

