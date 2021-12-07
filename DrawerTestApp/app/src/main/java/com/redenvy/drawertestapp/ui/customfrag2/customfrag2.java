package com.redenvy.drawertestapp.ui.customfrag2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redenvy.drawertestapp.R;
import com.redenvy.drawertestapp.databinding.Customfrag1FragmentBinding;
import com.redenvy.drawertestapp.databinding.Customfrag2FragmentBinding;
import com.redenvy.drawertestapp.ui.customfrag1.Customfrag1ViewModel;

public class customfrag2 extends Fragment {

    private Customfrag2ViewModel mViewModel;

    public static customfrag2 newInstance() {
        return new customfrag2();
    }

    private Customfrag2FragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(Customfrag2ViewModel.class);

        binding = Customfrag2FragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textview;
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}