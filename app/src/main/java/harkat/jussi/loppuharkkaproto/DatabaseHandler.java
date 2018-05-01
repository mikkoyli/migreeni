package harkat.jussi.loppuharkkaproto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikes on 21.3.2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "MigreeniDb";
    private static final String TABLE_NAME = "Migraine";
    private static final String KEY_ID = "id";
    private static final String DATE = "date";
    private static final String DURATION = "duration";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";

    private static final String PAININTENSITY = "painIntensity";
    private static final String TYPE = "type";
    private static final String TYPE2 = "type2";
    private static final String PRESYMPTOMS = "presymptoms";
    private static final String PRESYMPTOMS2 = "presymptoms2";
    private static final String PAINLOCATION = "painlocation";
    private static final String OTHERSYMPTOMS = "othersymptoms";
    private static final String MEDICINETAKEN = "medicinetaken";
    private static final String MEDICINE_EFFECT = "medicine_effect";
    private static final String LOCATION = "location";
    private static final String TRIGGERS = "triggers";
    private static final String TRIGGERS2 = "triggers2";
    private static final String MENSTRUATION = "menstruation";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Migraine ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, date STRING, start_time STRING, end_time STRING, duration STRING, painIntensity STRING, type " +
                "STRING, type2 STRING, presymptoms STRING, presymptoms2 STRING, painlocation STRING, othersymptoms STRING, " +
                "medicinetaken STRING, medicine_effect STRING, location STRING, triggers STRING, triggers2 STRING, menstruation STRING  )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addMigraine(Migreeni migraine) {
        //tähän rest-apin tai jonkin php-skriptin url, parametrina esim migreenin string tai mitä tahansa.
        internet("http://rest-apin-osoite/?GET_PARAM="+ URLEncoder.encode(migraine.toString()));

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, migraine.getDate());
        values.put(DURATION, migraine.getDuration());
        values.put(START_TIME, migraine.getStartTime());
        values.put(END_TIME, migraine.getEndTime());
        values.put(PAININTENSITY, migraine.getPainIntensity());
        values.put(TYPE, migraine.getType());
        values.put(TYPE2, migraine.getType2());
        values.put(PRESYMPTOMS, migraine.getPresymptoms());
        values.put(PRESYMPTOMS2, migraine.getPresymptoms2());
        values.put(PAINLOCATION, migraine.getPainLocation());
        values.put(OTHERSYMPTOMS, migraine.getOtherSymptoms());
        values.put(MEDICINETAKEN, migraine.getMedicineTaken());
        values.put(MEDICINE_EFFECT, migraine.getMedicineEffect());
        values.put(LOCATION, migraine.getLocation());
        values.put(TRIGGERS, migraine.getTriggers());
        values.put(TRIGGERS2, migraine.getTriggers2());
        values.put(MENSTRUATION, migraine.getMenstruation());

        db.insert(TABLE_NAME, null, values);
        db.close();

        Log.i("DatabaseHandler", "Added migraine");
    }

    public void deleteMigraine(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME + " WHERE id = "+id);
        db.close();
    }
    public List<Migreeni> getMigraines() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Migreeni> migraineList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY id";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // this is quick fix for bug which crashes if db is empty
        if (cursor.getCount() == 0) {
            return null;
        }

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                try {

                    String m_id = String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                    String m_date = cursor.getString(cursor.getColumnIndex(DATE));
                    String m_duration = cursor.getString(cursor.getColumnIndex(DURATION));
                    String m_start_time = cursor.getString(cursor.getColumnIndex(START_TIME));
                    String m_end_time = cursor.getString(cursor.getColumnIndex(END_TIME));
                    String m_painins = cursor.getString(cursor.getColumnIndex(PAININTENSITY));
                    String m_type = cursor.getString(cursor.getColumnIndex(TYPE));
                    String m_type2 = cursor.getString(cursor.getColumnIndex(TYPE2));
                    String m_presym = cursor.getString(cursor.getColumnIndex(PRESYMPTOMS));
                    String m_presym2 = cursor.getString(cursor.getColumnIndex(PRESYMPTOMS2));
                    String m_painloc = cursor.getString(cursor.getColumnIndex(PAINLOCATION));
                    String m_othersy = cursor.getString(cursor.getColumnIndex(OTHERSYMPTOMS));
                    String m_medicineta = cursor.getString(cursor.getColumnIndex(MEDICINETAKEN));
                    String m_medicine_ef = cursor.getString(cursor.getColumnIndex(MEDICINE_EFFECT));
                    String m_loca = cursor.getString(cursor.getColumnIndex(LOCATION));
                    String m_trigg = cursor.getString(cursor.getColumnIndex(TRIGGERS));
                    String m_trigg2 = cursor.getString(cursor.getColumnIndex(TRIGGERS2));
                    String m_mestr = cursor.getString(cursor.getColumnIndex(MENSTRUATION));
                    migraineList.add(new Migreeni(m_id,m_date, m_duration, m_start_time, m_end_time,
                            m_painins,
                            m_type, m_type2, m_presym, m_presym2, m_painloc,
                            m_othersy, m_medicineta, m_medicine_ef,
                            m_loca, m_trigg, m_trigg2, m_mestr, R.drawable.pain10));
                    cursor.moveToNext();
                } catch (Exception e) {
                }
            }
        }

        db.close();

        return migraineList;
    }
    public static String callURL(String myURL) {
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }

        return sb.toString();
    }
    private void internet(final String url) {

        Thread th = new Thread(new Runnable() {
            public void run() {
                final String texti=callURL(url);
                Log.e("regguls",texti);
            }
        });
        th.start();
    }
}
