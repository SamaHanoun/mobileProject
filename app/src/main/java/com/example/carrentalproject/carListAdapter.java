package com.example.carrentalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class carListAdapter extends RecyclerView.Adapter<carListAdapter.CarViewHolder> {

    private List<Car> carList;
    private OnCarClickListener onCarClickListener;

    public interface OnCarClickListener {
        void onCarClick(int position);
    }

    public carListAdapter(List<Car> carList, OnCarClickListener onCarClickListener) {
        this.carList = carList;
        this.onCarClickListener = onCarClickListener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CarViewHolder(view, onCarClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.textView.setText(car.toString());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        OnCarClickListener onCarClickListener;

        public CarViewHolder(@NonNull View itemView, OnCarClickListener onCarClickListener) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            this.onCarClickListener = onCarClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCarClickListener.onCarClick(getAdapterPosition());
        }
    }
}
