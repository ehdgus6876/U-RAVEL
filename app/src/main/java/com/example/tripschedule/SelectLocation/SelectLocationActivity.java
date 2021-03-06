package com.example.tripschedule.SelectLocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.tripschedule.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SelectLocationActivity extends AppCompatActivity {

    private Button btn_food;
    private Button btn_tour;
    private Button btn_sleep;
    private FloatingActionButton fab_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        btn_food=findViewById(R.id.btn_food);
        btn_tour=findViewById(R.id.btn_tour);
        btn_sleep=findViewById(R.id.btn_sleep);
        fab_main=findViewById(R.id.fab_main);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        FoodFragment foodFragment=new FoodFragment();
        transaction.replace(R.id.frame,foodFragment);
        transaction.commit();


        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                FoodFragment foodFragment=new FoodFragment();
                transaction.replace(R.id.frame,foodFragment);
                transaction.commit();
            }
        });
        btn_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                TourFragment tourFragment=new TourFragment();
                transaction.replace(R.id.frame,tourFragment);
                transaction.commit();
            }
        });
        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                SleepFragment SleepFragment=new SleepFragment();
                transaction.replace(R.id.frame,SleepFragment);
                transaction.commit();
            }
        });

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectBasket.class);
                startActivity(intent);
            }
        });



    }

}

