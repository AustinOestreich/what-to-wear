package bloc.whattowear;

import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;

import bloc.whattowear.model.WeatherModel;
import bloc.whattowear.network.APIRequest;
import bloc.whattowear.network.JSONParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView temp = (TextView) findViewById(R.id.tempText);
        NetworkRequest request = new NetworkRequest();
        request.execute(new String[]{"Hopkins"})
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class NetworkRequest extends AsyncTask<String, Void, String>{

        @Override
        protected WeatherModel doInBackground(String... params) {
            WeatherModel weather = new WeatherModel();
            String data = ((new APIRequest()).getWeatherData(params[0]));

            try{
                weather = JSONParser.getWeather(data);
            }catch(JSONException e){
                e.printStackTrace();
            }
            return weather;
        }
    }

}
