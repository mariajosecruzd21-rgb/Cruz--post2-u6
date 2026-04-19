package com.universidad.antipatrones;
// Estrategia para cliente PREMIUM
public class DescuentoPremium implements EstrategiaDescuento {
    @Override
    public double calcular(Pedido pedido) {
        if (pedido.getTotal() > 500) return 0.20;
        if ("PREM10".equals(pedido.getCodigoPromo())) return 0.15;
        return 0.10;
    }
    @Override public String getNombre() { return "PREMIUM"; }
}
