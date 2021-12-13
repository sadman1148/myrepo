package com.redenvy.drawertestapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class frag3 extends Fragment {
    private Button b;
    private TextView t;
    private tabs sender;
    public frag3() {
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
        return inflater.inflate(R.layout.fragment_frag3, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        b = getActivity().findViewById(R.id.frag3Butt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sender.receiveData(true,3);
            }
        });
        t = getActivity().findViewById(R.id.frag3Txt);
        t.setText("Activity says: "+sender.sendData(3));
    }
}