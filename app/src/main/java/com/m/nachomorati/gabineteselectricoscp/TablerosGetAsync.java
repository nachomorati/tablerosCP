package com.m.nachomorati.gabineteselectricoscp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TablerosGetAsync extends AsyncTask <String, String, String> {
    private TablerosDBHelper dbHelper;
    private SQLiteDatabase db;
    private TablerosData tablerosData;

    public TablerosGetAsync(Context context){
        this.dbHelper = new TablerosDBHelper(context);
        this.tablerosData = new TablerosData(context);
        this.db = dbHelper.getWritableDatabase();
    }


    @Override
    protected String doInBackground(String... strings) {
        String resultado = null;

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder stringBuilder = new StringBuilder();
            String inputString;

            while((inputString = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputString);
            }

            resultado = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    protected void onPostExecute(String s) {
        //Log.i("resultado", s);
        JSONArray array = null;
        try {
            array = new JSONArray(s.toString());
            for (int i = 0; i < array.length(); i++){
                JSONObject tablero_json = (JSONObject) array.get(i);
                String num = tablero_json.getString("numero");

                //EN SQLITE LO VOY A GUARDAR COMO STRING SIN CONVERTIR
                String llavesStr = tablero_json.getString("llaves");
                //FUNCIONA:
                //ArrayList<Llave> llaves = Helper.strLlaves2ArrLst(tablero_json.getString("llaves"));

                boolean archflash = tablero_json.getBoolean("arch_flash");
                boolean cortocircuito = tablero_json.getBoolean("cortocircuito");
                boolean candado = tablero_json.getBoolean("candado_negro");
                boolean req_candado = tablero_json.getBoolean("requiere_candado");
                boolean contrafrente = tablero_json.getBoolean("contrafrente");
                boolean req_contrafrente = tablero_json.getBoolean("requiere_contrafrente");
                boolean identif = tablero_json.getBoolean("identificaciones");
                boolean en_plano_general = tablero_json.getBoolean("en_plano_general");
                String plano = tablero_json.getString("plano");
                //Log.i("datos", "numero: " + num + "\nllaves: " + llaves + "\narch: " + String.valueOf(archflash) + "\ncorto: " + String.valueOf(cortocircuito));

                ContentValues cv = new ContentValues();
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO, tablero_json.getString("numero"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_LLAVES, tablero_json.getString("llaves"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_ARCH_FLASH, tablero_json.getBoolean("arch_flash"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_CORTOCIRCUITO, tablero_json.getBoolean("cortocircuito"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_CANDADO, tablero_json.getBoolean("candado_negro"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_REQ_CANDADO, tablero_json.getBoolean("requiere_candado"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_CONTRAFRENTE, tablero_json.getBoolean("contrafrente"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_REQ_CONTRAFRENTE, tablero_json.getBoolean("requiere_contrafrente"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_IDENTIFICACIONES, tablero_json.getBoolean("identificaciones"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_EN_PLANO, tablero_json.getBoolean("en_plano_general"));
                cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_PLANO, tablero_json.getString("plano"));

                //Log.i("CONTENTVALUES", cv.toString());

                int res = (int) db.insertWithOnConflict(TablerosContract.TablerosEntry.TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
                if (res==-1){
                    db.update(TablerosContract.TablerosEntry.TABLE_NAME,
                            cv,
                            TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO + "=?",
                            new String[]{tablero_json.getString("numero")});

                    Cursor c = db.query(
                            TablerosContract.TablerosEntry.TABLE_NAME,
                            null,
                            TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO + "=?",
                            new String[]{tablero_json.getString("numero")},
                            null,
                            null,
                            null
                            );
                    if (c!=null){
                        c.moveToFirst();
                        do {
                            String num_tablero = c.getString(c.getColumnIndex(TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO));
                            Log.i("TABLERO", "Tablero " + num_tablero + " creado/modificado");
                        } while (c.moveToNext());
                    } else {
                        Log.i("INFO", "c.no devolvio nada");
                    }
                    c.close();
                }
            }
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //TablerosActivity.tableros = tablerosData.todosLosTableros();
    }
}
