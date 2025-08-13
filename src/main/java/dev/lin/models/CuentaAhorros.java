package dev.lin.models;

public class CuentaAhorros extends Cuenta {

    private boolean estaActiva;

    public CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        this.estaActiva = saldo >= 10000;
    }

    @Override
    public void consignar(float cantidad) {
        if (estaActiva) {
            super.consignar(cantidad);
        }
    }

    @Override
    public void retirar(float cantidad) {
        if (estaActiva) {
            super.retirar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        if (numeroRetiros > 4) {
            comisionMensual += (numeroRetiros - 4) * 1000;
        }
        super.extractoMensual();
        estaActiva = saldo >= 10000;
    }

    @Override
    public String imprimir() {
        int numeroTransacciones = numeroConsignaciones + numeroRetiros;
        return "--- Información de Cuenta de Ahorros ---\n" +
               "Saldo: " + saldo + "\n" +
               "Comisión mensual: " + comisionMensual + "\n" +
               "Número de transacciones: " + numeroTransacciones;
    }
}