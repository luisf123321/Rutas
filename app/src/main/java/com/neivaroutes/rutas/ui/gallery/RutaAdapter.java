package com.neivaroutes.rutas.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.neivaroutes.rutas.IComuni.IComuncation;
import com.neivaroutes.rutas.R;
import com.neivaroutes.rutas.models.Empresas;
import com.neivaroutes.rutas.models.MRutas;

import java.util.ArrayList;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.RutaViewHolder> implements View.OnClickListener {

    ArrayList<MRutas> rutas;

    Activity actividad;
    LayoutInflater inflater;

    private View.OnClickListener listener;


    public RutaAdapter(ArrayList<MRutas> rutas, Context context){
        this.rutas =  rutas;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;

    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    @NonNull
    @Override
    public RutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rutas,parent,false);
        v.setOnClickListener(this);
        return new RutaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaViewHolder holder, int position) {
        MRutas ruta = rutas.get(position);
        holder.imgEmpresa.setImageResource(ruta.getFoto());
        holder.txtNombre.setText(ruta.getNombre());

    }

    @Override
    public int getItemCount() {
        return rutas.size();
    }

    public static class RutaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgEmpresa;
        private TextView txtNombre;

        public RutaViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgEmpresa = (ImageView) itemView.findViewById(R.id.imageView2);
            txtNombre = (TextView) itemView.findViewById(R.id.txtRuta);
        }

    }
}
