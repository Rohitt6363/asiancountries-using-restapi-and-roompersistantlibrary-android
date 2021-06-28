package com.android.rohitt.asiancountriesinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ImageView search_button;
    EditText search_bar;
    Shelf shelf;
    ImageView delete;
    public static RecyclerView mRecyclerView;
    public static CountryAdapter countryAdapter;
    public static Context fcontext;
    public static CountryAdapterOffline countryAdapterOffline;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_bar = findViewById(R.id.searchbar);
        delete = findViewById(R.id.delete);
        search_button = findViewById(R.id.searchbutton);
        mRecyclerView = findViewById(R.id.recyclerview);
        fcontext = getApplicationContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        shelf = new Shelf();
        ConnectivityManager cm =
                (ConnectivityManager) fcontext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Log.d("RRohit", String.valueOf(isConnected));
        if(isConnected){
            load_data();
        }
        else{
            ArrayList<Country> allcountries = shelf.fetch(fcontext);

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shelf.deleteAll();
                Toast.makeText(fcontext, "local data deleted", Toast.LENGTH_SHORT).show();
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                String name = search_bar.getText().toString();
                if(isConnected){
                    i = countryAdapter.getPosition(name);
                }
                else{
                    i = countryAdapterOffline.getPosition(name);
                }
                if(i == -1){
                    Toast.makeText(fcontext, "country not found", Toast.LENGTH_SHORT).show();
                }
                else{
                    mRecyclerView.smoothScrollToPosition(i);
                }

            }

        });
    }


    private void load_data() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://restcountries.eu/rest/v2/region/asia";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject country = null;
                int size = response.length();
                for(int i = 0; i < size; i++){
                    try {
                        country = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        createFile(country);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                countryAdapter = new CountryAdapter(shelf.getmCountries());
                mRecyclerView.setAdapter(countryAdapter);
                shelf.fcount(getApplicationContext());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        queue.add(request);
    }

    private void createFile(JSONObject country) throws JSONException {

        String name = country.getString("name");
        String capital = country.getString("capital");
        String flag = country.getString("flag");
        String region = country.getString("region");
        String subregion = country.getString("subregion");
        String population = country.get("population").toString();
        ArrayList<String> borders = new ArrayList<>();
        JSONArray bordersJson = country.getJSONArray("borders");
        ArrayList<String> languages = new ArrayList<>();
        JSONArray languagesJson = country.getJSONArray("languages");
        for(int j = 0; j < languagesJson.length(); j++){
            JSONObject temp = languagesJson.getJSONObject(j);
            String lang = temp.getString("name");
            languages.add(lang);
        }

        for(int j=0; j < bordersJson.length(); j++){
            borders.add(bordersJson.getString(j));
        }

        File file = new File(name, capital, flag, region, subregion, population, borders.toString(), languages.toString());
        shelf.addFile(file);
    }

}