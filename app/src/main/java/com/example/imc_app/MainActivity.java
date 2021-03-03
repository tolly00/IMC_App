package com.example.imc_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    // La chaîne de caractères par défaut
    private final String defaut = "Vous devez cliquer sur le bouton « Calculer l'IMC » pour obtenir un résultat.";
    // La chaîne de caractères de la megafonction
    private final String megaString = "Vous faites un poids parfait ! Wahou ! Trop fort ! On dirait Brad Pitt (si vous êtes un homme)/Angelina Jolie (si vous êtes une femme)/Willy (si vous êtes un orque) !";


    //declaration
    Button btn_calcul=null;
    Button btn_raz=null;

    EditText t_poid=null;
    EditText t_taille=null;

    RadioGroup btn_group=null;
    TextView result=null;
    CheckBox mega=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recuperation des vues
        btn_calcul=(Button)findViewById(R.id.calcul);
        btn_raz=(Button)findViewById(R.id.raz);

        t_poid=(EditText)findViewById(R.id.poids);
        t_taille=(EditText)findViewById(R.id.taille);

        mega=(CheckBox)findViewById(R.id.mega);
        btn_group=(RadioGroup)findViewById(R.id.group);

        result=(TextView)findViewById(R.id.result);

        //attribution listener aux vues
        btn_calcul.setOnClickListener(listener_calcul);
        btn_raz.setOnClickListener(listener_raz);
        t_taille.addTextChangedListener(textWatcher);
        t_poid.addTextChangedListener(textWatcher);

        mega.setOnClickListener(checkedListener);


    }
    private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //evenement sur le bonton calculer
    private View.OnClickListener listener_calcul= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!mega.isChecked()) {
                // Si la megafonction n'est pas activée
                // On récupère la taille
                String t = t_taille.getText().toString();
                // On récupère le poids
                String p = t_poid.getText().toString();

                float tValue = Float.valueOf(t);

                // Puis on vérifie que la taille est cohérente
                if(tValue == 0)
                    Toast.makeText(MainActivity.this, "Hého, tu es un Minipouce ou quoi ?", Toast.LENGTH_SHORT).show();
                else {
                    float pValue = Float.valueOf(p);
                    // Si l'utilisateur a indiqué que la taille était en centimètres
                    // On vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant
                    if(btn_group.getCheckedRadioButtonId() == R.id.radio2)
                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText("Votre IMC est " + String.valueOf(imc));
                }
            } else
                result.setText(megaString);
        }

    };
    // Listener du bouton de remise à zéro
    private View.OnClickListener listener_raz = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            t_poid.getText().clear();
            t_taille.getText().clear();
            result.setText(defaut);
        }
    };

    // Listener du bouton de la megafonction.
    private View.OnClickListener checkedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On remet le texte par défaut si c'était le texte de la megafonction qui était écrit
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);
        }
    };
}