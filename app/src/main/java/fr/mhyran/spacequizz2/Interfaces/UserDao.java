package fr.mhyran.spacequizz2.Interfaces;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import fr.mhyran.spacequizz2.Entity.User;

@Dao
public interface UserDao{

 @Query("SELECT COUNT(*) FROM " + User.TABLE_NAME)
 int count();

 @Query("SELECT score from User where pseudo=(:pseudo)")
 int scoredb(String pseudo);

 @Query("SELECT * FROM " + User.TABLE_NAME)
 List<User>getAllUsers();

 @Query("UPDATE User SET score = :score WHERE pseudo=(:pseudo)")
 void updateScore(String pseudo, int score);

 @Insert
 void instarAll(User ... users);

 @Insert
 void registerUser(User user);

 @Query("SELECT * from User where pseudo=(:pseudo) and password=(:password)")
User login(String pseudo, String password);

 @Query("DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_ID + " = :id")
 int deleteById(long id);

 @Update
 int updateEntity(User obj);

 @Insert
 long insert(User users);

}
