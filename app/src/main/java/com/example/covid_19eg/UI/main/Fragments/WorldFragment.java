package com.example.covid_19eg.UI.main.Fragments;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.covid_19eg.CoronaDataAdapter;
import com.example.covid_19eg.R;


import com.example.covid_19eg.models.allProperties;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorldFragment extends Fragment  {

    private String strJson;
    //Define Arraylist of user defined objects
    private List<allProperties> data = new ArrayList<>();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_world, container, false);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = Objects.requireNonNull(connMgr).getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            String worldData = "https://corona.lmao.ninja/v2/countries?sort=cases";

            //HTTP request
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(worldData)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        strJson = Objects.requireNonNull(response.body()).string();

                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buildRecyclerView(root);
                            }
                        });

                    }
                }
            });
        } else {
            ConstraintLayout constraintLayout = root.findViewById(R.id.parent);
            HomeFragment homeFragment = new HomeFragment();

            homeFragment.displayToast(constraintLayout);
            homeFragment.closeApp();
        }
        return root;
    }

    private List<allProperties> parseJson(String strJson){
        try {
            JSONArray jsonArray = new JSONArray(strJson);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject imageObject = jsonObject.getJSONObject("countryInfo");

                String imageUrl = imageObject.getString("flag");

                String country   = jsonObject.getString("country");

                String cases     = getResources().getString(R.string.confirmed) + jsonObject.getInt("cases");

                String deaths    = getResources().getString(R.string.deaths) + jsonObject.getInt("deaths");

                String recovered = getResources().getString(R.string.recovered) + jsonObject.getInt("recovered");

                data.add(new allProperties(country,imageUrl,cases,deaths,recovered));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void buildRecyclerView(View root){

        //parse the json response and return the result in the Arraylist
        data = parseJson(strJson);

        //Define a RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        //Define a SearchView
        SearchView searchView = root.findViewById(R.id.search_view);
        //Define an adapter from CoronaDataAdapter class
        final CoronaDataAdapter adapter = new CoronaDataAdapter(data);
        //Assign the adapter to the recyclerView using setAdapter method
        recyclerView.setAdapter(adapter);
        //Set the layout manager using setLayoutManager method
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //Add borders between items using addItemDecoration method
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        //searching
        search(searchView, adapter);
    }

    /**
     * @param searchView the view where the user type
     * @param adapter the adapter which contains the list to be searched
     */
    public static void search(android.widget.SearchView searchView, final CoronaDataAdapter adapter){

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }


}
