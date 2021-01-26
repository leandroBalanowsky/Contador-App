package com.example.contador2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int contador;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = 0;
        resultado = (TextView)findViewById(R.id.idContador);

        /*(1)*/
        eventoTeclado evento = new eventoTeclado();
        EditText txtReset = (EditText)findViewById(R.id.txtReset);
        txtReset.setOnEditorActionListener(evento);
    }
    public void incrementarContador(View vista)
    {
        contador++;
        resultado.setText(""+contador);

    }
    public void decrementarContador(View vista)
    {
        contador--;
        if(contador < 0)
        {
            CheckBox negativo = (CheckBox)findViewById(R.id.chkNegativos);
            if(!negativo.isChecked())
            {
                contador = 0;
            }
        }
        resultado.setText(""+contador);

    }
    public void resetearContador(View vista)
    {

        EditText reset = (EditText)findViewById(R.id.txtReset);
        try {
            contador = Integer.parseInt(reset.getText().toString());
        }
        catch (Exception e) {
            contador = 0;
        }
        reset.setText("");
        resultado.setText(""+contador);

        /*Ocultar teclado*/
        InputMethodManager teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(reset.getWindowToken(), 0);



    }
    /*Clase para Mnadar valor directo al teclado(1)*/
    class eventoTeclado implements TextView.OnEditorActionListener
    {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId == EditorInfo.IME_ACTION_DONE)
            {
                resetearContador(null);
            }
            return false;
        }
    }


}