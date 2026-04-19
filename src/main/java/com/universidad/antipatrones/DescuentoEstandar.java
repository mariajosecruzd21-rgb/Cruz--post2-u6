package com.universidad.antipatrones;
// Estrategia para cliente ESTANDAR
public class DescuentoEstandar implements EstrategiaDescuento {
    @Override
    public double calcular(Pedido pedido) {
        String promo = pedido.getCodigoPromo();
        if (promo == null) return 0.0;
        if (promo.startsWith("FIRST")) return 0.08;
        if ("SAVE5".equals(promo)) return 0.05;
        return 0.02;
    }
    @Override public String getNombre() { return "ESTANDAR"; }
}