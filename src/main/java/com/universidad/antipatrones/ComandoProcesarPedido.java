package com.universidad.antipatrones;
// Command concreto: procesar pedido con descuento
public class ComandoProcesarPedido implements ComandoPedido {
    private final Pedido pedido;
    private final EstrategiaDescuento estrategia;
    public
    ComandoProcesarPedido(Pedido pedido, EstrategiaDescuento
            estrategia) {
        this.pedido = pedido;
        this.estrategia = estrategia;
    }
    @Override
    public void ejecutar() {
        double descuento = estrategia.calcular(pedido);
        double totalFinal = pedido.getTotal() * (1 - descuento);
        System.out.println("Procesando pedido: " + pedido.getId());
        System.out.printf("  Estrategia: %s | Descuento: %.0f%%%n",
                estrategia.getNombre(), descuento * 100);
        System.out.printf("  Total final: $%.2f%n", totalFinal);
        if (totalFinal > 500)
            System.out.println(" [ALERTA] Pedido de alto valor: " +
                    pedido.getId());
        System.out.println("Pedido " + pedido.getId() + " procesado.");
    }
}
