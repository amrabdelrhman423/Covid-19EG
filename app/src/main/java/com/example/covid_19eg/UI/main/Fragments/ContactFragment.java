package com.example.covid_19eg.UI.main.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_19eg.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_contact, container, false);

        final TextView hotLine105 = root.findViewById(R.id.hot_line_105);
        final TextView hotLine15335 = root.findViewById(R.id.hot_line_15335);
        addUnderlineText(hotLine105);
        addUnderlineText(hotLine15335);


        hotLine105.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber("105");
            }
        });

        hotLine15335.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber("15335");
            }
        });
        return  root;
    }

    private void dialPhoneNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        if (intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * @param textView the underlined text
     */
    public void addUnderlineText(TextView textView){
        SpannableString content = new SpannableString( textView.getText().toString() );
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 );
        textView.setText(content);
    }

}
