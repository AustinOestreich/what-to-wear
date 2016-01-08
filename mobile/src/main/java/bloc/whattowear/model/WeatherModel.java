package bloc.whattowear.model;

/**
 * Created by Austin on 1/7/2016.
 */
public class WeatherModel {
    private String temperature;
    private Main main;

    public WeatherModel() {
    }

    public Main getMain(){
        return main;
    }

    public class Main{

        private double temp;

        public double getTemp() {
            return temp;
        }

    }

}

