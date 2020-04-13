package com.example.covid_19eg.UI.main.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.covid_19eg.R;
import com.example.covid_19eg.UI.main.MainActivity;
import com.example.covid_19eg.UI.main.PropertiesViewModel;
import com.example.covid_19eg.model.properties;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    GridView gridView;
    PropertiesViewModel propertiesViewModel;
    String[] typesName;
    int[] typesImage;
    String[] typenum;
    Toolbar toolbar;
    int[] typColor ;



    public HomeFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data netork
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            gridView = view.findViewById(R.id.gridview);
            typesName= new String[]{"Confirmed","Active", "Recovered", "Deaths"};
            typesImage= new int[]{ R.drawable.corona,R.drawable.patient, R.drawable.heart, R.drawable.death};
            propertiesViewModel = ViewModelProviders.of(this).get(PropertiesViewModel.class);
            propertiesViewModel.getproperties();
            propertiesViewModel.propertiesMutableLiveData.observe(getViewLifecycleOwner() , new Observer<properties>() {

                @Override
                public void onChanged(properties properties) {
                    typenum = new String[]{
                            Integer.toString(properties.getCases()),
                            Integer.toString(properties.getActive()),
                            Integer.toString(properties.getRecovered()),
                            Integer.toString(properties.getDeaths())
                    };

                    CustomAdapter customAdapter = new CustomAdapter();
                    gridView.setAdapter(customAdapter);
                }
            });

        }
        else {

            LinearLayout linearLayout = view.findViewById(R.id.parent);
            displayToast(linearLayout);
            closeApp();
        }


        return view;

    }

    /**
     * @param viewGroup the parent of the layout
     */
    public void displayToast(ViewGroup viewGroup){
        viewGroup.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Check your internet and try again", Toast.LENGTH_LONG).show();
    }

    public void closeApp(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after one second = 1000ms
                //kill the app
                Objects.requireNonNull(getActivity()).finish();
            }
        }, 1000);
    }
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return typesImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.Type);
            ImageView image = view1.findViewById(R.id.images);
            TextView num =view1.findViewById(R.id.num);

            num.setText(typenum[i]);
            name.setText(typesName[i]);
            image.setImageResource(typesImage[i]);

            return view1;
        }


    }

}
