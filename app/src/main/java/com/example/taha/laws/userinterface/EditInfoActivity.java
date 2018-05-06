package com.example.taha.laws.userinterface;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taha.laws.R;


public class EditInfoActivity extends AppCompatActivity

    {
        Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

        private void createDialog() {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want to delete your account ?")
                    .setMessage("This will delete your account (account name )and all of its contents")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(EditInfoActivity.this,"item deleted with success",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }
    }

