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

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();

    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.floatingActionButton2);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioSalva();

            }

        });
    }

    private void abreFormularioSalva() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();

    }

    private void configuraLista() {
        ListView listadePersonagens = findViewById(R.id.activity_main_lista_personagem);
        final List<Personagem> personagems = dao.todos();
        listaDePersonagens(listadePersonagens, personagems);
        configuraItemPorClique(listadePersonagens, personagems);
    }

    private void configuraItemPorClique(ListView listadePersonagens, List<Personagem> personagems) {
        listadePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                AbreFormularioModoEditar(personagemEscolhido);
            }
        });
    }

    private void AbreFormularioModoEditar(Personagem personagem) {
        Intent VaiParaFormulario = new Intent(this, FormularioPersonagemActivity.class);
        VaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(VaiParaFormulario);
    }

    private void listaDePersonagens(ListView listadePersonagens, List<Personagem> personagems) {
        listadePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagems));
    }
}
