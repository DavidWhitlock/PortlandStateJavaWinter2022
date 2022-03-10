package edu.pdx.cs410j.whitlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    public static final String INITIAL_LEFT_OPERAND_VALUE = "INITIAL_LEFT_OPERAND_VALUE";
    static final String EXTRA_SUM = "SUM";
    static final String EXTRA_FLIGHT = "FLIGHT";
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        int initialLeftOperand;
        if (intent.hasExtra(INITIAL_LEFT_OPERAND_VALUE)) {
            initialLeftOperand = intent.getIntExtra(INITIAL_LEFT_OPERAND_VALUE, 0);
            EditText leftOperand = findViewById(R.id.leftOperand);
            leftOperand.setText(String.valueOf(initialLeftOperand));
        }
    }


    public void calculateSum(View view) {
        EditText leftOperand = findViewById(R.id.leftOperand);
        EditText rightOperand = findViewById(R.id.rightOperand);

        int left;
        int right;
        try {
            left = Integer.parseInt(leftOperand.getText().toString());
            right = Integer.parseInt(rightOperand.getText().toString());

        } catch (NumberFormatException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        this.sum = left + right;

        TextView sumField = findViewById(R.id.sum);
        sumField.setText(String.valueOf(sum));
    }

    public void returnToMain(View view) {
        Intent data = new Intent();
        data.putExtra(EXTRA_SUM, this.sum);
        data.putExtra(EXTRA_FLIGHT, new Flight(this.sum));
        setResult(RESULT_OK, data);
        finish();
    }
}