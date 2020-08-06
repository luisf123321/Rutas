package com.neivaroutes.rutas.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.neivaroutes.rutas.IComuni.IComuncation;
import com.neivaroutes.rutas.R;
import com.neivaroutes.rutas.models.Empresas;
import com.neivaroutes.rutas.ui.slideshow.DetalleEmpresas;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> implements View.OnClickListener{


    ArrayList<Empresas> empresas;
    CardView card;
    IComuncation iComuncationFragmentEmp;

    Activity actividad;
    LayoutInflater inflater;

    private View.OnClickListener listener;


    public EmpresaAdapter(ArrayList<Empresas> empresas,Context context){
        this.empresas =  empresas;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_empresas,parent,false);
        v.setOnClickListener(this);
        return new EmpresaViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final EmpresaViewHolder holder, int position) {
        final Empresas emp = empresas.get(position);
        holder.imgEmpresa.setImageResource(emp.getFoto());
        holder.txtNombre.setText(emp.getNombre());



    }

    @Override
    public int getItemCount() {
        return empresas.size();

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


    public static class EmpresaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgEmpresa;
        private TextView txtNombre;
        private TextView txt;
        CardView card;
        IComuncation iComuncationFragmentEmp;
        public EmpresaViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgEmpresa = (ImageView) itemView.findViewById(R.id.imgEmpresas);
            txtNombre = (TextView) itemView.findViewById(R.id.txtEmpresas);
        }

    }
}
