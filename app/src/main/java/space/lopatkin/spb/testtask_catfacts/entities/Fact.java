package space.lopatkin.spb.testtask_catfacts.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Fact implements Serializable {


        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idForDb")
    int idForDb;


    @ColumnInfo(name = "id")
    String id;

    @ColumnInfo(name = "user")
    String user;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "source")
    String source;

    @ColumnInfo(name = "updatedAt")
    String updatedAt;

    @ColumnInfo(name = "createdAt")
    String createdAt;

    @Ignore
    public Fact() {
    }

    @Ignore
    public Fact(String text) {
        this.text = text;
    }

    @Ignore
    public Fact(String id, String text) {
        this.id = id;
        this.text = text;
    }


    public Fact(int idForDb, String id, String user,
                String text, String source,
                String updatedAt, String createdAt) {
        this.idForDb = idForDb;
        this.id = id;
        this.user = user;
        this.text = text;
        this.source = source;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    @Ignore
    public Fact(String id, String user,
                String text, String source,
                String updatedAt, String createdAt) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.source = source;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public int getIdForDb() {
        return idForDb;
    }

    public void setIdForDb(int idForDb) {
        this.idForDb = idForDb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Fact{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", text='" + text + '\'' +
                ", source='" + source + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
