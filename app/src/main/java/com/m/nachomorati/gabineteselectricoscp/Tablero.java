package com.m.nachomorati.gabineteselectricoscp;

import java.util.ArrayList;

public class Tablero {
    private int _id;
    private String numero;
    private String ubicacion;
    private ArrayList<Llave> llaves;
    private boolean arch_flash;
    private boolean cortocircuito;
    private boolean candado;
    private boolean req_candado;
    private boolean contrafrente;
    private boolean req_contrafrente;
    private boolean identificaciones;
    private boolean en_plano_general;
    private String plano;
    private String sector;

    public Tablero (String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Llave> getLlaves() {
        return llaves;
    }

    public void setLlaves(ArrayList<Llave> llaves) {
        this.llaves = llaves;
    }

    public boolean isArch_flash() {
        return arch_flash;
    }

    public void setArch_flash(boolean arch_flash) {
        this.arch_flash = arch_flash;
    }

    public boolean isCortocircuito() {
        return cortocircuito;
    }

    public void setCortocircuito(boolean cortocircuito) {
        this.cortocircuito = cortocircuito;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public boolean isCandado() {
        return candado;
    }

    public void setCandado(boolean candado) {
        this.candado = candado;
    }

    public boolean isReq_candado() {
        return req_candado;
    }

    public void setReq_candado(boolean req_candado) {
        this.req_candado = req_candado;
    }

    public boolean isContrafrente() {
        return contrafrente;
    }

    public void setContrafrente(boolean contrafrente) {
        this.contrafrente = contrafrente;
    }

    public boolean isReq_contrafrente() {
        return req_contrafrente;
    }

    public void setReq_contrafrente(boolean req_contrafrente) {
        this.req_contrafrente = req_contrafrente;
    }

    public boolean isIdentificaciones() {
        return identificaciones;
    }

    public void setIdentificaciones(boolean identificaciones) {
        this.identificaciones = identificaciones;
    }

    public boolean isEn_plano_general() {
        return en_plano_general;
    }

    public void setEn_plano_general(boolean en_plano_general) {
        this.en_plano_general = en_plano_general;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
