package com.strategicinvestor.strategicinvestor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strategicinvestor.strategicinvestor.R;

import java.util.ArrayList;

public class StrategiesAdapter extends RecyclerView.Adapter<StrategiesAdapter.StrategiesViewModel> {

    private ArrayList<String> allStrategies;
    private ChooseStratergy stratergy;

    public StrategiesAdapter(ArrayList<String> allStrategies, ChooseStratergy stratergy)
    {
        this.allStrategies = allStrategies;
        this.stratergy = stratergy;
    }

    @NonNull
    @Override
    public StrategiesViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stratergies_element_rv, viewGroup, false);
        return new StrategiesViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StrategiesViewModel strategiesViewModel, int i) {
        strategiesViewModel.strategy.setText(allStrategies.get(i));
        strategiesViewModel.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stratergy.selectStrat(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allStrategies.size();
    }

    class StrategiesViewModel extends RecyclerView.ViewHolder{

        TextView strategy;
        View view;

        StrategiesViewModel(@NonNull View itemView) {
            super(itemView);
            strategy = itemView.findViewById(R.id.ste_name_tv);
            view = itemView;
        }
    }

    public interface ChooseStratergy{
        void selectStrat(int pos);
    }
}
