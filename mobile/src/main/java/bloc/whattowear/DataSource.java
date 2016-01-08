package bloc.whattowear;

import bloc.whattowear.model.WeatherModel;

/**
 * Created by Austin on 1/7/2016.
 */
public class DataSource {

    private WeatherModel weatherModel;

    public DataSource(){
        testData();
    }

    public WeatherModel getWeatherModel(){
        return weatherModel;
    }

    void testData(){
        weatherModel = new WeatherModel("26");
    }

}
