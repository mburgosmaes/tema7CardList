package com.example.tema7cardlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tema7cardlist.Logic.LogicProducto;
import com.example.tema7cardlist.Model.Producto;
import com.example.tema7cardlist.Logic.LogicProducto;
import com.example.tema7cardlist.Model.Producto;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    //1


    private static List lstProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this)); // añade espacio arriba de la primera card
        listView.addFooterView(new View(this)); // añade espacio debajo de la última card

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        App.productoActivo = (Producto) lstProd.get(position - 1);
                        App.accion = App.INFORMACION;
                        startActivity(new Intent(getApplicationContext(), informacion.class));
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        CardAdapter listadoDeCards = new CardAdapter(getApplicationContext(), R.layout.list_item_card);

        lstProd = LogicProducto.listaProductos(this);
        if (lstProd == null) {
            Toast.makeText(this, "La base de datos está vacía.", Toast.LENGTH_LONG).show();
        } else {
            for (Object p : lstProd) {
                listadoDeCards.add(p);
            }
            listView.setAdapter(listadoDeCards);
        }

    }

    public void clicNuevo(View view) {
        App.productoActivo = new Producto();
        App.accion = App.INSERTAR;
        startActivity(new Intent(this, edicion.class));
    }
}
