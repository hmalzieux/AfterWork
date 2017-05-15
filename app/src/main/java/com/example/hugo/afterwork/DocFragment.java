package com.example.hugo.afterwork;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

public class DocFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        PDFView pdfView = (PDFView) view.findViewById(R.id.pdfView);
        TextView textView = (TextView) view.findViewById(R.id.doc_non_dispo);
        if (CoursActivity.pathPDF.equals("")) {
            pdfView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else{
            pdfView.fromAsset(CoursActivity.pathPDF).load();
            textView.setVisibility(View.GONE);
        }
        return view;
    }
}
