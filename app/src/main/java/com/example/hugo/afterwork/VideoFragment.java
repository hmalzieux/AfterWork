package com.example.hugo.afterwork;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        final VideoView videoView = (VideoView) view.findViewById(R.id.video_view);
        TextView textView = (TextView) view.findViewById(R.id.doc_non_dispo);
        Button play = (Button) view.findViewById(R.id.bouton_play);
        final MediaController mediaController = new MediaController(getContext());
        if (CoursActivity.pathVideo.equals("")) {
            videoView.setVisibility(View.GONE);
            play.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else{
            textView.setVisibility(View.GONE);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/"+CoursActivity.pathVideo));
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    videoView.start();
                }
            });
        }
        return view;
    }
}
