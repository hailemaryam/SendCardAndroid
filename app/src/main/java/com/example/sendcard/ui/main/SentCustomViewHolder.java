package com.example.sendcard.ui.main;

import android.view.View;
import android.widget.TextView;

import com.example.sendcard.R;

import androidx.recyclerview.widget.RecyclerView;

public class SentCustomViewHolder extends RecyclerView.ViewHolder {

    TextView sentPhoneTextView;
    TextView requestTextView;
    TextView responseTextView;

    public SentCustomViewHolder(View itemView) {
        super(itemView);

        sentPhoneTextView = itemView.findViewById(R.id.sentPhoneTextView);
        requestTextView = itemView.findViewById(R.id.requestTextView);
        responseTextView = itemView.findViewById(R.id.responseTextView);
    }
}
