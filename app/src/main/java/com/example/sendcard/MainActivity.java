package com.example.sendcard;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.sendcard.DTO.Phone;
import com.example.sendcard.Database.ObjectBox;
import com.example.sendcard.fragments.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import io.objectbox.Box;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.sendcard.adapters.SectionsPagerAdapter;
import android.app.AlertDialog;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectBox.init(this);
        setContentView(R.layout.activity_main);
        toolBar();
        tabs();
        floatingButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send_for_all:
                promptUser(R.layout.prompt_amount, "ok", "cancel");
                return true;
            case R.id.action_settings:
                promptUser(R.layout.prompt_confimation, "yes", "no");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void promptUser(int resource, final String posetiveText, String negativeText) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(resource, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(posetiveText,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if (posetiveText.equals("add")){
                                    promptUser(R.layout.prompt_confimation, "yes", "no");
                                }
                                // get user input and set it to result
                                // edit text
                            }
                        })
                .setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void promptUserAddPhone(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt_new_number, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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

    public void floatingButton(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptUserAddPhone(view);
            }
        });
    }
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void tabs(){
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}