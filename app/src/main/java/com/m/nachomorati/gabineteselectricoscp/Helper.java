package com.m.nachomorati.gabineteselectricoscp;

import android.util.Log;

import java.util.ArrayList;

public class Helper {
    static public ArrayList<Llave> strLlaves2ArrLst (String llaves) {
        ArrayList<Llave> mLlaves = new ArrayList<>();
        if (llaves.equals("")) {
            return mLlaves;
        } else {
            Llave llave = null;
            String[] pares = llaves.split("#\\*");
            for (String par : pares) {
                String[] item = par.split(",");
                llave = new Llave(Integer.parseInt(item[0]), item[1]);
                mLlaves.add(llave);
            }
            return mLlaves;
        }
    }
}
