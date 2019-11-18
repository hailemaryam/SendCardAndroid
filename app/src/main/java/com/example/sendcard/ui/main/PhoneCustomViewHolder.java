package com.example.sendcard.ui.main;

import android.view.View;
import android.widget.TextView;

import com.example.sendcard.R;

import androidx.recyclerview.widget.RecyclerView;

public class PhoneCustomViewHolder extends RecyclerView.ViewHolder {

    TextView phoneTextView;

    public PhoneCustomViewHolder(View itemView) {
        super(itemView);

        phoneTextView = itemView.findViewById(R.id.phoneTextView);
    }
}
