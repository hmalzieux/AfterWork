package com.example.hugo.afterwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hugo on 28/04/2017.
 */
public class CoursAdapter extends BaseAdapter {
    private Context mContext;
    private String[][] contenu;
    private LayoutInflater inflater;
    public CoursAdapter(Context c, String[][] contenu) {
        mContext = c;
        this.contenu = contenu;
    }

    public int getCount() {
        return contenu.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview = convertView;

        if (convertView == null) {
            inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listview = inflater.inflate(R.layout.item_cours, null);
        }
        TextView nom = (TextView) listview.findViewById(R.id.cour_nom);
        TextView matiere = (TextView) listview.findViewById(R.id.cours_matiere);
        TextView type = (TextView) listview.findViewById(R.id.cours_type);
        nom.setText(contenu[position][0]);
        matiere.setText(contenu[position][1]);
        type.setText(contenu[position][2]);
        return listview;
    }
}
