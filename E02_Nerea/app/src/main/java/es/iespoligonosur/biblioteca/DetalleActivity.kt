package es.iespoligonosur.biblioteca

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetalleActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        val titulo = intent.getStringExtra("titulo") ?: getString(R.string.sin_titulo)
        val autor = intent.getStringExtra("autor") ?: getString(R.string.sin_autor)
        findViewById<TextView>(R.id.textoTitulo).text = titulo
        findViewById<TextView>(R.id.textoAutor).text = autor
        findViewById<Button>(R.id.btnVolver).setOnClickListener { finish() }
    }
}
