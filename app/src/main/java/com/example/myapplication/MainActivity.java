package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner drillSpinner;
    Button btnStartDrill;

    String selectedDrill=" ";
//    private Build.VERSION_CODES android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        drillSpinner=findViewById(R.id.drillSpinner);
        btnStartDrill=findViewById(R.id.btnStartDrill);

        String[] drillList={"Selected Drill","Drill 1","Drill 2", "Drill 3"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, drillList);

        drillSpinner.setAdapter(adapter);

        drillSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDrill= drillList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                     selectedDrill="";
            }
        });
        btnStartDrill.setOnClickListener(v -> {
            if(selectedDrill.equals("Select Drill") || selectedDrill.isEmpty()){
                Toast.makeText(MainActivity.this,"Please select a drill", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent=new Intent(MainActivity.this, DrillInfoActivity.class);
                intent.putExtra("drill" , selectedDrill);
                startActivity(intent);
            }

        });
    }
}