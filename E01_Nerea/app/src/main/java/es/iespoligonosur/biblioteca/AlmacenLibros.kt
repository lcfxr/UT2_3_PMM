package es.iespoligonosur.biblioteca

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

// Apoyo ya preparado. No se modifica en UT2.2 ni en C2.1.
// Guarda SOLO título y autor. Es almacenamiento, no estado del formulario.
object AlmacenLibros {
    fun leer(contexto: Context): MutableList<Libro> {
        val preferencias = contexto.getSharedPreferences("biblioteca", Context.MODE_PRIVATE)
        val guardado = preferencias.getString("libros", null)
        if (guardado == null) return mutableListOf(Libro("La isla", "Ana"), Libro("El viaje", "Luis"))
        val resultado = mutableListOf<Libro>()
        val lista = JSONArray(guardado)
        for (i in 0 until lista.length()) {
            val dato = lista.getJSONObject(i)
            resultado.add(Libro(dato.getString("titulo"), dato.getString("autor")))
        }
        return resultado
    }
    fun agregar(contexto: Context, titulo: String, autor: String) {
        val libros = leer(contexto)
        libros.add(Libro(titulo, autor))
        val lista = JSONArray()
        for (libro in libros) {
            val dato = JSONObject()
            dato.put("titulo", libro.titulo)
            dato.put("autor", libro.autor)
            lista.put(dato)
        }
        contexto.getSharedPreferences("biblioteca", Context.MODE_PRIVATE)
            .edit().putString("libros", lista.toString()).apply()
    }
}
