package com.example.hugo.afterwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hugo.afterwork.androidsqlite.DatabaseHandler;

import java.util.ArrayList;

public class profilFragment extends Fragment {
    private ArrayList<String> matieres;
    private DatabaseHandler myDb;
    private OnFragmentInteractionListener mListener;

    public profilFragment() {
    }

    public static profilFragment newInstance(String param1, String param2) {
        profilFragment fragment = new profilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("idUser", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("idUser", -1);
        myDb = new DatabaseHandler(getActivity());
        String[] res = myDb.getInfoUser(id);

        TextView nom_prenom = (TextView) view.findViewById(R.id.id_user_nomprenom);
        TextView mail = (TextView) view.findViewById(R.id.textView);
        TextView section = (TextView) view.findViewById(R.id.section);

        nom_prenom.setText(res[0]+" "+res[1]);
        mail.setText(res[2]);
        section.setText(" "+section.getText()+res[3]);

        matieres = myDb.getNamesMatiere(id);

        TextView liste_matieres = (TextView) view.findViewById(R.id.matieres);
        String s = "";
        for (int i = 0;i<matieres.size();i++) {
            s += matieres.get(i)+"\n";
        }
        liste_matieres.setText(s);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
