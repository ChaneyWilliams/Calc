package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    HistoryAdapter adapter;
    List<String> historyList = new ArrayList<>();
    private Button clearAllButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnClearAll).setOnClickListener(v -> clearAllHistory());

        loadFragment(new TwoNumberFragment());

        SwitchCompat switchMode = findViewById(R.id.switchMode);

        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                loadFragment(new StandardCalcFragment());
            } else {
                loadFragment(new TwoNumberFragment());
            }
        });

        recyclerView = findViewById(R.id.historyRecycler);
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public void addToHistoryStd(String record){
        historyList.add(record);
        adapter.notifyItemInserted(historyList.size() - 1);
        recyclerView.scrollToPosition(historyList.size() - 1);
    }
    public void addToHistory(double a, double b, String op, double result)
    {
        String record;
        switch (op) {
            case "sin":
            case "cos":
            case "tan":
            case "ln":
                record = op + " " + a + " = " + result;
                break;
            case "log":
            case "√":
                record = b + " " + op + " " + a + " = " + result;
                break;
            case "!":
                record = a + " " + op + " = " + result;
                break;
            default:
                record = a + " " + op + " " + b + " = " + result;
                break;
        }
        historyList.add(record);
        adapter.notifyItemInserted(historyList.size() - 1);
        recyclerView.scrollToPosition(historyList.size() - 1);
    }

    public void clearAllHistory(){
        historyList.clear();
        adapter.notifyDataSetChanged();
    }
}
