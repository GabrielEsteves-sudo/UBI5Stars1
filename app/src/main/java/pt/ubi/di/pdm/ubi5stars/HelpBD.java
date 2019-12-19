package pt.ubi.di.pdm.ubi5stars;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelpBD extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UBIBD";

    protected static final String TABLE_NAME = "PtTuristico";
    //protected static final String COL0="id";
    protected static final String COL1= "categoria";
    protected static final String COL2 = "nome";
    protected static final String COL3 = "descrição";
    protected static final String COL4 = "informação";
    protected static final String COL5 = "rating";
    protected static final String COL6 = "reviews";



    protected static final String CREATE_QUESTION="CREATE TABLE " + TABLE_NAME + "( " +
            COL1 + " TEXT PRIMARY KEY, " +
            COL2 + " TEXT PRIMARY KEY, " +
            COL3 + " TEXT," +
            COL4 + " TEXT," +
            COL5 + " TEXT," +
            COL6 + " TEXT);";



    public HelpBD(Context context) {
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db){


        db.execSQL(CREATE_QUESTION);


    }

    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {


        db.execSQL("DROP TABLE "
                + TABLE_NAME + " ; ");
        db.execSQL(CREATE_QUESTION);



    }




}