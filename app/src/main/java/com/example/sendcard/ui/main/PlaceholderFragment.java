package com.example.sendcard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.sendcard.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root;
        if (getArguments() != null && getArguments().getInt(ARG_SECTION_NUMBER) == 1 ) {
            root = inflater.inflate(R.layout.fragment_phone_list, container, false);
            final TextView textView = root.findViewById(R.id.section_label);
            textView.setText("phone list");
        } else if (getArguments() != null && getArguments().getInt(ARG_SECTION_NUMBER) == 2){
            root = inflater.inflate(R.layout.fragment_sent_list, container, false);
            final TextView textView = root.findViewById(R.id.section_label);
            textView.setText("sent list");
        } else {
            root = inflater.inflate(R.layout.fragment_phone_list, container, false);
            final TextView textView = root.findViewById(R.id.section_label);
            textView.setText("phone list");
        }
        return root;
    }
}