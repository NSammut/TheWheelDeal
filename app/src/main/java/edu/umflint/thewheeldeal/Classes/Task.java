package edu.umflint.thewheeldeal.Classes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.umflint.thewheeldeal.Main2Activity;


public class Task extends AsyncTask<String, Integer, String> {

    Context context;
    ProgressBar progressBar;
    private Vehicle vehicle = new Vehicle(new JSONObject());

    public Task(Context mContext)
    {
        this.context = mContext;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setMax(5);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(1);
    }

    protected String doInBackground(String... arg0) {
        publishProgress(2);
        String image = encodeImage(BitmapFactory.decodeFile(arg0[0]));
        JSONObject jsonResponse = null;

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
            os.write(image.getBytes("UTF-8"));
            os.close();
            publishProgress(3);
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String response = "";

            for (int c; (c = in.read()) >= 0; )
                response += (char) c;
            publishProgress(4);
            Log.i("Response", response);
            jsonResponse = new JSONObject(response);

            vehicle = new Vehicle(jsonResponse);

        } catch(Exception ex)
        {
            Log.e("Task", ex.toString());
        }
        publishProgress(5);
        return "Task Completed.";
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        if (vehicle.getColor() == null || vehicle.getMake() == null || vehicle.getModel() == null) {
            builder1.setMessage("Car not found! Please try again.");
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            builder1.setMessage("Make : " + vehicle.getMake() + "\n" +
                    "Model : " + vehicle.getModel() + "\n" +
                    "Color : " + vehicle.getColor());
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if(this.progressBar != null)
            progressBar.setProgress(values[0]);
    }

    private String encodeImage(Bitmap bm)
    {
        Log.i("Camera", "Encoding Image!");
        System.gc();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
}