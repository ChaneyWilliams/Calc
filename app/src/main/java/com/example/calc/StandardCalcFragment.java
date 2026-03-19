package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;

public class StandardCalcFragment extends Fragment {

    //all these use the same function, and it's way easier to parse
    //what's happening when it's all assigned in a loop
    int[] buttonIds = {
            R.id.stdSin, R.id.stdCos, R.id.stdTan, R.id.stdMod,
            R.id.std7, R.id.std8, R.id.std9, R.id.stdAdd,
            R.id.std4, R.id.std5, R.id.std6, R.id.stdSub,
            R.id.std1, R.id.std2, R.id.std3, R.id.stdMult,
            R.id.std0, R.id.stdDot, R.id.stdClear, R.id.stdDiv,
            R.id.stdLParen, R.id.stdRParen
    };
    String[] buttonString = {
            "sin", "cos", "tan", "%",
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "C", "/",
            "(", ")"
    };
    private TextView inputExpression;
    private Button btnEquals, btnClear;

    public StandardCalcFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.standard_calc_layout, container, false);

        inputExpression = view.findViewById(R.id.stdResults);

        for(int i = 0 ; i < buttonIds.length; i++){
            int finalI = i;
            view.findViewById(buttonIds[i]).setOnClickListener(v ->appendToExpression(buttonString[finalI]));
        }

        btnEquals = view.findViewById(R.id.stdEquals);
        btnEquals.setOnClickListener(v -> evaluateExpression());

        btnClear = view.findViewById(R.id.stdClear);
        btnClear.setOnClickListener(v -> inputExpression.setText(""));

        return view;
    }

    private void appendToExpression(String str) {
        inputExpression.append(str);
    }

    private void evaluateExpression() {
        String expr = inputExpression.getText().toString();
        try {
            Expression expression = new ExpressionBuilder(expr).build();
            double result = expression.evaluate();
            inputExpression.setText(String.valueOf(result));
            ((MainActivity) getActivity()).addToHistoryStd(expr);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Invalid expression", Toast.LENGTH_SHORT).show();
        }
    }
}