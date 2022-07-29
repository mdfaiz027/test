package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<Uri> childModelClassList;
    ParentAdapter parentAdapter;
    ChildAdapter childAdapter;
    FloatingActionButton btnOpenDialog;

    private  static  final int Read_Permission = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.idFirstRV);

        parentModelClassArrayList = new ArrayList<>();
        childModelClassList = new ArrayList<>();

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

                        parentModelClassArrayList.add(new ParentModelClass(title,childModelClassList));
                        parentAdapter.notifyItemInserted(parentModelClassArrayList.size()-1);
                        recyclerView.scrollToPosition(parentModelClassArrayList.size()-1);
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
        parentAdapter = new ParentAdapter(this, parentModelClassArrayList, this::onItemClick);
        recyclerView.setAdapter(parentAdapter);
        //done here

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
        }

        //recyclerAdapter = new RecyclerAdapter(uri);
        //recyclerView1.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        //recyclerView1.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "Button " + position + " clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setType("image/*");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            }
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data.getClipData() != null){
                int x = data.getClipData().getItemCount();

                for(int i=0;i<x;i++){
                    childModelClassList.add(data.getClipData().getItemAt(i).getUri());
                }
                parentAdapter.notifyDataSetChanged();
            }
            else if(data.getData() != null){
                String imageURL = data.getData().getPath();
                childModelClassList.add(Uri.parse(imageURL));
            }
        }
    }
}