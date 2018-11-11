package com.strategicinvestor.strategicinvestor.algolia;

import android.util.Log;
import android.view.View;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.strategicinvestor.strategicinvestor.fragment.CompaniesFragment;
import com.strategicinvestor.strategicinvestor.object.Company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Search {

    public static void algoliaQuery(String search, Listener listener) {
        ArrayList<Company> allComp = new ArrayList<>();
        Client client = new Client("91WCKSBFTE", "29ddfa0c991845808986cf2d56cb93c2");
        Index index = client.getIndex("company_symbols");

        CompletionHandler completionHandler = (jsonObject, e) -> {
            try {
                JSONArray hitsArray = jsonObject.getJSONArray("hits");
                for (int i = 0; i < hitsArray.length(); i++)
                {
                    JSONObject temp = hitsArray.getJSONObject(i);
                    String name = temp.getString("Company Name");
                    String tick = temp.getString("Symbol");
                    Log.v(Search.class.getSimpleName(), "Name: " + name);
                    Company tempCompany = new Company(name, tick, 5, CompaniesFragment.haveTick(tick));
                    allComp.add(tempCompany);
                }
                listener.onResultRecieve(allComp);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        };

        index.searchAsync(new Query(search), completionHandler);

    }

    public interface Listener{
         void onResultRecieve(ArrayList<Company> allCompany);
    }
}
