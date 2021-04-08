package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");

        dao.salva(new Personagem("Ken","1,80","02041999"));
        dao.salva(new Personagem("Ryu","1,80","02041999"));

        configuraFacNovoPersonagem();

    }

    private void configuraFacNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.floatingActionButton2);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listadePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagems = dao.todos();
        listadePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagems));
        listadePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagems.get(posicao);
                Intent VaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                VaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(VaiParaFormulario);
            }
        });

    }
}
