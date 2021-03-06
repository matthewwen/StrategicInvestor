package com.strategicinvestor.strategicinvestor;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.strategicinvestor.strategicinvestor.fragment.PersonalFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class Deposit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        EditText balance_view = findViewById(R.id.editText);

        Button btnDeposit = findViewById(R.id.btnDeposit);
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalFragment.balance += Integer.parseInt(balance_view.getText().toString());
                Deposit.super.onBackPressed();
            }
        } );
    }

    public class sendJSONDataToServer extends AsyncTask<String, Void, String>
    {
        JSONObject jPaymentDetails = storeDataInJson();
        String json = jPaymentDetails.toString();

        String statusOp;
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL("https://apitest.authorize.net/xml/v1/request.api");
                HttpURLConnection con = null;
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                Writer writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
                writer.write(json);
                Log.i("JSON String",json);
                writer.close();

                InputStream inputStream = con.getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = null;
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");

//                JsonResponse = buffer.toString();
//                Log.i("JSON Response",JsonResponse);
//                storeJSONDataInDB(JsonResponse);
                con.disconnect();
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public void storeJSONDataInDB(String JsonResponse)
    {
        String desc =null;
        try {
            JSONObject  jsonObj = new JSONObject(JsonResponse);

            JSONArray jsonStatusArray= jsonObj.getJSONObject("messages").getJSONArray("message");




            for (int i = 0; i < jsonStatusArray.length(); ++i) {
                JSONObject rec = jsonStatusArray.getJSONObject(i);
//                returnCode = rec.getString("code");
//                status = rec.getString("text");
//                Log.i("code",returnCode);
//                Log.i("status",status);
            }

//            Log.i("returnCode",returnCode);
//            if(returnCode.matches("I00001")) {
//                Log.i("Parse Server","store in bitnami");

//                ParseObject obj = new ParseObject("TransactionDetails");
//                obj.put("transId", jsonObj.getJSONObject("transactionResponse").getString("transId"));
//                obj.put("accountNumber", jsonObj.getJSONObject("transactionResponse").getString("accountNumber"));
//                obj.put("accountType", jsonObj.getJSONObject("transactionResponse").getString("accountType"));
//                obj.put("refId", jsonObj.getString("refId"));
//                obj.put("status", status);

//                obj.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            Log.i("Parse Result", "Successful");
//                        } else {
//                            Log.i("Parse Result", "Failed");
//                        }
//                    }
//                });
//            }
            if(jsonObj.getJSONObject("transactionResponse").has("messages")) {
                Log.i("Inside","transactionResponse");
                Log.i("Inside","messages");

                desc = jsonObj.getJSONObject("transactionResponse").getJSONArray("messages").getJSONObject(0).getString("description");
//                status = status + desc;
            }
            else
            {
                Log.i("Inside","errorText");
                desc = jsonObj.getJSONObject("transactionResponse").getJSONArray("errors").getJSONObject(0).getString("errorText");
//                status = status + desc;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject storeDataInJson()
    {
//        String CardNumber = _cardNumber.getText().toString().trim();
//        String ExpiratonDate = _expDate.getText().toString().trim();
//        String CVV = _cardCode.getText().toString().trim();
//        String Amount = _amount.getText().toString().trim();

        JSONObject jfinal = new JSONObject();
        JSONObject jkeys =new JSONObject();
        JSONObject jCreditCardTrans = new JSONObject();

        JSONObject jTransactionReq = new JSONObject();
        JSONObject jInfo = new JSONObject();
        JSONObject jLine = new JSONObject();
        JSONObject jObjectType = new JSONObject();
        JSONObject jObjectItem = new JSONObject();
        JSONObject jObjectTax = new JSONObject();
        JSONObject jObjectduty = new JSONObject();

        try {
            jkeys.put("name","7vz8xGg3D7j");
            jkeys.put("transactionKey","4brhB8ukY2R8n98W");
            jCreditCardTrans.put("merchantAuthentication",jkeys);
            jCreditCardTrans.put("refId","123456");
            jTransactionReq.put("transactionType" ,"authCaptureTransaction");
            jTransactionReq.put("amount","5");
//            jObjectType.put("cardNumber",CardNumber);
//            jObjectType.put("expirationDate",ExpiratonDate);
//            jObjectType.put("cardCode",CVV);
            jInfo.put("creditCard",jObjectType);
            jTransactionReq.put("payment",jInfo);
            jObjectItem.put("itemId",1);
            jObjectItem.put("name","vase");
            jObjectItem.put("description","pink color");
            jObjectItem.put("quantity","18");
//            jObjectItem.put("unitPrice",Amount);
            jLine.put("lineItem",jObjectItem);
            jTransactionReq.put("lineItems",jLine);
            jObjectTax.put("amount","4.26");
            jObjectTax.put("name","level2 tax name");
            jObjectTax.put("description","level2 tax");
            jTransactionReq.put("tax",jObjectTax);
            jObjectduty.put("amount","8.55");
            jObjectduty.put("name","duty name");
            jObjectduty.put("description","duty description");
            jTransactionReq.put("duty",jObjectduty);
            jCreditCardTrans.put("transactionRequest",jTransactionReq);
            jfinal.put("createTransactionRequest",jCreditCardTrans);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("JSON String",jfinal.toString());
        return jfinal;
    }
}
