package com.neivaroutes.rutas.ui.slideshow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.neivaroutes.rutas.IComuni.IComuncation;
import com.neivaroutes.rutas.R;
import com.neivaroutes.rutas.models.Empresas;
import com.neivaroutes.rutas.models.MRutas;
import com.neivaroutes.rutas.ui.gallery.EmpresaAdapter;
import com.neivaroutes.rutas.ui.gallery.RutaAdapter;

import java.util.ArrayList;


public class DetalleEmpresas extends Fragment {


    private static final String TAG = "" ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Empresas");

    TextView textdetalle;
    private RecyclerView recyclerViewRutas;
    IComuncation comuncation;
    Activity actividad;

    ArrayList<MRutas> rutas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detalle_empresas, container, false);
        //Bundle parametros = getActivity().getIntent().getExtras();
        //String nombre = parametros.getString("nombre");
        //textdetalle = ( TextView) root.findViewById(R.id.txtdetalle);
        //textdetalle.setText(nombre);


        recyclerViewRutas = (RecyclerView) root.findViewById(R.id.reciclerruta);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRutas.setLayoutManager(llm);
        rutas = new ArrayList<MRutas>();

        myRef.child("AutobusesSA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot hijodata:data.getChildren()){
                    System.out.println("Ruta #    :   "+hijodata.getKey());
                    rutas.add(new MRutas(hijodata.getKey(),R.drawable.com_facebook_profile_picture_blank_portrait));

                }
                adaptadoruta();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return root;
    }
    public void adaptadoruta(){
        if (rutas==null){
            System.out.println("array null");

        }
        RutaAdapter adapter = new RutaAdapter(rutas,getActivity());
        recyclerViewRutas.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comuncation.IComunicaFragmentRuta();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
            comuncation = (IComuncation) this.actividad;
        }
    }
}
