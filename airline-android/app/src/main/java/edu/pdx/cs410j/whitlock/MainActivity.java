package edu.pdx.cs410j.whitlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final int GET_SUM_FROM_CALCULATOR = 42;
    private ArrayAdapter<Integer> sums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sums = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        ListView listView = findViewById(R.id.sums);
        listView.setAdapter(this.sums);
        listView.setOnItemClickListener((adapterView, view, index, id) -> {
            int sum = MainActivity.this.sums.getItem(index);
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra(CalculatorActivity.INITIAL_LEFT_OPERAND_VALUE, sum);
            startActivityForResult(intent, GET_SUM_FROM_CALCULATOR);
        });
    }

    public void launchCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivityForResult(intent, GET_SUM_FROM_CALCULATOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_SUM_FROM_CALCULATOR && resultCode == RESULT_OK && data != null) {
            this.sums.add(data.getIntExtra(CalculatorActivity.EXTRA_SUM, 0));
            writeSumsToDisk();
            Flight flight = (Flight) data.getSerializableExtra(CalculatorActivity.EXTRA_FLIGHT);
            Toast.makeText(this, "Flight: " + flight, Toast.LENGTH_LONG).show();
        }
    }

    private void writeSumsToDisk() {
        File file = new File(this.getFilesDir(), "sums.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < this.sums.getCount(); i++) {
                pw.println(this.sums.getItem(i));
            }

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}