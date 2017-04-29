package com.example.hugo.afterwork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChoixTypeCourFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ChoixTypeCourFragment() {}

    public static ChoixTypeCourFragment newInstance(String param1, String param2) {
        ChoixTypeCourFragment fragment = new ChoixTypeCourFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choix_type_cour, container, false);

        LinearLayout linear_up = (LinearLayout) view.findViewById(R.id.linear_up_arrow);
        linear_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom);
                ft.replace(R.id.content_main, new MatiereFragment());
                ft.commit();
            }
        });

        TextView nom_matiere = (TextView) view.findViewById(R.id.nom_matiere);
        nom_matiere.setText(getArguments().getString("Matiere"));
        Button button_cour = (Button) view.findViewById(R.id.bouton_cour);
        button_cour.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        changerActiviteCours(getArguments().getString("Matiere")+", Cours");
                    }
                });
        Button button_td = (Button) view.findViewById(R.id.bouton_td);
        button_td.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changerActiviteCours(getArguments().getString("Matiere")+", TDs");
            }
        });
        Button button_tp = (Button) view.findViewById(R.id.bouton_tp);
        button_tp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changerActiviteCours(getArguments().getString("Matiere")+", TPs");
            }
        });
        Button button_all = (Button) view.findViewById(R.id.tous_les_cours);
        button_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changerActiviteCours(getArguments().getString("Matiere"));
            }
        });

        return view;
    }

    private void changerActiviteCours(String filtre){
        Intent intent = new Intent(getActivity(),ListCoursActivity.class);
        intent.putExtra("filtre",filtre);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
