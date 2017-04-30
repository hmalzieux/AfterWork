package com.example.hugo.afterwork;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profilFragment extends Fragment {
    String[] matieres = {"Android", "TEST", "Compilation", "Ingé Log." , "TEST2"};

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
        TextView liste_matieres = (TextView) view.findViewById(R.id.matieres);
        String s = "";
        for (int i = 0;i<matieres.length;i++) {
            s += matieres[i]+"\n";
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
