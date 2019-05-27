package com.m.nachomorati.gabineteselectricoscp;

public class Llave {
    private int numero;
    private String alimenta_a;

    public Llave(int numero, String alimenta_a) {
        this.numero = numero;
        this.alimenta_a = alimenta_a;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAlimenta_a() {
        return alimenta_a;
    }

    public void setAlimenta_a(String alimenta_a) {
        this.alimenta_a = alimenta_a;
    }
}
