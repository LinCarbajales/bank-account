package dev.lin.models;

public class CuentaCorriente extends Cuenta {

    private float sobregiro;

    public CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        this.sobregiro = 0;
    }

    @Override
    public void retirar(float cantidad) {
        if (cantidad <= saldo) {
            super.retirar(cantidad);
        } else {
            sobregiro = cantidad - saldo;
            saldo = 0;
            numeroRetiros++;
        }
    }

    @Override
    public void consignar(float cantidad) {
        if (sobregiro > 0) {
            float cantidadParaSobregiro = Math.min(cantidad, sobregiro);
            sobregiro -= cantidadParaSobregiro;
            cantidad -= cantidadParaSobregiro;
        }
        if (cantidad > 0) {
            super.consignar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        super.extractoMensual();
    }

    @Override
    public String imprimir() {
        int numeroTransacciones = numeroConsignaciones + numeroRetiros;
        return "--- Información de Cuenta Corriente ---\n" +
               "Saldo: " + saldo + "\n" +
               "Comisión mensual: " + comisionMensual + "\n" +
               "Número de transacciones: " + numeroTransacciones + "\n" +
               "Valor de sobregiro: " + sobregiro;
    }

    public float getSobregiro() {
        return sobregiro;
    }
}