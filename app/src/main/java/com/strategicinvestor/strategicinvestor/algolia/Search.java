package com.strategicinvestor.strategicinvestor.algolia;

import android.util.Log;
import android.view.View;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.strategicinvestor.strategicinvestor.object.Company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Search {

    public static ArrayList<Company> algoliaQuery(String search) {
        ArrayList<Company> allCompany = new ArrayList<>();
        Client client = new Client("91WCKSBFTE", "29ddfa0c991845808986cf2d56cb93c2");
        Index index = client.getIndex("company_symbols");

        CompletionHandler completionHandler = new CompletionHandler() {
            @Override
            public void requestCompleted(JSONObject jsonObject, AlgoliaException e) {
                try {
                    JSONArray hitsArray = jsonObject.getJSONArray("hits");
                    for (int i = 0; i < hitsArray.length(); i++)
                    {
                        JSONObject temp = hitsArray.getJSONObject(i);
                        String name = temp.getString("Company Name");
                        String tick = temp.getString("Symbol");
                        Company tempCompany = new Company(name, tick, 5, false);
                        allCompany.add(tempCompany);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        index.searchAsync(new Query(search), completionHandler);

        return allCompany;
    }
}
