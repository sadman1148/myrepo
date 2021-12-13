package com.redenvy.drawertestapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.redenvy.drawertestapp.databinding.FragmentFrag1Binding;

public class frag1 extends Fragment {
    private Button b;
    private TextView t;
    private tabs sender;

    public frag1() { }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        b = getActivity().findViewById(R.id.frag1Butt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sender.receiveData("A String",1);
            }
        });
        t = getActivity().findViewById(R.id.frag1Txt);
        t.setText("Activity says: "+sender.sendData(1));
    }

}