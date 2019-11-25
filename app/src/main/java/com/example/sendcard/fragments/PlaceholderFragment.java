package com.example.sendcard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sendcard.DTO.Phone;
import com.example.sendcard.DTO.Sent;
import com.example.sendcard.Database.DatabaseQueryClass;
import com.example.sendcard.R;
import com.example.sendcard.adapters.PhoneListRecyclerViewAdapter;
import com.example.sendcard.adapters.SentRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private DatabaseQueryClass databaseQueryClass= new DatabaseQueryClass(getActivity());
    private List<Phone> phoneList = new ArrayList<>();
    private TextView phoneListEmptyTextView;
    private RecyclerView phoneListRecyclerView;
    private PhoneListRecyclerViewAdapter phoneListRecyclerViewAdapter;
    private List<Sent> sentList = new ArrayList<>();
    private TextView sentListEmptyTextView;
    private RecyclerView sentListRecyclerView;
    private SentRecyclerViewAdapter sentListRecyclerViewAdapter;
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
        } else if (getArguments() != null && getArguments().getInt(ARG_SECTION_NUMBER) == 2){
            root = inflater.inflate(R.layout.fragment_sent_list, container, false);
        } else {
            root = inflater.inflate(R.layout.fragment_phone_list, container, false);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().getInt(ARG_SECTION_NUMBER) == 1 ) {
            phoneListRecyclerView =  (RecyclerView) getView().findViewById(R.id.phoneRecyclerView);
            phoneListEmptyTextView = getView().findViewById(R.id.emptyPhoneListTextView);
//            phoneList.addAll(databaseQueryClass.getAllPhone());
            phoneList.add(new Phone(1, "0987654321"));
            phoneListRecyclerViewAdapter = new PhoneListRecyclerViewAdapter(phoneList,getActivity());
            phoneListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            phoneListRecyclerView.setAdapter(phoneListRecyclerViewAdapter);
            phoneViewVisibility();
        } else if (getArguments() != null && getArguments().getInt(ARG_SECTION_NUMBER) == 2){
            sentListRecyclerView =  (RecyclerView) getView().findViewById(R.id.sentRecyclerView);
            sentListEmptyTextView = getView().findViewById(R.id.emptySentListTextView);
            sentList.add(new Sent(1,"0987767856765", "test", "test"));
//            sentList.addAll(databaseQueryClass.getAllSent());
            sentListRecyclerViewAdapter = new SentRecyclerViewAdapter(sentList,getActivity());
            sentListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            sentListRecyclerView.setAdapter(sentListRecyclerViewAdapter);
           sentViewVisibility();
        } else {
            phoneListRecyclerView =  (RecyclerView) getView().findViewById(R.id.phoneRecyclerView);
            phoneListEmptyTextView = getView().findViewById(R.id.emptyPhoneListTextView);
            phoneList.addAll(databaseQueryClass.getAllPhone());
            phoneListRecyclerViewAdapter = new PhoneListRecyclerViewAdapter(phoneList,getActivity());
            phoneListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            phoneListRecyclerView.setAdapter(phoneListRecyclerViewAdapter);
            phoneViewVisibility();
        }

    }

    public void phoneViewVisibility() {
        if(phoneList.isEmpty())
            phoneListEmptyTextView.setVisibility(View.VISIBLE);
        else
            phoneListEmptyTextView.setVisibility(View.GONE);
    }
    public void sentViewVisibility() {
        if(sentList.isEmpty())
            sentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            sentListEmptyTextView.setVisibility(View.GONE);
    }

}