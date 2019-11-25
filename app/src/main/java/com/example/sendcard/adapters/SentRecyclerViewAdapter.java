package com.example.sendcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendcard.DTO.Sent;
import com.example.sendcard.Database.DatabaseQueryClass;
import com.example.sendcard.R;
import com.example.sendcard.ui.main.SentCustomViewHolder;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SentRecyclerViewAdapter extends RecyclerView.Adapter<SentCustomViewHolder> {

    private List<Sent> sentList;
    private Context context;
    private DatabaseQueryClass databaseQueryClass;

    public SentRecyclerViewAdapter(List<Sent> sentList, Context context) {
        this.sentList = sentList;
        this.context = context;
        this.databaseQueryClass = new DatabaseQueryClass(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @NonNull
    @Override
    public SentCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sent_item, parent, false);
        return new SentCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentCustomViewHolder holder, int position) {
        final int itemPosition = position;
        Sent sent = sentList.get(itemPosition);
        holder.sentPhoneTextView.setText(sent.getPhone());
        holder.requestTextView.setText(sent.getRequest());
        holder.responseTextView.setText(sent.getResponse());
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("sent delete on click listener.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return sentList.size();
    }
}
