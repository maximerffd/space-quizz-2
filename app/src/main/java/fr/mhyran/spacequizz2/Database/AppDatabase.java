package fr.mhyran.spacequizz2.Database;

import android.app.Person;
//import android.arch.persistence.room.Room;
import android.content.Context;
//import android.support.annotation.VisibleForTesting;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import fr.mhyran.spacequizz2.Config.Constant;
import fr.mhyran.spacequizz2.Entity.User;
import fr.mhyran.spacequizz2.Interfaces.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    @SuppressWarnings("WeakerAccess")

    //que permisos va tener listar, eliminar, update, insertar
    private static AppDatabase sInstance; //variable
    public static synchronized AppDatabase getAppDatabase(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, Constant.BD_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }
    public abstract UserDao userDao();
}

