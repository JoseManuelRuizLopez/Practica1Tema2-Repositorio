package com.example.practica1tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtApellidos;
    EditText txtEdad;
    RadioButton rbhombre;
    RadioButton rbMujer;
    Spinner spnEstadoCivil;
    Switch swHijos;
    Button btnComprobar;
    Button btnBorrar;
    TextView txtComprobar;

    String aviso;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        rbhombre = (RadioButton) findViewById(R.id.rbHombre);
        rbMujer = (RadioButton) findViewById(R.id.rbMujer);
        spnEstadoCivil = (Spinner) findViewById(R.id.spEstadoCivil);
        swHijos = (Switch) findViewById(R.id.swHijos);
        btnComprobar = (Button) findViewById(R.id.btnComprobar);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        txtComprobar = (TextView) findViewById(R.id.txtComprobar);

        //Spinner
        ArrayAdapter adaptadorSpinner;
        adaptadorSpinner = ArrayAdapter.createFromResource(this, R.array.estadoCivil, R.layout.support_simple_spinner_dropdown_item);

        final Spinner spnEstadocivil;
        spnEstadocivil = findViewById(R.id.spEstadoCivil);
        spnEstadocivil.setAdapter(adaptadorSpinner);

        //Comprobar los campos
        btnComprobar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                String edad ="";
                String hijos;
                String genero;

                //Mayorio de edad
                try {
                    if (Integer.parseInt(txtEdad.getText().toString()) < 18) {
                        edad = getString(R.string.menorEdad);
                    } else {
                        edad = getString(R.string.mayorEdad);
                    }
                }
                catch(NumberFormatException nfe)
                {

                }

                //Tiene o no hijos
                if(swHijos.isChecked())
                {
                    hijos = getString(R.string.conHijos);
                }
                else
                {
                    hijos = getString(R.string.sinHijos);
                }

                //Genero
                if(rbhombre.isChecked())
                {
                    genero = getString(R.string.hombre);
                }
                else
                {
                    genero = getString(R.string.mujer);
                }


                //Datos correctos
                resultado = txtApellidos.getText().toString() + ", " + txtNombre.getText().toString() + ", " + edad + ", " +
                         genero + " " + spnEstadocivil.getSelectedItem().toString() + " " + hijos + ".";

                txtComprobar.setTextColor(getColor(R.color.negro));
                txtComprobar.setText(resultado);


                //Falta algun dato
                aviso = "";
                Boolean fallo = false;

                if(txtNombre.getText().toString().equals(""))
                {
                    aviso = aviso + getString(R.string.fNombre) + " ";
                    fallo = true;
                }
                if(txtApellidos.getText().toString().equals(""))
                {
                    aviso = aviso +  getString(R.string.fApellidos) + " ";
                    fallo = true;
                }
                if(txtEdad.getText().toString().equals(""))
                {
                    aviso = aviso +  getString(R.string.fEdad) + " ";
                    fallo = true;
                }
                if(rbhombre.isChecked() == false && rbMujer.isChecked() == false)
                {
                    aviso = aviso +  getString(R.string.fGenero) + " ";
                    fallo = true;
                }
                if(spnEstadocivil.getSelectedItemPosition() == 0)
                {
                    aviso = aviso +  getString(R.string.fEstadoCivil) + " ";
                    fallo = true;
                }

                if(fallo == true)
                {
                    txtComprobar.setTextColor(getColor(R.color.rojo));

                    txtComprobar.setText(aviso);
                }
            }
        });

        //Borrar los datos con el botón
        btnBorrar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText(R.string.txtNombre);
                txtApellidos.setText(R.string.txtApellidos);
                txtEdad.setText(R.string.txtEdad);
                spnEstadocivil.setSelection(0);
                rbhombre.setChecked(false);
                rbMujer.setChecked(false);
                swHijos.setChecked(false);
                txtComprobar.setText(R.string.txtComprobar);
            }
        });
    }
}
