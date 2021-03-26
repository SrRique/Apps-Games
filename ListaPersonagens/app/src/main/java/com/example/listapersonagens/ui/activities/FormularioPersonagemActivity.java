package com.example.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

import java.io.Serializable;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private  final  PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulário de Personagens");

        PersonagemDAO dao = new PersonagemDAO();

         campoNome = findViewById(R.id.editText_nome);
         campoAltura = findViewById(R.id.editText_altura);
         campoNascimento = findViewById(R.id.editText_nascimento);


        Button botaosalvar = findViewById(R.id.savebutton);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem  personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);
                finish();


               // startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.edita(personagemSalvo);

                /*Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - "  + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),
                        Toast.LENGTH_SHORT).show();*/
                //new Personagem(nome, altura, nascimento);

                //Toast.makeText(FormularioPersonagemActivity.this,"Estou Funcionando!",Toast.LENGTH_SHORT).show();
            }
        });
        Intent dados = getIntent();
        Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }
}