package com.redenvy.drawertestapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.redenvy.drawertestapp.Activity1;
import com.redenvy.drawertestapp.R;
import com.redenvy.drawertestapp.databinding.FragmentHomeBinding;
import com.redenvy.drawertestapp.ui.home.Adapter.ListAdapter;
import com.redenvy.drawertestapp.ui.home.Model.Sample;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    List <Sample> sampleList = new ArrayList<>();
    String [] name = {"BJIT","BRAC","DotNet"};
    int [] images = {android.R.drawable.ic_dialog_alert,android.R.drawable.ic_dialog_info,android.R.drawable.ic_dialog_map};
    String [] details = {"Software Company","NGO","Internet Service Provider"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        binding.act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Activity1.class));
            }
        });
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        binding.recycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(
                getActivity().getApplicationContext()).color(android.R.color.darker_gray).
                sizeResId(R.dimen.divider).marginResId(R.dimen.recMargin,R.dimen.recMargin).build());
        getData();
        return root;
    }
    public void getData(){
        for(int i=0; i<name.length; i++){
            sampleList.add(new Sample(name[i],details[i],images[i]));
        }
        generateList(sampleList);
    }
    public void generateList(List<Sample> sampleList){
        ListAdapter listAdapter = new ListAdapter(getActivity().getApplicationContext(),sampleList);
        binding.recycler.setAdapter(listAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}