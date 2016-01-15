package bloc.whattowear.network;

import com.google.gson.Gson;

import org.json.JSONException;

import bloc.whattowear.model.WeatherModel;

/**
 * Created by Austin on 1/7/2016.
 */
public class JSONParser {

    public static WeatherModel getWeather(String jsonString) throws JSONException {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, WeatherModel.class);
    }


}

