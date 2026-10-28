package es.iespoligonosur.biblioteca

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FormularioActivity : Activity() {
    // PASO A: propiedad de la clase, fuera de onCreate.
    private var revisado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        // PASO B: recuperar la marca antes de mostrarla.
        revisado = savedInstanceState
            ?.getBoolean("revisado", false) ?: false
        val campoTitulo = findViewById<EditText>(R.id.campoTitulo)
        val campoAutor = findViewById<EditText>(R.id.campoAutor)
        // C2.1: localizar aquí el campo nuevo.
        mostrarRevision()
        findViewById<Button>(R.id.btnRevisar).setOnClickListener {
            revisado = true
            mostrarRevision()
        }
        findViewById<Button>(R.id.btnCancelar).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            val titulo = campoTitulo.text.toString().trim()
            val autor = campoAutor.text.toString().trim()
            campoTitulo.error = null
            campoAutor.error = null
            // C2.1: leer y limpiar el error del campo nuevo.
            if (titulo.length < 2) {
                campoTitulo.error = getString(R.string.error_titulo)
                campoTitulo.requestFocus()
                return@setOnClickListener
            }
            if (autor.isBlank()) {
                campoAutor.error = getString(R.string.error_autor)
                campoAutor.requestFocus()
                return@setOnClickListener
            }
            // C2.1: validar aquí el campo nuevo antes de guardar.
            // Todos los campos obligatorios deben ser válidos antes de aquí.
            AlmacenLibros.agregar(this, titulo, autor)
            Toast.makeText(this, R.string.guardado, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // Esta función actualiza únicamente el mensaje de revisión.
    private fun mostrarRevision() {
        val texto = findViewById<TextView>(R.id.txtRevision)
        if (revisado) {
            texto.setText(R.string.revisado)
        } else {
            texto.setText(R.string.pendiente)
        }
    }

    // PASO C: guardar la marca. Este método va fuera de onCreate.
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("revisado", revisado)
        super.onSaveInstanceState(outState)
    }
}
