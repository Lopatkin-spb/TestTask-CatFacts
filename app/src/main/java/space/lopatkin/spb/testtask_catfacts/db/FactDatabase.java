package space.lopatkin.spb.testtask_catfacts.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import space.lopatkin.spb.testtask_catfacts.entities.Fact;

@Database(entities = {Fact.class}, version = 1)
public abstract class FactDatabase extends RoomDatabase {

   public abstract FactDao getFactDao();


}
