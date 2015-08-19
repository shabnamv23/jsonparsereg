package com.example.administrator.jsonparsereg;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.app.Activity;
        import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we will using AsyncTask during parsing
        new AsyncTaskParseJson().execute();
    }

    // you can make this class as another java file so it will be separated from your main activity.
    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        String yourJsonStringUrl = "http://demo.codeofaninja.com/tutorials/json-example-with-php/index.php";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
              Log.d("shabu", "shabu"+ json.toString());
                // get the array of users
                dataJsonArr = json.getJSONArray("Users");
                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String firstname = c.getString("firstname");
                    String lastname = c.getString("lastname");
                    String username = c.getString("username");

                    // show the values in our logcat
                    Log.e("shabu", "firstname: " + firstname
                            + ", lastname: " + lastname
                            + ", username: " + username);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {



        }
    }
}