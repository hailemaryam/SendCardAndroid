package com.example.sendcard.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sendcard.R;

import androidx.recyclerview.widget.RecyclerView;

public class SentCustomViewHolder extends RecyclerView.ViewHolder {

    public TextView sentPhoneTextView;
    public TextView requestTextView;
    public TextView responseTextView;
    public ImageView deleteImageView;

    public SentCustomViewHolder(View itemView) {
        super(itemView);

        sentPhoneTextView = itemView.findViewById(R.id.sentPhoneTextView);
        requestTextView = itemView.findViewById(R.id.requestTextView);
        responseTextView = itemView.findViewById(R.id.responseTextView);
        deleteImageView = itemView.findViewById(R.id.deleteSentImageView);
    }
}
