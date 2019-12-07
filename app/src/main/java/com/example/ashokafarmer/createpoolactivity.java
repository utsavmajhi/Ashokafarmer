package com.example.ashokafarmer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class createpoolactivity extends AppCompatActivity {

    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpoolactivity);
        mItemSelected = (TextView) findViewById(R.id.itemselect);


    }

//selecting toggle menu for diffrent lands he has which has already been verified
    public void createselland(View view) {

        //backend work




        //backend work ends



        //pass the list contents from json get and put it in listitems
        listItems = getResources().getStringArray(R.array.LandName);
        checkedItems = new boolean[listItems.length];
        //passing list contentts ends


        //hardcoded the toggle menu for selecting lands
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(createpoolactivity.this);
        mBuilder.setTitle("Lands");
        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//
                if(isChecked){
                    mUserItems.add(position);
                }else{
                    mUserItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                //basically museritem has all the things just pass this array
                for (int i = 0; i < mUserItems.size(); i++) {
                    item = item + listItems[mUserItems.get(i)];
                    if (i != mUserItems.size() - 1) {
                        item = item + ", ";
                    }
                }
                //showing the slected items
                mItemSelected.setText("Selected Lands:"+item);
            }
        });

        mBuilder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton("CLEAR ALL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                    mUserItems.clear();
                    mItemSelected.setText("");
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
        //Multiselect ends ...output is array or string of text separated by comma




    }


    //final submit btn click for lands selcted to make pool
    public void slelandsubclick(View view) {

        if(mItemSelected.getText().equals("")||mItemSelected.getText().equals("No Land selected"))
        {
            Toast.makeText(this, "Please Select Lands to proceed Note:If land name is not showing then contact Engineer/Administartor", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Creating Pool Done!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(createpoolactivity.this,homepage.class));
            finish();
        }

    }
    //
}
