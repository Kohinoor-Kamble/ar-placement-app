package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DrillInfoActivity extends AppCompatActivity {

    TextView drillName, drillDescription, drillTip;
    ImageView drillImage;
    Button startARButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drill_info);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        drillName=findViewById(R.id.drillName);
        drillDescription=findViewById(R.id.drillDescription);
        drillTip=findViewById(R.id.drillTip);
        drillImage=findViewById(R.id.drillImage);
        startARButton=findViewById(R.id.stratButton);
        //get selected name from the intent
        String selectedDrill=getIntent().getStringExtra("drill");

        //setDummy data
//        drillName.setText(selectedDrill);
//        drillDescription.setText("This is a description for "+ selectedDrill);
//        drillTip.setText("Tips for" + selectedDrill+":stay steady while placing");

        if(selectedDrill !=null){
            drillName.setText(selectedDrill);

            switch (selectedDrill){
                case "Drill 1":
                    drillDescription.setText("This is a drill 1. It focuses on basic agility.");
                   drillTip.setText("Tips: Keep your balance steady.");
                   drillImage.setImageResource(R.drawable.elon_musk3);
                   break;
                case "Drill 2":
                    drillDescription.setText("This is a drill 2. It focuses on accuracy.");
                    drillTip.setText("Tips: Focus your eyes on the marker.");
                    drillImage.setImageResource(R.drawable.elon_musk2);
                    break;
                case "Drill 3":
                    drillDescription.setText("This is a drill 3. Improve your endurance.");
                    drillTip.setText("Tips: Pace yourself slowly");
                    drillImage.setImageResource(R.drawable.elon_musk4);
                    break;
            }
        }

        startARButton.setOnClickListener(v ->{
            Intent intent=new Intent(DrillInfoActivity.this, ARActivity.class);
            intent.putExtra("drill", selectedDrill);
            startActivity(intent);
        });

    }
}