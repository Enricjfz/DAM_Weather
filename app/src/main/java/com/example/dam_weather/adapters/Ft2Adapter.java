package com.example.dam_weather.adapters;


import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dam_weather.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Ft2Adapter extends RecyclerView.Adapter<Ft2Adapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ModelFt2> lista;
    private View.OnClickListener listener;

    public Ft2Adapter(Context context, ArrayList<ModelFt2> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public Ft2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_custom_list_view_ft2,parent,false);

        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ft2Adapter.ViewHolder holder, int position) {

        ModelFt2 model = this.lista.get(position);
        holder.max_temp.setText(model.getMax_temp() + " º C");
        holder.min_temp.setText(model.getMin_temp()+ " º C");
        holder.fecha.setText(model.getFecha()); //Se puede formatear la fecha
        holder.prep.setText(model.getPrecipitaciones() + " mm");
        holder.maxwind_kph.setText(model.getMaxwind_kph() + " kph");
        Picasso.get().load("http:".concat(model.getIcon_path())).into(holder.icon);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    @Override
    public void onClick(View view) {
        if(this.listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fecha, max_temp, min_temp, prep, maxwind_kph;
        private ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.fecha);
            max_temp = itemView.findViewById(R.id.t_max);
            min_temp = itemView.findViewById(R.id.t_min);
            prep = itemView.findViewById(R.id.precipitacion);
            maxwind_kph = itemView.findViewById(R.id.vel_viento);
            icon = itemView.findViewById(R.id.iconImg);

        }
    }
}
