package com.m.nachomorati.gabineteselectricoscp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

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
import java.util.List;

public class TablerosActivity extends AppCompatActivity {
    private ArrayList<Tablero> tableros;
    private ProgressBar pb;
    private ListView lvTableros;
    private static Spinner spinnerSectores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableros);

        pb = findViewById(R.id.progress_bar);
        lvTableros = findViewById(R.id.lv_tableros);
        spinnerSectores = findViewById(R.id.sp_sectores);

        new TablerosGet(getBaseContext()).execute("https://script.google.com/macros/s/AKfycbwYPVxdR4RjkgUU4DIkJJFtjgB6kiaNbLuKUIc4JJtWhBbpCi7-/exec");
    }

    class TablerosGet extends AsyncTask<String, Integer, String> {
        private TablerosDBHelper dbHelper;
        private SQLiteDatabase db;
        private TablerosData tablerosData;
        private List<String> list = new ArrayList<>();

        public TablerosGet(Context context){
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

                URL urlSectores = new URL("https://script.google.com/macros/s/AKfycbzBPQkEF8FWIdUzw1DdehNFNF_lTxPvaAVeIt1aU6euJ3NQBIyC/exec");
                HttpURLConnection urlConnection1 = (HttpURLConnection) urlSectores.openConnection();
                InputStream stream1 = new BufferedInputStream(urlConnection1.getInputStream());
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(stream1));

                String json_sectores = bufferedReader1.readLine();
                JSONArray jsonArray = new JSONArray(json_sectores);

                for (int i = 0; i < jsonArray.length(); i++){
                    list.add(jsonArray.getJSONObject(i).getString("nombre"));
                }
                //Log.d("jsonList", list.toString());



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            ///////////////////////////////////////////////////////////////////
            //setear el spinner de sectores con los datos de la //////////////
            //lista que trajimos del sheet //////////////////////////////////
            ArrayAdapter<String> spinnerSectoresAdapter = new ArrayAdapter<String>(
                    TablerosActivity.this,
                    android.R.layout.simple_spinner_item,
                    list
            );
            spinnerSectoresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSectores.setAdapter(spinnerSectoresAdapter);

            /////////////////////////////////////////////////////////////////
            //tomar el String con el json que trajimos del sheet,  /////////
            //parsearlo y guardarlo en la bd local. ///////////////////////
            JSONArray array = null;
            try {
                array = new JSONArray(s.toString());
                for (int i = 0; i < array.length(); i++){
                    JSONObject tablero_json = (JSONObject) array.get(i);

                    ContentValues cv = new ContentValues();
                    cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE__ID, tablero_json.getInt("id"));
                    cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_NUMERO, tablero_json.getString("numero"));
                    cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_SECTOR, tablero_json.getString("sector"));
                    cv.put(TablerosContract.TablerosEntry.COLUMN_TITLE_UBICACION, tablero_json.getString("ubicacion"));
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

                    Log.i("CONTENTVALUES", cv.toString());

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

            tableros = tablerosData.todosLosTableros();
            TablerosListViewAdapter tlvadapter = new TablerosListViewAdapter(TablerosActivity.this, tableros);
            lvTableros = findViewById(R.id.lv_tableros);
            lvTableros.setAdapter(tlvadapter);

            pb.setVisibility(View.INVISIBLE);
            lvTableros.setVisibility(View.VISIBLE);
            spinnerSectores.setVisibility(View.VISIBLE);

        }
    }
}
