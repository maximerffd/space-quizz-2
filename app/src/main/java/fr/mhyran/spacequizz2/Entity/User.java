package fr.mhyran.spacequizz2.Entity;
import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = User.TABLE_NAME)
public class User {

    /** The name of the Cheese table. */
    public static final String TABLE_NAME = "user";

    public static final String COLUMN_NAME = "name";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = "pseudo")
    private String pseudo;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "score")
    private Integer score;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    @Ignore
    public User() {
    }

    public User(long id, String pseudo, String password, Integer score) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.score = score;

    }

    public static User fromContentValues(ContentValues values) {
        final User obj = new User();
        if (values.containsKey(COLUMN_ID)) {
            obj.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            obj.setId(1);
        }
        return obj;
    }
}
