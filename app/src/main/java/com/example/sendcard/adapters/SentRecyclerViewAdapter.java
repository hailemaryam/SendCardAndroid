package com.example.sendcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sendcard.DTO.Sent;
import com.example.sendcard.Database.ObjectBox;
import com.example.sendcard.R;
import com.example.sendcard.ui.main.SentCustomViewHolder;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.objectbox.Box;

public class SentRecyclerViewAdapter extends RecyclerView.Adapter<SentCustomViewHolder> {
    Box<Sent> sentBox = ObjectBox.get().boxFor(Sent.class);

    private List<Sent> sentList;
    private Context context;
    private TextView sentListEmptyTextView;


    public SentRecyclerViewAdapter(List<Sent> sentList, Context context, TextView sentListEmptyTextView) {
        this.sentList = sentList;
        this.context = context;
        this.sentListEmptyTextView = sentListEmptyTextView;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @NonNull
    @Override
    public SentCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sent_item, parent, false);
        sentViewVisibility();
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
                sentBox.remove(sent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sentList.size();
    }

    public void sentViewVisibility() {
        if(sentList.isEmpty())
            sentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            sentListEmptyTextView.setVisibility(View.GONE);
    }

}
