package com.example.hugo.afterwork;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hugo on 28/04/2017.
 */
public class QcmDoneAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String[]> contenu;
    private LayoutInflater inflater;
    public QcmDoneAdapter(Context c, ArrayList<String[]> contenu) {
        mContext = c;
        this.contenu = contenu;
    }

    public int getCount() {
        return contenu.size();
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
            listview = inflater.inflate(R.layout.item_qcm_done, null);
        }
        TextView nom = (TextView) listview.findViewById(R.id.nom_qcm_done);
        TextView note = (TextView) listview.findViewById(R.id.note_qcm);
        nom.setText(contenu.get(position)[0]);
        note.setText(contenu.get(position)[1]);
        note.setText(note.getText()+"/10");
        return listview;
    }
}
