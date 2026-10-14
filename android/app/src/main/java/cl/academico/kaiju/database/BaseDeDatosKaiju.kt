package cl.academico.kaiju.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto

/**
 * La base de datos local (RD-03): une las tablas de [Producto] y [Movimiento] con su DAO.
 * Los usuarios de prueba no se guardan aquí: vienen de DatosDePrueba.
 */
// Investigación Guía Room §10 (migraciones): exportSchema guarda cada versión del esquema en
// app/schemas. Si más adelante una tabla cambia, Room exige una Migration para no perder los
// datos guardados, y ese archivo permite escribirla y comprobarla.
@Database(entities = [Producto::class, Movimiento::class], version = 1, exportSchema = true)
abstract class BaseDeDatosKaiju : RoomDatabase() {
    abstract fun inventarioDao(): InventarioDao

    companion object {
        // @Volatile + synchronized: evita crear dos bases de datos a la vez.
        @Volatile private var instancia: BaseDeDatosKaiju? = null

        fun obtenerInstancia(contexto: Context): BaseDeDatosKaiju = instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(contexto.applicationContext, BaseDeDatosKaiju::class.java, "kaiju.db")
                .build().also { instancia = it }
        }
    }
}
