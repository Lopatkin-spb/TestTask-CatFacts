package space.lopatkin.spb.testtask_catfacts.db;


import androidx.room.*;

import space.lopatkin.spb.testtask_catfacts.entities.Fact;

import java.util.List;

@Dao
public interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFacts(List<Fact> facts);

    @Query("select * from fact")
    List<Fact> getFacts();

    @Delete
    void deleteFact(Fact fact);

    @Query("delete from fact")
    void deleteAll();

}
