package com.example.listapersonagens;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu", "Chun-Li"));

        ListView listadePersonagens = findViewById(R.id.activity_main_lista_personagem);
        listadePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagem));

        // TextView primeiropersonagem = findViewById(R.id.textView);
        // TextView segundopersonagem = findViewById(R.id.textView2);
        // TextView terceiropersonagem = findViewById(R.id.textView3);

        // primeiropersonagem.setText(personagem.get(0));
        //  segundopersonagem.setText(personagem.get(1));
        // terceiropersonagem.setText(personagem.get(2));
    }
}
