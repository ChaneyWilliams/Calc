package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber1;
    private EditText editNumber2;
    private TextView txtResult;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMult;
    private Button btnDiv;

    RecyclerView recyclerView;
    HistoryAdapter adapter;
    List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);

        //findViewById(R.id.btnSubmit).setOnClickListener(v -> singleValue());
        findViewById(R.id.btnAdd).setOnClickListener(v -> PerformOperation("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> PerformOperation("-"));
        findViewById(R.id.btnMult).setOnClickListener(v -> PerformOperation("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> PerformOperation("/"));
        findViewById(R.id.btnPower).setOnClickListener(v->PerformOperation("^"));
        findViewById(R.id.btnRoot).setOnClickListener(v->PerformOperation("√"));
        findViewById(R.id.btnMod).setOnClickListener(v->PerformOperation("%"));
        findViewById(R.id.btnLog).setOnClickListener(v->PerformOperation("log"));
        findViewById(R.id.btnSin).setOnClickListener(v->PerformOperation("sin"));
        findViewById(R.id.btnCos).setOnClickListener(v->PerformOperation("cos"));
        findViewById(R.id.btnTan).setOnClickListener(v->PerformOperation("tan"));
        findViewById(R.id.btnFactorial).setOnClickListener(v->PerformOperation("!"));
        findViewById(R.id.btnLn).setOnClickListener(v->PerformOperation("ln"));

        findViewById(R.id.btnClear).setOnClickListener(v -> clearAll());

        txtResult = findViewById(R.id.txtResult);

        recyclerView = findViewById(R.id.historyRecycler);

        adapter = new HistoryAdapter(historyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

        // Add the two numbers
    public void PerformOperation(String operation)
    {
        String text1 = editNumber1.getText().toString().trim();
        String text2 = editNumber2.getText().toString().trim();

        if (text1.isEmpty() || text2.isEmpty()) {
            Toast.makeText(MainActivity.this,
                    "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try
        {
            double value1 = Integer.parseInt(text1);
            double value2 = Integer.parseInt(text2);
            double result = 0;
            switch(operation)
            {
                case "+":
                    result = value1 + value2;
                    break;
                case "-":
                    result = value1 - value2;
                    break;
                case "*":
                    result = value1 * value2;
                    break;
                case "/":
                    result = value1 / value2;
                    break;
                case "^":
                    result = Math.pow(value1, value2);
                    break;
                case "√":
                    result = Math.pow(value1, 1.0/value2);
                    break;
                case "log":
                    result = LogBase(value1, value2);
                    break;
                case "%":
                    result = value1 % value2;
                    break;
                case "sin":
                    result = Math.sin(value1);
                    break;
                case "cos":
                    result = Math.cos(value1);
                    break;
                case "tan":
                    result = Math.tan(value1);
                    break;
                case "!":
                    result = Factorial(value1);
                    break;
                case "ln":
                    result = Math.log(value1);
                    break;
            }

                txtResult.setText(ResultFormat(operation, result));
                addToHistory(value1, value2, operation, result);


        } catch (NumberFormatException e)
        {
            Toast.makeText(MainActivity.this,
                    "Invalid input, please enter valid integers", Toast.LENGTH_SHORT).show();
        }
    }

    public double Factorial(double num)
    {
        if(num <= 0){
            throw new IllegalArgumentException("Number must be >= 0");
        }
        double result = 1.0;
        for (int i = 2; i <= num; i ++)
        {
            result *= i;
        }
        return result;

    }
    public double LogBase(double value, double base)
    {
        return Math.log(value) / Math.log(base);
    }
    public String ResultFormat(String operator, double result)
    {
        switch (operator)
        {
            case "+":
                return "Sum: " + result;
            case "-":
                return "Difference: " + result;
            case "*":
                return "Product: " + result;
            case "/":
                return "Quotient: " + result;
            case "^":
                return "Power: " + result;
            case "√":
                return "Root: " + result;
            case "log":
                return "Log base: " + result;
            case "%":
                return "Remainder: " + result;
            case "sin":
                return "Sin: " + result;
            case "cos":
                return "Cos: " + result;
            case "tan":
                return "Tan: " + result;
            case "!":
                return "Factorial: " + result;
            case "ln":
                return "Natural log: " + result;
            default:
                return "";
        }
    }

    private void addToHistory(double a, double b, String op, double result)
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
    public void clearAll(){
        historyList.clear();
        adapter.notifyDataSetChanged();

        editNumber1.setText("");
        editNumber2.setText("");
        txtResult.setText("Results will appear here");
    }

}
