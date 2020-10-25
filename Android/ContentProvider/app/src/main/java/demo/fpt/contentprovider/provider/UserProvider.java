package demo.fpt.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import demo.fpt.contentprovider.helper.DatabaseHelper;

public class UserProvider extends ContentProvider {
    private SQLiteDatabase db;

    public static final String PROVIDER_NAME = "demo.fpt.contentprovider.provider.UserProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/users";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String id = "id";
    public static final String name = "name";
    public static final int URI_CODE = 1;
    public static UriMatcher uriMatcher = null;
    public static final HashMap<String, String> values = null;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", URI_CODE);
        uriMatcher.addURI(PROVIDER_NAME, "users/*", URI_CODE);
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        db = databaseHelper.getWritableDatabase();
        return (db != null);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatabaseHelper.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case URI_CODE: {
                qb.setProjectionMap(values); break;
            }
            default: throw new IllegalArgumentException("Unsupported URI : " + uri);
        }

        sortOrder = (sortOrder == null || sortOrder.isEmpty()) ? id : sortOrder;

        Cursor cursor = qb.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_CODE : return "vnd.android.cursor.dir/users";
            default: throw new IllegalArgumentException("Unsupported URI : " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri,
                      @Nullable ContentValues values) {
        long rowId = db.insert(DatabaseHelper.TABLE_NAME, "", values);
        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case URI_CODE: {
                count = db.delete(DatabaseHelper.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case URI_CODE: {
                count = db.update(DatabaseHelper.TABLE_NAME,values, selection, selectionArgs);
                break;
            }
            default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
