package com.example.hugo.afterwork;

import android.content.Context;
import android.hardware.SensorManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import static android.content.Context.SENSOR_SERVICE;
import com.example.hugo.afterwork.androidsqlite.DatabaseHandler;

import java.util.ArrayList;


public class MatiereFragment extends Fragment {
    private ArrayList<String> matieres;
    private DatabaseHandler myDb;


    private OnFragmentInteractionListener mListener;


    public MatiereFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("idUser", Context.MODE_PRIVATE);
        myDb = new DatabaseHandler(getActivity());
        matieres = myDb.getNamesMatiere(sharedPreferences.getInt("idUser", -1));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matiere, container, false);
         /*Listview Matière*/
        GridView gridview = (GridView) view.findViewById(R.id.gridview_matiere);
        ArrayAdapter<String> matieresAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item_matiere, matieres);
        gridview.setAdapter(matieresAdapter);

        gridview.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView , View view , int position ,long arg3)
            {
                ChoixTypeCourFragment dest = new ChoixTypeCourFragment();
                Bundle args = new Bundle();
                args.putString("Matiere", matieres.get(position));
                dest.setArguments(args);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                ft.replace(R.id.content_main, dest);
                ft.commit();
            }
        });
        if (gridview.getCount() == 0){
            view.findViewById(R.id.list_vide).setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
