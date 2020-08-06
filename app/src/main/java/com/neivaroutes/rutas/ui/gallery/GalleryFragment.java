package com.neivaroutes.rutas.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.neivaroutes.rutas.IComuni.IComuncation;
import com.neivaroutes.rutas.R;
import com.neivaroutes.rutas.models.CodenadasRutas;
import com.neivaroutes.rutas.models.Empresas;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final String TAG = "";
    //private ListView listview ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Empresas");


    private ArrayList<Empresas> emp;

    private RecyclerView recyclerViewempresas;
    IComuncation comuncation;

    Activity actividad;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerViewempresas = (RecyclerView) root.findViewById(R.id.recicleremp);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewempresas.setLayoutManager(llm);
        emp = new ArrayList<Empresas>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot hijodata:data.getChildren()){
                    System.out.println("empresas    :   "+hijodata.getKey());
                    emp.add(new Empresas(hijodata.getKey(),R.drawable.com_facebook_profile_picture_blank_portrait));

                }
                adaptadoremp();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        return root;
    }
    public void adaptadoremp(){
        if (emp==null){
            System.out.println("array null");

        }
        Context context;
        EmpresaAdapter adapter = new EmpresaAdapter(emp,getActivity());
        recyclerViewempresas.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comuncation.IComunicaFragmentEmp();
                //Toast.makeText(getContext(),"empresa frag", Toast.LENGTH_SHORT).show();
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
