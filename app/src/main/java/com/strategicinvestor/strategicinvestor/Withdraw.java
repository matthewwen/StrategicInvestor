package com.strategicinvestor.strategicinvestor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.SaveCallback;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.strategicinvestor.strategicinvestor.fragment.PersonalFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Withdraw extends AppCompatActivity{

//    private static final String TAG = "AutoLogger";
//    private static final int RECORD_REQUEST_CODE = 101;
//    private static final int CAMERA_REQUEST_CODE = 102;
//
//    public String brand_name = "Lays";
//
//    private static final String CLOUD_VISION_API_KEY = "AIzaSyAAO5TG773UHzQQG3HROVm9L12U2pDo5dg";
//
//    private Feature feature;
//    private Bitmap bitmap;
//    private String[] visionAPI = new String[]{"LOGO_DETECTION"};
//
//    private String api = visionAPI[0];
//
//    Button btnCamera = findViewById(R.id.btnCamera);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

//        btnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                takePictureFromCamera();
//                System.out.println(brand_name);
//            }
//        });

        EditText balance_view = findViewById(R.id.editText);

        Button btnWithdraw = findViewById(R.id.btnWithdraw);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalFragment.balance -= Integer.parseInt(balance_view.getText().toString());
                Withdraw.super.onBackPressed();
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (checkPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            btnCamera.setVisibility(View.VISIBLE);
//        } else {
//            btnCamera.setVisibility(View.INVISIBLE);
//            makeRequest(Manifest.permission.CAMERA);
//        }
//    }
//
//    private int checkPermission(String permission) {
//        return ContextCompat.checkSelfPermission(this, permission);
//    }
//
//    private void makeRequest(String permission) {
//        ActivityCompat.requestPermissions(this, new String[]{permission}, RECORD_REQUEST_CODE);
//    }
//
//    public void takePictureFromCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//            bitmap = (Bitmap) data.getExtras().get("data");
//            callCloudVision(bitmap, feature);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == RECORD_REQUEST_CODE) {
//            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                finish();
//            } else {
//                btnCamera.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//
//    private void callCloudVision(final Bitmap bitmap, final Feature feature) {
//        final List<Feature> featureList = new ArrayList<>();
//        featureList.add(feature);
//
//        final List<AnnotateImageRequest> annotateImageRequests = new ArrayList<>();
//
//        AnnotateImageRequest annotateImageReq = new AnnotateImageRequest();
//        annotateImageReq.setFeatures(featureList);
//        annotateImageReq.setImage(getImageEncodeImage(bitmap));
//        annotateImageRequests.add(annotateImageReq);
//
//
//        new AsyncTask<Object, Void, String>() {
//            @Override
//            protected String doInBackground(Object... params) {
//                try {
//
//                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
//                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
//
//                    VisionRequestInitializer requestInitializer = new VisionRequestInitializer(CLOUD_VISION_API_KEY);
//
//                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
//                    builder.setVisionRequestInitializer(requestInitializer);
//
//                    Vision vision = builder.build();
//
//                    BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
//                    batchAnnotateImagesRequest.setRequests(annotateImageRequests);
//
//                    Vision.Images.Annotate annotateRequest = vision.images().annotate(batchAnnotateImagesRequest);
//                    annotateRequest.setDisableGZipContent(true);
//                    BatchAnnotateImagesResponse response = annotateRequest.execute();
//                    return convertResponseToString(response);
//                } catch (GoogleJsonResponseException e) {
//                    Log.d(TAG, "failed to make API request because " + e.getContent());
//                } catch (IOException e) {
//                    Log.d(TAG, "failed to make API request because of other IOException " + e.getMessage());
//                }
//                return "Cloud Vision API request failed. Check logs for details.";
//            }
//
//            protected void onPostExecute(String result) {
//                brand_name = result;
//            }
//        }.execute();
//    }
//
//
//    @NonNull
//    private Image getImageEncodeImage(Bitmap bitmap) {
//        Image base64EncodedImage = new Image();
//        // Convert the bitmap to a JPEG
//        // Just in case it's a format that Android understands but Cloud Vision
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
//        byte[] imageBytes = byteArrayOutputStream.toByteArray();
//
//        // Base64 encode the JPEG
//        base64EncodedImage.encodeContent(imageBytes);
//        return base64EncodedImage;
//    }
//
//    private String convertResponseToString(BatchAnnotateImagesResponse response) {
//
//        AnnotateImageResponse imageResponses = response.getResponses().get(0);
//
//        List<EntityAnnotation> entityAnnotations;
//
//        String message = "";
//        switch (api) {
//            case "LANDMARK_DETECTION":
//                entityAnnotations = imageResponses.getLandmarkAnnotations();
//                message = formatAnnotation(entityAnnotations);
//                break;
//            case "LOGO_DETECTION":
//                entityAnnotations = imageResponses.getLogoAnnotations();
//                message = formatAnnotation(entityAnnotations);
//                break;
//            case "SAFE_SEARCH_DETECTION":
//                SafeSearchAnnotation annotation = imageResponses.getSafeSearchAnnotation();
//                message = getImageAnnotation(annotation);
//                break;
//            case "IMAGE_PROPERTIES":
//                ImageProperties imageProperties = imageResponses.getImagePropertiesAnnotation();
//                message = getImageProperty(imageProperties);
//                break;
//            case "LABEL_DETECTION":
//                entityAnnotations = imageResponses.getLabelAnnotations();
//                message = formatAnnotation(entityAnnotations);
//                break;
//        }
//        return message;
//    }
//
//    private String getImageAnnotation(SafeSearchAnnotation annotation) {
//        return String.format("adult: %s\nmedical: %s\nspoofed: %s\nviolence: %s\n",
//                annotation.getAdult(),
//                annotation.getMedical(),
//                annotation.getSpoof(),
//                annotation.getViolence());
//    }
//
//    private String getImageProperty(ImageProperties imageProperties) {
//        String message = "";
//        DominantColorsAnnotation colors = imageProperties.getDominantColors();
//        for (ColorInfo color : colors.getColors()) {
//            message = message + "" + color.getPixelFraction() + " - " + color.getColor().getRed() + " - " + color.getColor().getGreen() + " - " + color.getColor().getBlue();
//            message = message + "\n";
//        }
//        return message;
//    }
//
//    private String formatAnnotation(List<EntityAnnotation> entityAnnotation) {
//        String message = "";
//
//        if (entityAnnotation != null) {
//            for (EntityAnnotation entity : entityAnnotation) {
//                message = message + "    " + entity.getDescription() + " " + entity.getScore();
//                message += "\n";
//            }
//        } else {
//            message = "Nothing Found";
//        }
//        return message;
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        api = (String) adapterView.getItemAtPosition(i);
//        feature.setType(api);
//        if (bitmap != null)
//            callCloudVision(bitmap, feature);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//    }
}
