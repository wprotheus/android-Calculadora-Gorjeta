package com.wprotheus.androidgorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText tietValor;
    private TextView tvPorcentagem;
    private SeekBar sbPorcentagem;
    private TextView tvValorGorjeta;
    private TextView tvValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tietValor = findViewById(R.id.tietValor);
        tvPorcentagem = findViewById(R.id.tvPorcentagem);
        sbPorcentagem = findViewById(R.id.sbPorcentagem);
        tvValorGorjeta = findViewById(R.id.tvValorGorjeta);
        tvValorTotal = findViewById(R.id.tvValorTotal);

        sbPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPorcentagem.setText(String.format("%d %%", sbPorcentagem.getProgress()));
                calcularGorjeta();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void calcularGorjeta()
    {
        String valor = Objects.requireNonNull(tietValor.getText()).toString();
        double valorConta = 0.0;
        if(!valor.isEmpty())
            valorConta = Double.parseDouble(valor);
        else
            Toast.makeText(this.getApplicationContext(), "É necessário informar o valor da conta.", Toast.LENGTH_LONG).show();
        double gorjeta = (valorConta * sbPorcentagem.getProgress()) / 100;
        double valorTotal = valorConta + gorjeta;
        DecimalFormat df = new DecimalFormat("###.##");
        tvValorGorjeta.setText(String.format("R$ %s", df.format(gorjeta)));
        tvValorTotal.setText(String.format("R$ %s", df.format(valorTotal)));
    }
}