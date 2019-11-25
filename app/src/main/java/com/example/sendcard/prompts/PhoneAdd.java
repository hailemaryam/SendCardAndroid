package com.example.sendcard.prompts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.sendcard.DTO.Phone;
import com.example.sendcard.R;
import com.example.sendcard.fragments.PlaceholderFragment;
import com.google.android.material.snackbar.Snackbar;

public class PhoneAdd {
    public static void promptUserAddPhone(View view, Context context) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt_new_number, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Phone phone = new Phone();
                                phone.setPhone(userInput.getText().toString());
                                try {
                                    Snackbar snackbar = Snackbar.make(view, "successfully added.", 2000);
                                    snackbar.show();
                                    PlaceholderFragment.phoneListRecyclerViewAdapter.addPhoneList(phone);
                                } catch (Exception e) {
                                    Snackbar snackbar = Snackbar.make(view, "Phone number already added.", 2000);
                                    snackbar.show();
                                }
                            }
                        })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
