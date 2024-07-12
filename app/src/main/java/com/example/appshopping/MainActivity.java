package com.example.appshopping;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
Toolbar Toolbarmhc;
    ViewFlipper ViewFlippermhc;
    NavigationView NavigationViewmhc;
    ListView ListViewmhc;
    RecyclerView recycleviewmhc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
    }
    private void anhxa(){
        Toolbarmhc = findViewById(R.id.Toolbarmhc);
        ViewFlippermhc = findViewById(R.id.ViewFlippermhc);
        ListViewmhc = findViewById(R.id.ListViewmhc);
        NavigationViewmhc = findViewById(R.id.NavigationViewmhc);
        recycleviewmhc = findViewById(R.id.recycleviewmhc);
    }
}