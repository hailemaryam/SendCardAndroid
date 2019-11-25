package com.example.sendcard.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sendcard.R;

import androidx.recyclerview.widget.RecyclerView;

public class PhoneCustomViewHolder extends RecyclerView.ViewHolder {

    public TextView phoneTextView;
    public ImageView deleteImageView;
    public ImageView sendImageView;

    public PhoneCustomViewHolder(View itemView) {
        super(itemView);

        phoneTextView = itemView.findViewById(R.id.phoneTextView);
        deleteImageView = itemView.findViewById(R.id.deletePhoneImageView);
        sendImageView = itemView.findViewById(R.id.sendImageView);
    }
}
