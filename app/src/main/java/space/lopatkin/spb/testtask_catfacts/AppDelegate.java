package space.lopatkin.spb.testtask_catfacts;

import android.app.Application;
import androidx.room.Room;
import space.lopatkin.spb.testtask_catfacts.db.FactDatabase;

public class AppDelegate extends Application {

    //гугл рекомендует инициализировать рум через апликэйшин
    // -> манифест

    private FactDatabase factDatabase;

    public FactDatabase getFactDatabase() {
        return factDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        factDatabase = Room.databaseBuilder(
                getApplicationContext()
                , FactDatabase.class
                , "fact_database")
                .build();




    }
}
