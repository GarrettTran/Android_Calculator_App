package rmit.ad.lecture1; // Change to your package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure this matches your XML filename

        screen = findViewById(R.id.screen);

        // Number buttons
        setupButton(R.id.num0, "0");
        setupButton(R.id.num1, "1");
        setupButton(R.id.num2, "2");
        setupButton(R.id.num3, "3");
        setupButton(R.id.num4, "4");
        setupButton(R.id.num5, "5");
        setupButton(R.id.num6, "6");
        setupButton(R.id.num7, "7");
        setupButton(R.id.num8, "8");
        setupButton(R.id.num9, "9");
        setupButton(R.id.dot, ".");

        // Operator buttons
        setupOperatorButton(R.id.plus, "+");
        setupOperatorButton(R.id.minus, "-");
        setupOperatorButton(R.id.div, "/");
        setupOperatorButton(R.id.time, "*");
        setupButton(R.id.equal, "=");

        // On and Off buttons
        setupButton(R.id.on, "ON", true);
        setupButton(R.id.off, "OFF", true);
        setupButton(R.id.ac, "AC");
        setupButton(R.id.del, "DEL");
    }

    private void setupButton(int buttonId, String value) {
        setupButton(buttonId, value, false);
    }

    private void setupButton(int buttonId, String value, boolean isToggle) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(view -> {
            if (isToggle) {
                if (value.equals("ON")) {
                    screen.setText("Calculator is ON");
                } else {
                    screen.setText("Calculator is OFF");
                    resetCalculator();
                }
            } else if (value.equals("DEL")) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                }
                screen.setText(currentInput.isEmpty() ? "0" : currentInput);
            } else if (value.equals("AC")) {
                resetCalculator();
                screen.setText("0");
            } else if (value.equals("=")) {
                calculateResult();
            } else {
                currentInput += value;
                screen.setText(currentInput);
            }
        });
    }

    private void setupOperatorButton(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(view -> {
            if (!currentInput.isEmpty()) {
                firstValue = Double.parseDouble(currentInput);
                operator = op;
                currentInput = "";
            }
        });
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondValue = Double.parseDouble(currentInput);
            double result;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue == 0) {
                        screen.setText("Error");
                        resetCalculator();
                        return;
                    }
                    result = firstValue / secondValue;
                    break;
                default:
                    return;
            }

            currentInput = String.valueOf(result);
            screen.setText(currentInput);
            operator = ""; // Reset operator after calculation
        }
    }

    private void resetCalculator() {
        currentInput = "";
        operator = "";
        firstValue = 0;
    }
}
