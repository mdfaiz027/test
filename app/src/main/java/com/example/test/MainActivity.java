package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    ArrayList<TitleModel> titleModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton btnOpenDialog;
    TitleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.idFirstRV);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog_layout);

                TextView edtTitle = dialog.findViewById(R.id.edtTitle);
                Button okBtn = dialog.findViewById(R.id.okBtn);
                Button cancelBtn = dialog.findViewById(R.id.cancelBtn);

                // ok button
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String title = "";

                        if(!edtTitle.getText().toString().equals("")){
                            title = edtTitle.getText().toString();

                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter the title", Toast.LENGTH_SHORT).show();
                        }

                        titleModelArrayList.add(new TitleModel(title));
                        adapter.notifyItemInserted(titleModelArrayList.size()-1);
                        recyclerView.scrollToPosition(titleModelArrayList.size()-1);
                        dialog.dismiss();
                    }
                });
                // cancel button
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TitleAdapter(this, titleModelArrayList, this::onItemClick);
        recyclerView.setAdapter(adapter);
        //done here
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "Button " + position + " clicked", Toast.LENGTH_SHORT).show();
    }
}