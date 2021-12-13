package com.redenvy.drawertestapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class frag2 extends Fragment {
    private Button b;
    private TextView t;
    private tabs sender;
    public frag2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sender = (tabs)getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag2, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        b = getActivity().findViewById(R.id.frag2Butt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sender.receiveData(11432,2);
            }
        });
        t = getActivity().findViewById(R.id.frag2Txt);
        t.setText("Activity says: "+sender.sendData(2));
    }
}