package com.strategicinvestor.strategicinvestor.intrinio;

import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PriceIntrinio {

    private static final String BASE_URL = "https://api.intrinio.com/prices?";
    private static final String AUTHENTICATION = "api_key";
    private static final String IDENTIFIER = "identifier";
    private static final String FREQUENCY = "frequency";
    private static final String KEY = "OmRlNzE3N2M4NjExY2M5Zjg2MmI5YmI5MjljYTY4Mzc2";
    private static final String START_DATE = "start_date";

    private static final String TAG = PriceIntrinio.class.getSimpleName();

    public static ArrayList<Double> fetchPrice90(String ticker){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        long threeMonth = TimeUnit.DAYS.toMillis(90);

        Uri uri = Uri.parse(BASE_URL);
        Uri.Builder builder = uri.buildUpon();
        builder.appendQueryParameter(START_DATE, format.format(new Date(Calendar.getInstance().getTimeInMillis() - threeMonth)));
        builder.appendQueryParameter(IDENTIFIER, ticker);
        builder.appendQueryParameter(FREQUENCY, "daily");
        builder.appendQueryParameter(AUTHENTICATION, KEY);

        URL url = createURL(builder.toString());

        String jsonData;
        try {
            jsonData = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonData = "";
        }
        return extractPrice(jsonData);
    }

    public static ArrayList<Double> fetchOther(String ticker){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Uri uri = Uri.parse(BASE_URL);
        Uri.Builder builder = uri.buildUpon();
        builder.appendQueryParameter(START_DATE, format.format(Calendar.getInstance().getTimeInMillis() - TimeUnit.DAYS.toMillis(5)));
        builder.appendQueryParameter(IDENTIFIER, ticker);
        builder.appendQueryParameter(FREQUENCY, "daily");
        builder.appendQueryParameter(AUTHENTICATION, KEY);

        Log.v("The URL", builder.toString());

        URL url = createURL(builder.toString());

        String jsonData;
        try {
            jsonData = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonData = "";
        }
        return extractOther(jsonData);

    }

    public static double fetchPrice1(String ticker){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Uri uri = Uri.parse(BASE_URL);
        Uri.Builder builder = uri.buildUpon();
        builder.appendQueryParameter(START_DATE, format.format(Calendar.getInstance().getTimeInMillis() - TimeUnit.DAYS.toMillis(5)));
        builder.appendQueryParameter(IDENTIFIER, ticker);
        builder.appendQueryParameter(FREQUENCY, "daily");
        builder.appendQueryParameter(AUTHENTICATION, KEY);

        Log.v("The URL", builder.toString());

        URL url = createURL(builder.toString());

        String jsonData;
        try {
            jsonData = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonData = "";
        }
        ArrayList<Double> allVal = extractPrice(jsonData);

        if (allVal.size() > 0)
        {
            return allVal.get(0);
        }
        return 0;
    }

    private static ArrayList<Double> extractOther(String jsonResponse){
        ArrayList<Double> allPrice = new ArrayList<>();
        try {
            JSONObject mainObject = new JSONObject(jsonResponse);
            JSONArray dataArray = mainObject.getJSONArray("data");
            for (int i = 0; i < 1; i++)
            {
                JSONObject temp = dataArray.getJSONObject(i);
                allPrice.add(temp.getDouble("open"));
                allPrice.add(temp.getDouble("high"));
                allPrice.add(temp.getDouble("low"));
                allPrice.add(temp.getDouble("close"));
                allPrice.add(temp.getDouble("volume"));
                allPrice.add(temp.getDouble("ex_dividend"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allPrice;
    }

    private static ArrayList<Double> extractPrice(String jsonResponse){
        ArrayList<Double> allPrice = new ArrayList<>();
        try {
            JSONObject mainObject = new JSONObject(jsonResponse);
            JSONArray dataArray = mainObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++)
            {
                JSONObject temp = dataArray.getJSONObject(i);
                allPrice.add(temp.getDouble("close"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allPrice;
    }


    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.v(TAG, "response code: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else {
                Log.e(TAG, "Connection to url failed");
            }
        }catch (IOException e){
            Log.e(TAG, "IOException e is made");
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createURL(String urlString){
        URL url;
        try{
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        return url;
    }
}
