package es.iespoligonosur.biblioteca

import android.content.Context
import org.json.JSONArray
import org.json.JSONException

// Solo lectura local; no hace peticiones de red.
object LectorPruebas {
    fun leer(contexto: Context, archivo: String): List<Libro> {
        val texto = contexto.assets.open(archivo).bufferedReader().use { it.readText() }
        val datos = JSONArray(texto)
        val resultado = mutableListOf<Libro>()
        for (i in 0 until datos.length()) {
            val dato = datos.getJSONObject(i)
            val titulo = dato.getString("titulo").trim()
            val autor = dato.getString("autor").trim()
            if (titulo.isBlank() || autor.isBlank()) {
                throw JSONException("Falta título o autor")
            }
            resultado.add(Libro(titulo, autor))
        }
        return resultado
    }
}
