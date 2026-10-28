package es.iespoligonosur.biblioteca

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnAnadir).setOnClickListener {
            val destino = Intent(this, FormularioActivity::class.java)
            startActivity(destino)
        }
    }

    override fun onResume() {
        super.onResume()
        // Actualiza el listado cuando volvemos de guardar un libro.
        val libros = AlmacenLibros.leer(this)
        val lista = findViewById<ListView>(R.id.listaLibros)
        lista.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            libros.map { it.titulo + " - " + it.autor })
        lista.setOnItemClickListener { _, _, posicion, _ ->
            val libro = libros[posicion]
            val destino = Intent(this, DetalleActivity::class.java)
            destino.putExtra("titulo", libro.titulo)
            destino.putExtra("autor", libro.autor)
            startActivity(destino)
        }
    }
}
