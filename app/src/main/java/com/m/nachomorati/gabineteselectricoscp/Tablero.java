package com.m.nachomorati.gabineteselectricoscp;

import java.util.ArrayList;

public class Tablero {
    private String numero;
    private String ubicacion;
    private ArrayList<Llave> llaves;
    private boolean arch_flash;
    private boolean cortocircuito;

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
}
