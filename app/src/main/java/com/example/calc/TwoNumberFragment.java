package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class TwoNumberFragment extends Fragment {

    TextView txtResults;
    private EditText editNumber1, editNumber2;

    public TwoNumberFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_two_numbers_fragment, container, false);

        editNumber1 = view.findViewById(R.id.editNumber1);
        editNumber2 = view.findViewById(R.id.editNumber2);
        txtResults = view.findViewById(R.id.txtResult);

        view.findViewById(R.id.btnAdd).setOnClickListener(v -> PerformOperation("+"));
        view.findViewById(R.id.btnSub).setOnClickListener(v -> PerformOperation("-"));

        //findViewById(R.id.btnSubmit).setOnClickListener(v -> singleValue());
        view.findViewById(R.id.btnAdd).setOnClickListener(v -> PerformOperation("+"));
        view.findViewById(R.id.btnSub).setOnClickListener(v -> PerformOperation("-"));
        view.findViewById(R.id.btnMult).setOnClickListener(v -> PerformOperation("*"));
        view.findViewById(R.id.btnDiv).setOnClickListener(v -> PerformOperation("/"));
        view.findViewById(R.id.btnPower).setOnClickListener(v->PerformOperation("^"));
        view.findViewById(R.id.btnRoot).setOnClickListener(v->PerformOperation("√"));
        view.findViewById(R.id.btnMod).setOnClickListener(v->PerformOperation("%"));
        view.findViewById(R.id.btnLog).setOnClickListener(v->PerformOperation("log"));
        view.findViewById(R.id.btnSin).setOnClickListener(v->PerformOperation("sin"));
        view.findViewById(R.id.btnCos).setOnClickListener(v->PerformOperation("cos"));
        view.findViewById(R.id.btnTan).setOnClickListener(v->PerformOperation("tan"));
        view.findViewById(R.id.btnFactorial).setOnClickListener(v->PerformOperation("!"));
        view.findViewById(R.id.btnLn).setOnClickListener(v->PerformOperation("ln"));

        view.findViewById(R.id.btnClear).setOnClickListener(v -> clearInputs());


        view.findViewById(R.id.btnClear).setOnClickListener(v -> clearInputs());

        return view;
    }

public void PerformOperation(String operation)
    {
        String text1 = editNumber1.getText().toString().trim();
        String text2 = editNumber2.getText().toString().trim();

        if (text1.isEmpty() || text2.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
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

            txtResults.setText(ResultFormat(operation, result));
            ((MainActivity) getActivity()).addToHistory(value1, value2, operation, result);


        } catch (NumberFormatException e)
        {
            Toast.makeText(getActivity(), "Please Enter Valid Numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        editNumber1.setText("");
        editNumber2.setText("");
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


    public String ResultFormat(String operator, double result) {
        switch (operator) {
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
}





