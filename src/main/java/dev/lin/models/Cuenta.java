package dev.lin.models;

public class Cuenta {
    
    protected float saldo;
    protected int numeroConsignaciones;
    protected int numeroRetiros;
    protected float tasaAnual;
    protected float comisionMensual;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
        this.numeroConsignaciones = 0;
        this.numeroRetiros = 0;
        this.comisionMensual = 0;
    }

    public void consignar(float cantidad) {
        saldo += cantidad;
        numeroConsignaciones++;
    }

    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        }
    }

    public void calcularInteresMensual() {
        float interesMensual = (tasaAnual / 12) / 100 * saldo;
        saldo += interesMensual;
    }

    public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteresMensual();
    }

    public String imprimir() {
        return "Saldo: " + saldo + "\n" +
               "Número de consignaciones: " + numeroConsignaciones + "\n" +
               "Número de retiros: " + numeroRetiros + "\n" +
               "Tasa anual: " + tasaAnual + "\n" +
               "Comisión mensual: " + comisionMensual;
    }

    public float getSaldo() {
        return saldo;
    }   

    public int getNumeroConsignaciones() {
        return numeroConsignaciones;
    }

    public int getNumeroRetiros() {
        return numeroRetiros;
    }

    public float getComisionMensual() {
        return comisionMensual;
    }
}