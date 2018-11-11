package com.strategicinvestor.strategicinvestor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCompany extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
    }

    public Integer autocomplete_idx = 0;
    public String autocomplete_str = "AAPL";

    protected void algoliaQuery() {
        Client client = new Client("91WCKSBFTE", "29ddfa0c991845808986cf2d56cb93c2");
        Index index = client.getIndex("company_symbols");

        CompletionHandler completionHandler = new CompletionHandler() {
            @Override
            public void requestCompleted(JSONObject content, AlgoliaException error) {

                try {
                    autocomplete_idx = content.getJSONArray("hits").getJSONObject(0).getJSONObject("_highlightResult").getJSONArray("fruits").toString().split("full")[0].split("value").length;
                    autocomplete_str = content.getJSONArray("hits").getJSONObject(0).getJSONArray("fruits").toString().split("\",\"")[autocomplete_idx-2];
                    if(autocomplete_str.substring(0,1).equals("[")) {
                        autoField.setText("AAPL");
                    }
                    else {
                        autoField.setText(autocomplete_str);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        index.searchAsync(new Query(autoField.getText()), completionHandler);
    }
}
