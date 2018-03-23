package harkat.jussi.loppuharkkaproto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mikes on 21.3.2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MigreeniDb";
    private static final String TABLE_NAME = "Migraine";
    private static final String KEY_ID = "id";
    private static final String DATE = "date";
    private static final String DURATION = "duration";
    private static final String PAINTINTENSITY = "painIntensity";
    private static final String TYPE = "type";
    private static final String PRESYMPTOMS = "presymptoms";
    private static final String PAINLOCATION = "painlocation";
    private static final String OTHERSYMPTOMS = "othersymptoms";
    private static final String MEDICINETAKEN = "medicinetaken";
    private static final String LOCATION = "location";
    private static final String TRIGGERS = "triggers";
    private static final String MENSTRUATION = "menstruation";

    private static final String[] COLUMNS = { KEY_ID, DATE, DURATION, PAINTINTENSITY, TYPE,
            PRESYMPTOMS, PAINLOCATION, OTHERSYMPTOMS, MEDICINETAKEN, LOCATION, TRIGGERS,
            MENSTRUATION  };


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Migraine ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, date STRING, duration STRING, painIntensity STRING, type " +
                "STRING, presymptoms STRING, painlocation STRING, othersymptoms STRING, " +
                "medicinetaken STRING, location STRING, triggers STRING, menstruation STRING  )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addMigraine(Migreeni migraine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, migraine.getDate());
        values.put(DURATION, migraine.getDuration());
        values.put(PAINTINTENSITY, migraine.getPainIntensity());
        values.put(TYPE, migraine.getType());
        values.put(PRESYMPTOMS, migraine.getPresymptoms());
        values.put(PAINLOCATION, migraine.getPainLocation());
        values.put(OTHERSYMPTOMS, migraine.getOtherSymptoms());
        values.put(MEDICINETAKEN, migraine.getMedicineTaken());
        values.put(LOCATION, migraine.getLocation());
        values.put(TRIGGERS, migraine.getTriggers());
        values.put(MENSTRUATION, migraine.getMenstruation());

        db.insert(TABLE_NAME,null, values);
        db.close();

        Log.i("DatabaseHandler", "Added migraine");
    }

}
