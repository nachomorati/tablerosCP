package com.m.nachomorati.gabineteselectricoscp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TablerosListViewAdapter extends BaseAdapter {

    private ArrayList<Tablero> mTableros = null;
    private Activity activity;

    public TablerosListViewAdapter(Activity activity, ArrayList<Tablero> tableros) {
        this.activity = activity;
        this.mTableros = tableros;
    }

    @Override
    public int getCount() {
        return mTableros.size();
    }

    @Override
    public Object getItem(int i) {
        return mTableros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.tableros_listview_row, viewGroup, false);
        }

        Tablero mTablero = mTableros.get(i);

        ((TextView)v.findViewById(R.id.tv_tablero_numero)).setText(String.valueOf(mTablero.getNumero()));
        ((TextView)v.findViewById(R.id.tv_tablero_ubicacion)).setText(String.valueOf(mTablero.getUbicacion()));

        return v;
    }
}
