package bloc.whattowear;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;

import bloc.whattowear.model.WeatherModel;
import bloc.whattowear.network.APIRequest;
import bloc.whattowear.network.JSONParser;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Austin on 1/7/2016.
 */
public class DataSource implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static Context context;
    private WeatherModel weatherModel;
    public Location location;

    public DataSource() {
        getLocation();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public interface WeatherDataCallback {
        void success(WeatherModel model);

        void fail();
    }


    public static class RequestWeatherData extends AsyncTask<String, String, WeatherModel> {
        WeatherDataCallback callback;


        public RequestWeatherData(WeatherDataCallback weatherDataCallback) {
            this.callback = weatherDataCallback;
        }

        @Override
        protected WeatherModel doInBackground(String... params) {
            WeatherModel weather = new WeatherModel();
            String data = ((new APIRequest()).getWeatherData(params[0]));

            try {
                weather = JSONParser.getWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        protected void onPostExecute(WeatherModel model) {
            callback.success(model);
        }


    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void getWeatherData(WeatherDataCallback weatherDataCallback, String location) {
        new RequestWeatherData(weatherDataCallback).execute(location);
    }

    private Location getLocation() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        location = lastLocation;
        return lastLocation;

    }

}
