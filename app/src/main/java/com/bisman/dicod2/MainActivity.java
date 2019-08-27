package com.bisman.dicod2;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLength;
    private Button btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_calculate ) {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true;
                edtHeight.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            Double panjang = toDouble(inputLength);
            Double lebar = toDouble(inputWidth);
            Double tinggi = toDouble(inputHeight);

            if (panjang == null){
                isEmptyFields = true;
                edtLength.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            if (lebar == null){
                isEmptyFields = true;
                edtWidth.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            if (tinggi == null){
                isEmptyFields = true;
                edtHeight.setError("TIDAK BOLEH KOSONG MAMANK");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = panjang * lebar * tinggi;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str){
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }
}