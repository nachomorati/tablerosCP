package com.m.nachomorati.gabineteselectricoscp;

import android.provider.BaseColumns;

public class TablerosContract {
    private TablerosContract(){}

    public static class TablerosEntry implements BaseColumns {
        public static final String TABLE_NAME = "TABLEROS";
        public static final String COLUMN_TITLE_NUMERO = "tab_numero";
        public static final String COLUMN_TITLE__ID = "_id";
        public static final String COLUMN_TITLE_UBICACION = "tab_ubicacion";
        public static final String COLUMN_TITLE_ARCH_FLASH = "tab_arch_flash";
        public static final String COLUMN_TITLE_CORTOCIRCUITO = "tab_cortocircuito";
        public static final String COLUMN_TITLE_CANDADO = "tab_candado";
        public static final String COLUMN_TITLE_REQ_CANDADO = "tab_req_candado";
        public static final String COLUMN_TITLE_CONTRAFRENTE = "tab_contrafrente";
        public static final String COLUMN_TITLE_REQ_CONTRAFRENTE = "tab_req_contrafrente";
        public static final String COLUMN_TITLE_IDENTIFICACIONES = "tab_identificaciones";
        public static final String COLUMN_TITLE_EN_PLANO = "tab_en_plano";
        public static final String COLUMN_TITLE_PLANO = "tab_plano";
        public static final String COLUMN_TITLE_LLAVES = "tab_llaves";
    }


}
