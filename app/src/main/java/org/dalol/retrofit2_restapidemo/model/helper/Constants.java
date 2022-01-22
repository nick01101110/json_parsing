

package org.dalol.retrofit2_restapidemo.model.helper;




public class Constants {

    public static final class HTTP {
        public static final String BASE_URL = "http://www.mocky.io";
    }

    public static final class DATABASE {

        /**
                id
                isActive
                balance
                picture
                age
                eyeColor
                name
                gender
                company
                email
                phone
                address
                about
                registered
                favoriteFruit

        **/

        public static final String DB_NAME = "users";
        public static final int DB_VERSION = 5;
        public static final String TABLE_NAME = "user";

        public static final String ID = "id";
        public static final String ISACTIVE = "isactive";
        public static final String BALANCE = "balance";
        public static final String PICTURE = "picture";
        public static final String AGE = "age";
        public static final String EYECOLOR = "eyecolor";
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String COMPANY = "company";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String ABOUT = "about";
        public static final String REGISTERED = "registered";
        public static final String FAVORITEFRUIT = "favoritefruit";

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
                "(" +
                ID + " TEXT not null," +
                ISACTIVE + " TEXT not null," +
                BALANCE + " TEXT not null," +
                PICTURE + " TEXT not null," +
                AGE + " TEXT not null," +
                EYECOLOR + " TEXT not null," +
                NAME + " TEXT not null," +
                GENDER + " TEXT not null," +
                COMPANY + " TEXT not null," +
                EMAIL + " TEXT not null," +
                PHONE + " TEXT not null," +
                ADDRESS + " TEXT not null," +
                ABOUT + " TEXT not null," +
                REGISTERED + " TEXT not null," +
                FAVORITEFRUIT + " TEXT not null)" ;

        public static final String TAG_TABLE_NAME = "tags";
        public static final String TAG_USERID = "userid";
        public static final String TAG_NAME = "name";

        public static final String CREATE_TAGS_TABLE_QUERY = "CREATE TABLE "+ TAG_TABLE_NAME + " (" +
            TAG_USERID + " INTEGER NOT NULL, " +
            TAG_NAME + " TEXT NOT NULL)";

        public static final String FRIEND_TABLE_NAME = "friends";
        public static final String FRIEND_USERID = "userid";
        public static final String FRIEND_NAME = "friend_name";

        public static final String CREATE_FRIENDS_TABLE_QUERY = "CREATE TABLE "+ FRIEND_TABLE_NAME + " (" +
                FRIEND_USERID + " INTEGER NOT NULL, " +
                FRIEND_NAME + " TEXT NOT NULL)";

        public static final String GET_USERS_QUERY = "SELECT "+ TABLE_NAME +".*, " + TAG_TABLE_NAME + "." + TAG_NAME + " as tag_name, " + FRIEND_TABLE_NAME + "." + FRIEND_NAME + " AS " + FRIEND_NAME + " FROM " + TABLE_NAME +
                " JOIN " + TAG_TABLE_NAME + " ON " + TABLE_NAME + ".rowid = " + TAG_TABLE_NAME + "." + TAG_USERID +
                " JOIN " + FRIEND_TABLE_NAME + " ON " + TABLE_NAME + ".rowid = " + FRIEND_TABLE_NAME + "." + FRIEND_USERID;

        public static final String TRUNCATE_USERS = "DELETE FROM " + TABLE_NAME;
        public static final String TRUNCATE_FRIENDS = "DELETE FROM " + FRIEND_TABLE_NAME;
        public static final String TRUNCATE_TAGS = "DELETE FROM " + TAG_TABLE_NAME;

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String DROP_QUERY_TAGS = "DROP TABLE IF EXISTS " + TAG_TABLE_NAME;
        public static final String DROP_QUERY_FRIENDS = "DROP TABLE IF EXISTS " + FRIEND_TABLE_NAME;
    }



    public static final class REFERENCE {
        public static final String USER = Config.PACKAGE_NAME + "user";
    }

    public static final class Config {
        public static final String PACKAGE_NAME = "org.dalol.retrofit2_restapidemo.";
    }


}
