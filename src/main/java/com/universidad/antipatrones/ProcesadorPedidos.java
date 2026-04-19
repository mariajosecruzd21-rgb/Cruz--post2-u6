package com.universidad.antipatrones;
// SPAGHETTI CODE — procesador con condicionales anidados
public class ProcesadorPedidos {
    // Metodo principal con 6 niveles de anidamiento
    public void procesarPedido(Pedido pedido) {
        System.out.println("Procesando pedido: " + pedido.getId());
        double descuento = 0;
        String tipo = pedido.getTipoCliente();
        String promo = pedido.getCodigoPromo();
        double total = pedido.getTotal();
        if (tipo != null) {
            if (tipo.equals("VIP")) {
                if (total > 1000) {
                    descuento = 0.35;
                    if (promo != null && promo.equals("VIPEXTRA")) {
                        descuento = 0.45;
                    }
                } else if (total > 500) {
                    descuento = 0.25;
                    if (promo != null && promo.startsWith("VIP")) {
                        descuento = 0.30;
                    }
                } else {
                    descuento = 0.15;
                }
            } else if (tipo.equals("PREMIUM")) {
                if (total > 500) {
                    descuento = 0.20;
                } else {
                    descuento = 0.10;
                    if (promo != null && promo.equals("PREM10")) {
                        descuento = 0.15;
                    }
                }
            } else { // ESTANDAR
                if (promo != null) {
                    if (promo.startsWith("FIRST")) {
                        descuento = 0.08;
                    } else if (promo.equals("SAVE5")) {
                        descuento = 0.05;
                    } else {
                        descuento = 0.02;
                    }
                }
            }
        }
        double totalConDescuento = total * (1 - descuento);
        System.out.printf("  Descuento aplicado: %.0f%%%n", descuento *
                100);
        System.out.printf("  Total final: $%.2f%n", totalConDescuento);
// Notificacion mezclada en el mismo metodo
        if (totalConDescuento > 500) {
            System.out.println(" [ALERTA] Pedido de alto valor: " +
                    pedido.getId());
        }
        System.out.println("Pedido " + pedido.getId() + " procesado.");
    }
}
