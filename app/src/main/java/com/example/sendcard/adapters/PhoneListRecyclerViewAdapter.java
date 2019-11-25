package com.example.sendcard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendcard.DTO.Phone;
import com.example.sendcard.DTO.Sent;
import com.example.sendcard.Database.ObjectBox;
import com.example.sendcard.R;
import com.example.sendcard.ui.main.PhoneCustomViewHolder;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.objectbox.Box;

public class PhoneListRecyclerViewAdapter extends RecyclerView.Adapter<PhoneCustomViewHolder> {
    Box<Phone> phoneBox = ObjectBox.get().boxFor(Phone.class);
    private List<Phone> phoneList;
    private Context context;
    public PhoneListRecyclerViewAdapter(List<Phone> phoneList, Context context) {
        this.phoneList = phoneList;
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @NonNull
    @Override
    public PhoneCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_item, parent, false);
        return new PhoneCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhoneCustomViewHolder holder, int position) {
        final int itemPosition = position;
        Phone phone = phoneList.get(itemPosition);
        holder.phoneTextView.setText(phone.getPhone());
        holder.sendImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneBox.remove(phone);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}