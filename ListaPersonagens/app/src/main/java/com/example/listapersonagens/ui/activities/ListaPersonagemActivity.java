package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");




        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.floatingActionButton2);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));

            }
        });

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Chun-Li"));



        // TextView primeiropersonagem = findViewById(R.id.textView);
        // TextView segundopersonagem = findViewById(R.id.textView2);
        // TextView terceiropersonagem = findViewById(R.id.textView3);

        // primeiropersonagem.setText(personagem.get(0));
        //  segundopersonagem.setText(personagem.get(1));
        // terceiropersonagem.setText(personagem.get(2));
    }

    @Override
    protected void onResume() {
        super.onResume();

        PersonagemDAO dao = new PersonagemDAO();
        ListView listadePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagems = dao.todos();
        listadePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagems));
        listadePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagems.get(posicao);
                //Log.i("posição", "" + personagemEscolhido);
                Intent VaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                startActivity(VaiParaFormulario);
            }
        });

    }
}
