package com.universidad.antipatrones;
// Interfaz del patron Strategy
public interface EstrategiaDescuento {
    double calcular(Pedido pedido);
    String getNombre(); // Para logging y trazabilidad
}
