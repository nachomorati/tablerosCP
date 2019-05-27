package com.m.nachomorati.gabineteselectricoscp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TablerosData {
    private TablerosDBHelper dbHelper;

    public TablerosData (Context context){
        this.dbHelper = new TablerosDBHelper(context);
    }

    public ArrayList<Tablero> todosLosTableros() {
        ArrayList<Tablero> mTableros = new ArrayList<>();

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        Cursor c = db.query(
                TablerosContract.TablerosEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (c!=null){
            while (c.moveToNext()){
                Tablero tablero = new Tablero(c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO)));
                tablero.setUbicacion(c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_UBICACION)));
                tablero.setLlaves(Helper.strLlaves2ArrLst(c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_LLAVES))));
                tablero.setArch_flash(Boolean.parseBoolean(c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_ARCH_FLASH))));
                tablero.setCortocircuito(Boolean.parseBoolean(c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_CORTOCIRCUITO))));
                mTableros.add(tablero);
            }
        }

        return mTableros;
    }
}
