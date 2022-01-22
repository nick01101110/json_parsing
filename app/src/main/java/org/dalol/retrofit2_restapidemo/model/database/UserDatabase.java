
package org.dalol.retrofit2_restapidemo.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.dalol.retrofit2_restapidemo.model.helper.Constants;
import org.dalol.retrofit2_restapidemo.model.pojo.Friend;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String TAG = UserDatabase.class.getSimpleName();

    public UserDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_TAGS_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_FRIENDS_TABLE_QUERY);
        } catch (SQLException ex) {
            Log.e(TAG, "Create error", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(Constants.DATABASE.DROP_QUERY);
            db.execSQL(Constants.DATABASE.DROP_QUERY_TAGS);
            db.execSQL(Constants.DATABASE.DROP_QUERY_FRIENDS);
            this.onCreate(db);
        }catch (SQLException ex){
            Log.e(TAG, "Update error", ex);
            throw new RuntimeException(ex);
        }
    }

    public void addUsers(List<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            truncateTables(db);
            for (User user: users) {
                ContentValues values = new ContentValues();
                values.put(Constants.DATABASE.ID, user.getId());
                values.put(Constants.DATABASE.ISACTIVE, user.isActive() ? 1 : 0);
                values.put(Constants.DATABASE.BALANCE, user.getBalance());
                values.put(Constants.DATABASE.AGE, user.getAge());
                values.put(Constants.DATABASE.EYECOLOR, user.getEyeColor());
                values.put(Constants.DATABASE.NAME, user.getName());
                values.put(Constants.DATABASE.GENDER, user.getGender());
                values.put(Constants.DATABASE.COMPANY, user.getCompany());
                values.put(Constants.DATABASE.EMAIL, user.getEmail());
                values.put(Constants.DATABASE.PHONE, user.getPhone());
                values.put(Constants.DATABASE.ADDRESS, user.getAddress());
                values.put(Constants.DATABASE.ABOUT, user.getAbout());
                values.put(Constants.DATABASE.REGISTERED, user.getRegistered());
                values.put(Constants.DATABASE.FAVORITEFRUIT, user.getFavoriteFruit());
                values.put(Constants.DATABASE.PICTURE, user.getPicture());
                long id = db.insert(Constants.DATABASE.TABLE_NAME, null, values);

                for (String tag: user.getTags()){
                    ContentValues tagValues = new ContentValues();
                    tagValues.put(Constants.DATABASE.TAG_USERID, id);
                    tagValues.put(Constants.DATABASE.TAG_NAME, tag);
                    db.insert(Constants.DATABASE.TAG_TABLE_NAME, null, tagValues);
                }

                for (Friend friend: user.getFriends()){
                    ContentValues tagValues = new ContentValues();
                    tagValues.put(Constants.DATABASE.FRIEND_USERID, id);
                    tagValues.put(Constants.DATABASE.FRIEND_NAME, friend.getName());
                    db.insert(Constants.DATABASE.FRIEND_TABLE_NAME, null, tagValues);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            throw new RuntimeException(e);
        }
        db.close();
    }

    public List<User> fetchUsers(){
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(Constants.DATABASE.GET_USERS_QUERY, null);
            List<User> users = new ArrayList<>();
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                String prevId = null;
                String prevTag = null;
                List<String> tags = new ArrayList<>();
                List<Friend> friends = new ArrayList<>();
                do {
                    String userId = cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ID));
                    String tag = cursor.getString(cursor.getColumnIndex("tag_name"));
                    User user;
                    if (userId.equals(prevId)) {
                        if (users.size() > 0) {
                            user = users.get(users.size() - 1);
                            user.setTags(tags.toArray(new String[0]));
                            user.setFriends(friends.toArray(new Friend[0]));
                        }
                        if (tag.equals(prevTag)) {
                            Friend friend = new Friend();
                            friend.setName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.FRIEND_NAME)));
                            friends.add(friend);
                        } else {
                            tags.add(tag);
                            prevTag = tag;
                        }
                    } else {
                        tags = new ArrayList<>();
                        user = new User();
                        user.setFromDatabase(true);
                        user.setId(userId);
                        user.setActive(cursor.getInt(cursor.getColumnIndex(Constants.DATABASE.ISACTIVE)) != 0);
                        user.setBalance(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.BALANCE)));
                        user.setPicture(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PICTURE)));
                        user.setAge(cursor.getInt(cursor.getColumnIndex(Constants.DATABASE.AGE)));
                        user.setEyeColor(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.EYECOLOR)));
                        user.setName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.NAME)));
                        user.setGender(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.GENDER)));
                        user.setCompany(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.COMPANY)));
                        user.setEmail(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.EMAIL)));
                        user.setPhone(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PHONE)));
                        user.setAddress(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ADDRESS)));
                        user.setAbout(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.ABOUT)));
                        user.setRegistered(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.REGISTERED)));
                        user.setFavoriteFruit(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.FAVORITEFRUIT)));
                        prevId = userId;
                        users.add(user);
                        tags.add(tag);
                    }
                } while (cursor.moveToNext());
                users.get(users.size() - 1).setTags(tags.toArray(new String[0]));
                users.get(users.size() - 1).setFriends(friends.toArray(new Friend[0]));
            }
            cursor.close();

            return users;
        }catch (Exception e){
            Log.e(TAG, "An error has occurred", e);
            throw new RuntimeException(e);
        }
    }

    private void truncateTables(SQLiteDatabase db){
        db.execSQL(Constants.DATABASE.TRUNCATE_USERS);
        db.execSQL(Constants.DATABASE.TRUNCATE_TAGS);
        db.execSQL(Constants.DATABASE.TRUNCATE_FRIENDS);

    }
}
