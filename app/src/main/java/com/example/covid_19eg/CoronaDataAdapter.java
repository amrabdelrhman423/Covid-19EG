package com.example.covid_19eg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19eg.models.allProperties;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoronaDataAdapter extends RecyclerView.Adapter<CoronaDataAdapter.CustomViewHolder> implements Filterable {

    private List<allProperties> data;
    private List<allProperties> dataCopy;
    public CoronaDataAdapter(List<allProperties> data) {
        this.data = data;
        //copy the list for searching purpose
        dataCopy = new ArrayList<>(data);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        //Views inside the list_item xml file
        private ImageView image;
        private TextView countryName;
        private TextView totalCases;
        private TextView totalDeaths;
        private TextView totalRecovered;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.country_flag);
            countryName = itemView.findViewById(R.id.country_name);
            totalCases = itemView.findViewById(R.id.infected);
            totalDeaths = itemView.findViewById(R.id.deaths);
            totalRecovered = itemView.findViewById(R.id.recovered);

        }
    }


    @NonNull
    @Override
    public CoronaDataAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull CoronaDataAdapter.CustomViewHolder holder, int position) {
        allProperties currentElement = data.get(position);

        Picasso.get().load(currentElement.getImageUrl()).into(holder.image);
        holder.countryName.setText(currentElement.getCountry());
        holder.totalCases.setText(currentElement.getCases());
        holder.totalDeaths.setText(currentElement.getDeaths());
        holder.totalRecovered.setText(currentElement.getRecovered());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public Filter getFilter() {
        return dataFilter;
    }

    private Filter dataFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<allProperties> filteredData = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filteredData.addAll(dataCopy);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (allProperties properties : dataCopy){
                    if(properties.getCountry().toLowerCase().contains(filterPattern)){
                        filteredData.add(properties);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredData;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
