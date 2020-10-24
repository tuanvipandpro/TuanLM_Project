package demo.fpt.androidsqlite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import demo.fpt.androidsqlite.model.Account;

public class MyDatabaseUtils extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Demo_DB";

    public MyDatabaseUtils(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Account(Id INTEGER PRIMARY KEY, Username TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    public void createDefaultAccount() {
        if (this.getCountAccount() == 0) {
            this.addNote(new Account("leminhtuan", "1"));
        }

    }

    public void addNote(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", account.getUsername());
        values.put("Password", account.getPassword());

        db.insert("Account", null, values);
        db.close();
    }

    public int getCountAccount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Account", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean login(String username, String password) {
        Cursor cursor = this.getReadableDatabase()
                            .rawQuery("SELECT * FROM Account WHERE Username = ? AND Password = ? ",
                                        new String[] {username, password});
        int result = cursor.getCount();
        cursor.close();

        return (result > 0);
    }
}
