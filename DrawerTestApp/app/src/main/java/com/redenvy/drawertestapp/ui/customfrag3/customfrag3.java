package com.redenvy.drawertestapp.ui.customfrag3;

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
import android.widget.Toast;

import com.redenvy.drawertestapp.R;
import com.redenvy.drawertestapp.databinding.Customfrag3FragmentBinding;
import com.redenvy.drawertestapp.databinding.FragmentSlideshowBinding;
import com.redenvy.drawertestapp.ui.slideshow.SlideshowViewModel;

public class customfrag3 extends Fragment {

    private Customfrag3ViewModel mViewModel;

    public static customfrag3 newInstance() {
        return new customfrag3();
    }

    private Customfrag3FragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Toast.makeText(getContext(), "Custom Frag 3 Selected", Toast.LENGTH_SHORT).show();

        mViewModel = new ViewModelProvider(this).get(Customfrag3ViewModel.class);

        binding = Customfrag3FragmentBinding.inflate(inflater, container, false);
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