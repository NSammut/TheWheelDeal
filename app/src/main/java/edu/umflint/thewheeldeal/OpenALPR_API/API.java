package edu.umflint.thewheeldeal.OpenALPR_API;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NSammut on 3/22/2017.
 */

public class API extends AsyncTask<String, Void, Vehicle> {

    @Override
    protected Vehicle doInBackground(String... params) {

        JSONObject jsonResponse = null;
        Vehicle v = new Vehicle(new JSONObject());
        try {

            String url = "https://api.openalpr.com/v2/recognize_bytes?secret_key=sk_89170dc75d7a35cf2964ab81&recognize_vehicle=1&country=us&return_image=0&topn=1";

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            //Set Request To POST
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(params[0].toString().getBytes("UTF-8"));
            os.close();

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String response = "";

            for (int c; (c = in.read()) >= 0;)
                response += (char)c;

            jsonResponse = new JSONObject(response);

            v = new Vehicle(jsonResponse);
        } catch (Exception ex) {
            Log.e("API Call", ex.toString());
        }
        return v;
    }
}