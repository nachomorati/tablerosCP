package com.m.nachomorati.gabineteselectricoscp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TablerosDBHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "TablerosDB";
    private static SQLiteDatabase.CursorFactory factory = null;

    public TablerosDBHelper(Context context){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(this.getClass().toString(), "Creando base de datos...");
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

        sqLiteDatabase.execSQL("CREATE UNIQUE INDEX tab_numero ON TABLEROS(tab_numero ASC)");
        Log.i(this.getClass().toString(), "Tabla TABLEROS creada.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ TablerosContract.TablerosEntry.TABLE_NAME +"(" +
                    TablerosContract.TablerosEntry.COLUMN_TITLE__ID +" INTEGER PRIMARY KEY," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO +" TEXT NOT NULL, " +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_UBICACION + " TEXT," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_ARCH_FLASH +" INTEGER, " +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_CORTOCIRCUITO +" INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_CANDADO + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_REQ_CANDADO + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_CONTRAFRENTE + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_REQ_CONTRAFRENTE + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_IDENTIFICACIONES + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_EN_PLANO + " INTEGER," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_PLANO + " TEXT," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_SECTOR + " TEXT," +
                    TablerosContract.TablerosEntry.COLUMN_TITLE_LLAVES + " TEXT)";
}
