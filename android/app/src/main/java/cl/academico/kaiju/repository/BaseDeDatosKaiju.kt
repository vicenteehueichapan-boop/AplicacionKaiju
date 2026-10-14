package cl.academico.kaiju.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto

/** Base de datos local de la app (RD-03). Los usuarios de prueba no se guardan: vienen de [DatosDePrueba]. */
@Database(entities = [Producto::class, Movimiento::class], version = 1)
abstract class BaseDeDatosKaiju : RoomDatabase() {
    abstract fun inventarioDao(): InventarioDao

    companion object {
        @Volatile private var instancia: BaseDeDatosKaiju? = null

        /** Una sola instancia para toda la app. */
        fun obtener(context: Context): BaseDeDatosKaiju = instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(context.applicationContext, BaseDeDatosKaiju::class.java, "kaiju.db")
                .build().also { instancia = it }
        }
    }
}
