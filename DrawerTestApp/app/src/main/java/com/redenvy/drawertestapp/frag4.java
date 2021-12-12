package com.redenvy.drawertestapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class frag4 extends Fragment {
    private Button b;
    private tabs sender;
    public frag4() {
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
        return inflater.inflate(R.layout.fragment_frag4, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        b = getActivity().findViewById(R.id.frag4Butt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sender.receiveData('X',4);
            }
        });
    }
}