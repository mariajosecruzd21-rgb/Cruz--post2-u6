package com.universidad.antipatrones;
// Clase de dominio simple
public class Pedido {
    private final String id;
    private final String tipoCliente; // "VIP", "PREMIUM", "ESTANDAR"
    private final double total;
    private final String codigoPromo;
    public Pedido(String id, String tipoCliente, double total, String
            codigoPromo) {
        this.id = id; this.tipoCliente = tipoCliente;
        this.total = total; this.codigoPromo = codigoPromo;
    }
    public String getId() { return id; }
    public String getTipoCliente() { return tipoCliente; }
    public double getTotal() { return total; }
    public String getCodigoPromo() { return codigoPromo; }
}
