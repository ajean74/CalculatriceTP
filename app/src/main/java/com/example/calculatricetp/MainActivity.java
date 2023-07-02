package com.example.calculatricetp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCalculation;
    private TextView textViewResult;

    private StringBuilder currentNumber;
    private double operand1;
    private double operand2;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCalculation = findViewById(R.id.textViewCalculation);
        textViewResult = findViewById(R.id.textViewResult);

        currentNumber = new StringBuilder();
        currentNumber.append("0"); // Valeur initiale

        textViewCalculation.setText("");
        textViewResult.setText("0");

        // Définition des listeners pour les boutons numériques
        setNumericButtonListeners();

        // Définition des listeners pour les boutons d'opération
        setOperatorButtonListeners();

        // Définition du listener pour le bouton égal
        Button btnEvaluate = findViewById(R.id.btnEvaluate);
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        // Définition du listener pour le bouton Clear
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculator();
            }
        });

        // Définition du listener pour le bouton Plus/Minus
        Button btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invertSign();
            }
        });

        // Définition du listener pour le bouton Pourcent
        Button btnPourcent = findViewById(R.id.btnPourcent);
        btnPourcent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePercentage();
            }
        });
    }

    public void onDecimalButtonClick(View view) {
        if (!currentNumber.toString().contains(".")) {
            appendDecimal();
        }
    }

    private void appendDecimal() {
        currentNumber.append(".");
        currentExpression = currentExpression + ".";
        textViewCalculation.setText(currentExpression);
    }


    private void setNumericButtonListeners() {
        // Définition des listeners pour les boutons numériques (0-9)
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            final int digit = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appendDigit(digit);
                }
            });
        }
    }

    private void setOperatorButtonListeners() {
        // Définition des listeners pour les boutons d'opération (+, -, *, /)
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("+");
            }
        });

        Button btnSubtract = findViewById(R.id.btnSubtract);
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("-");
            }
        });

        Button btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("*");
            }
        });

        Button btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("/");
            }
        });
    }

    private String currentExpression = "";

    private void appendDigit(int digit) {
        currentNumber.append(digit);
        currentExpression = currentExpression + digit;
        textViewCalculation.setText(currentExpression);
    }


    private void setOperator(String operator) {
        this.operator = operator;
        operand1 = Double.parseDouble(currentNumber.toString());
        currentNumber.setLength(0); // Réinitialiser le StringBuilder
        currentExpression = currentExpression + operator;
        textViewCalculation.setText(currentExpression);
    }


    private void calculateResult() {
        operand2 = Double.parseDouble(currentNumber.toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }

        currentNumber.setLength(0); // Réinitialiser le StringBuilder
        currentNumber.append(result);
        currentExpression = "";
        textViewCalculation.setText(currentExpression);
        textViewResult.setText(String.valueOf(result));
    }


    private void clearCalculator() {
        currentNumber.setLength(0); // Réinitialiser le StringBuilder
        currentNumber.append("0"); // Remettre à zéro
        operand1 = 0;
        operand2 = 0;
        operator = "";
        currentExpression = "";
        textViewCalculation.setText(currentExpression);
        textViewResult.setText("0");
    }

    private void invertSign() {
        if (currentNumber.length() > 0) {
            double number = Double.parseDouble(currentNumber.toString());
            number = -number;
            currentNumber.setLength(0);
            currentNumber.append(String.valueOf(number));
            textViewCalculation.setText(currentNumber.toString());
        }
    }

    private void calculatePercentage() {
        if (currentNumber.length() > 0) {
            double number = Double.parseDouble(currentNumber.toString());
            double percentage = number / 100.0;
            currentNumber.setLength(0);
            currentNumber.append(String.valueOf(percentage));
            textViewCalculation.setText(currentNumber.toString());
        }
    }
}
