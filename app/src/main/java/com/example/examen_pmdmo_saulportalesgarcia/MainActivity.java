package com.example.examen_pmdmo_saulportalesgarcia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText valueAltura=findViewById(R.id.editAltura);
        EditText valueAnioNacimiento=findViewById(R.id.editYear);
        EditText valuePeso=findViewById(R.id.editTextNumber2);
        RadioButton radioMujer=findViewById(R.id.rbWomen);
        RadioButton radioHombre=findViewById(R.id.rbMen);
        RadioButton radioPerrault=findViewById(R.id.rbPerrault);
        RadioButton radioBroca=findViewById(R.id.rbBroca);
        RadioButton radioWanDerVael=findViewById(R.id.rbWanDerVael);
        Button botonCalcular=findViewById(R.id.btnCalculate);
        ImageView imagen=findViewById(R.id.imgResult);
        TextView resultPesoIdeal=(findViewById(R.id.textResult));

        botonCalcular.setOnClickListener(view -> {
            String alturaString = valueAltura.getText().toString();
            String anioNacimientoString = valueAnioNacimiento.getText().toString();
            String pesoString = valuePeso.getText().toString();
            if(!alturaString.isEmpty()){
                if(!anioNacimientoString.isEmpty()){
                    if(!pesoString.isEmpty()){
                        // Obtener el año actual
                        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
                        // Convertir el año de nacimiento a entero
                        int anioNacimiento = Integer.parseInt(anioNacimientoString);
                        // Calcular la edad
                        int edad = anioActual - anioNacimiento;
                        if(radioMujer.isChecked()){
                            ///Calcula el peso ideal
                            double pesoIdeal=0;
                            if(radioPerrault.isChecked()){
                                pesoIdeal=Double.parseDouble(alturaString) - 100 +
                                        (edad / 10f) * 0.9;
                                resultPesoIdeal.setText("Peso ideal: "+pesoIdeal);
                            } else if (radioBroca.isChecked()) {
                                pesoIdeal=Double.parseDouble(alturaString)-100;

                                resultPesoIdeal.setText(String.format("Peso ideal: %.0f",pesoIdeal));
                            } else if (radioWanDerVael.isChecked()) {
                                pesoIdeal=(Double.parseDouble(alturaString)-150)*0.6+50;
                                resultPesoIdeal.setText("Peso ideal: "+pesoIdeal);
                            }
                            ///Mete la imagen del peso actual
                            // Diferencia absoluta entre peso real e ideal
                            double porcentajeDiferencia=porcentajeDiferenciaPeso(Double.parseDouble(pesoString),pesoIdeal);
                            // Comprobamos si la diferencia es mayor al 10%
                            if (porcentajeDiferencia > 10) {
                                imagen.setImageResource(R.drawable.angrygirl);
                            }else{
                                imagen.setImageResource(R.drawable.happygirl);
                            }
                        } else if (radioHombre.isChecked()) {
                            ///Calcula el peso ideal
                            double pesoIdeal=0;
                            if(radioPerrault.isChecked()){
                                pesoIdeal=Double.parseDouble(alturaString) - 100 +
                                        ((Double.parseDouble(String.valueOf(edad)) / 10) * 0.9);
                                resultPesoIdeal.setText("Peso ideal: "+pesoIdeal);
                            } else if (radioBroca.isChecked()) {
                                pesoIdeal=Double.parseDouble(alturaString)-100;
                                resultPesoIdeal.setText("Peso ideal: "+pesoIdeal);
                            } else if (radioWanDerVael.isChecked()) {
                                pesoIdeal=(Double.parseDouble(alturaString)-150)*0.75+50;
                                resultPesoIdeal.setText("Peso ideal: "+pesoIdeal);
                            }
                            ///Mete la imagen del peso actual
                            // Diferencia absoluta entre peso real e ideal
                            double porcentajeDiferencia=porcentajeDiferenciaPeso(Double.parseDouble(pesoString),pesoIdeal);
                            // Comprobamos si la diferencia es mayor al 10%
                            if (porcentajeDiferencia > 10) {
                                imagen.setImageResource(R.drawable.angrymen);
                            }else{
                                imagen.setImageResource(R.drawable.happymen);
                            }
                        }
                    }else{
                        System.out.println("No has introducido ningun peso");                    }
                }else{
                    System.out.println("No has introducido ningun anio de nacimiento");
                }

            }else{
                System.out.println("No has intoducido ninguna altura");
            }
        });

    }
    public double porcentajeDiferenciaPeso(double pesoActual,double pesoIdeal){
        double diferencia = pesoActual - pesoIdeal;
        return (diferencia / pesoIdeal) * 100;
    }
}