# Análisis Detallado: Refactorización de Código Spaghetti

## 📊 Comparación Detallada del Código

### ❌ CÓDIGO ORIGINAL (ProcesadorPedidos.java)

```java
public class ProcesadorPedidos {
    // Metodo principal con 6 niveles de anidamiento
    public void procesarPedido(Pedido pedido) {
        System.out.println("Procesando pedido: " + pedido.getId());
        double descuento = 0;
        String tipo = pedido.getTipoCliente();
        String promo = pedido.getCodigoPromo();
        double total = pedido.getTotal();
        
        // NIVEL 1
        if (tipo != null) {
            // NIVEL 2
            if (tipo.equals("VIP")) {
                // NIVEL 3
                if (total > 1000) {
                    descuento = 0.35;
                    // NIVEL 4
                    if (promo != null && promo.equals("VIPEXTRA")) {
                        // NIVEL 5
                        descuento = 0.45;
                    }
                } else if (total > 500) {
                    descuento = 0.25;
                    // NIVEL 4
                    if (promo != null && promo.startsWith("VIP")) {
                        // NIVEL 5
                        descuento = 0.30;
                    }
                } else {
                    descuento = 0.15;
                }
            // NIVEL 2
            } else if (tipo.equals("PREMIUM")) {
                // NIVEL 3
                if (total > 500) {
                    descuento = 0.20;
                } else {
                    descuento = 0.10;
                    // NIVEL 4
                    if (promo != null && promo.equals("PREM10")) {
                        // NIVEL 5
                        descuento = 0.15;
                    }
                }
            } else { // ESTANDAR
                // NIVEL 3
                if (promo != null) {
                    // NIVEL 4
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
        System.out.printf("  Descuento aplicado: %.0f%%%n", descuento * 100);
        System.out.printf("  Total final: $%.2f%n", totalConDescuento);
        
        // Notificacion mezclada en el mismo metodo
        if (totalConDescuento > 500) {
            System.out.println(" [ALERTA] Pedido de alto valor: " + pedido.getId());
        }
        System.out.println("Pedido " + pedido.getId() + " procesado.");
    }
}
```

### ✅ CÓDIGO REFACTORIZADO

#### Paso 1: Crear la Interfaz Strategy

```java
public interface EstrategiaDescuento {
    double calcular(Pedido pedido);
    String getNombre();
}
```

#### Paso 2: Implementaciones Concretas

**DescuentoVIP.java** - Encapsula la lógica VIP

```java
public class DescuentoVIP implements EstrategiaDescuento {
    @Override
    public double calcular(Pedido pedido) {
        double base = pedido.getTotal() > 1000 ? 0.35 :
                      pedido.getTotal() > 500  ? 0.25 : 0.15;
        String promo = pedido.getCodigoPromo();
        
        if ("VIPEXTRA".equals(promo) && pedido.getTotal() > 1000) 
            return 0.45;
        if (promo != null && promo.startsWith("VIP") && pedido.getTotal() > 500) 
            return base + 0.05;
            
        return base;
    }
    
    @Override 
    public String getNombre() { return "VIP"; }
}
```

**DescuentoPremium.java** - Encapsula la lógica Premium

```java
public class DescuentoPremium implements EstrategiaDescuento {
    @Override
    public double calcular(Pedido pedido) {
        if (pedido.getTotal() > 500) return 0.20;
        if ("PREM10".equals(pedido.getCodigoPromo())) return 0.15;
        return 0.10;
    }
    
    @Override 
    public String getNombre() { return "PREMIUM"; }
}
```

**DescuentoEstandar.java** - Encapsula la lógica Estándar

```java
public class DescuentoEstandar implements EstrategiaDescuento {
    @Override
    public double calcular(Pedido pedido) {
        String promo = pedido.getCodigoPromo();
        if (promo == null) return 0.0;
        if (promo.startsWith("FIRST")) return 0.08;
        if ("SAVE5".equals(promo)) return 0.05;
        return 0.02;
    }
    
    @Override 
    public String getNombre() { return "ESTANDAR"; }
}
```

#### Paso 3: Factory para Seleccionar Estrategia

```java
public class SelectorEstrategia {
    private final java.util.Map<String, EstrategiaDescuento> estrategias = 
        java.util.Map.of(
            "VIP",      new DescuentoVIP(),
            "PREMIUM",  new DescuentoPremium(),
            "ESTANDAR", new DescuentoEstandar()
        );
    
    public EstrategiaDescuento seleccionar(String tipoCliente) {
        return estrategias.getOrDefault(tipoCliente, 
                estrategias.get("ESTANDAR"));
    }
}
```

#### Paso 4: Patrón Command

**ComandoPedido.java** - Interfaz Command

```java
public interface ComandoPedido {
    void ejecutar();
}
```

**ComandoProcesarPedido.java** - Implementación Concreta

```java
public class ComandoProcesarPedido implements ComandoPedido {
    private final Pedido pedido;
    private final EstrategiaDescuento estrategia;
    
    public ComandoProcesarPedido(Pedido pedido, 
                                 EstrategiaDescuento estrategia) {
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
```

#### Paso 5: Cliente (Main.java)

```java
public class Main {
    public static void main(String[] args) {
        SelectorEstrategia selector = new SelectorEstrategia();
        
        java.util.List<Pedido> pedidos = java.util.List.of(
                new Pedido("P001", "VIP",      1200.0, "VIPEXTRA"),
                new Pedido("P002", "VIP",       600.0, "VIP20"),
                new Pedido("P003", "PREMIUM",   300.0, "PREM10"),
                new Pedido("P004", "ESTANDAR",  150.0, "FIRST50"),
                new Pedido("P005", "ESTANDAR",   80.0, null)
        );
        
        pedidos.stream()
                .map(p -> new ComandoProcesarPedido(p, 
                        selector.seleccionar(p.getTipoCliente())))
                .forEach(ComandoPedido::ejecutar);
    }
}
```

---

## 📈 Métricas de Mejora

### Complejidad Ciclomática

**Antes**:
```
Complejidad del método procesarPedido(): 11
- if (tipo != null)
- if (tipo.equals("VIP"))
- if (total > 1000)
- if (promo != null && promo.equals("VIPEXTRA"))
- else if (total > 500)
- if (promo != null && promo.startsWith("VIP"))
- else if (tipo.equals("PREMIUM"))
- if (total > 500)
- else if (promo != null && promo.equals("PREM10"))
- else (ESTANDAR)
- if (promo != null)
- if (promo.startsWith("FIRST"))
- else if (promo.equals("SAVE5"))
- if (totalConDescuento > 500)
```

**Después**:
```
DescuentoVIP.calcular(): 3
DescuentoPremium.calcular(): 2
DescuentoEstandar.calcular(): 3
ComandoProcesarPedido.ejecutar(): 2
SelectorEstrategia.seleccionar(): 1

Total repartido: Máximo 3 por método
```

### Acoplamiento

| Aspecto | Antes | Después |
|---------|-------|---------|
| Dependencias de ProcesadorPedidos | Pedido + Lógica interna | Solo Pedido |
| Cambios necesarios para agregar tipo de cliente | Modificar método completo | Agregar 1 clase nueva |
| Posibilidad de reutilizar lógica | Baja (mezclada) | Alta (estrategias independientes) |

### Testabilidad

**Antes - Difícil de testear**:
```
// Imposible testear cada rama sin estar en el mismo método
// Hay que mockear System.out para verificar salida
// Lógica de negocio + presentación mezcladas
```

**Después - Fácil de testear**:
```java
// Testear cada estrategia de forma independiente
@Test
void testDescuentoVIPAlto() {
    EstrategiaDescuento vip = new DescuentoVIP();
    Pedido pedido = new Pedido("P001", "VIP", 1200, "VIPEXTRA");
    assertEquals(0.45, vip.calcular(pedido));
}

@Test
void testDescuentoPremium() {
    EstrategiaDescuento premium = new DescuentoPremium();
    Pedido pedido = new Pedido("P002", "PREMIUM", 600, null);
    assertEquals(0.20, premium.calcular(pedido));
}

// Testear la selección
@Test
void testSelectorEstrategia() {
    SelectorEstrategia selector = new SelectorEstrategia();
    EstrategiaDescuento estrategia = selector.seleccionar("VIP");
    assertNotNull(estrategia);
    assertEquals("VIP", estrategia.getNombre());
}
```

---

## 🎯 Ventajas del Código Refactorizado

### 1. Mantenibilidad ⭐⭐⭐⭐⭐
- Cada clase tiene una responsabilidad clara
- Cambios localizados en una sola clase
- Fácil de entender qué hace cada parte

### 2. Escalabilidad ⭐⭐⭐⭐⭐
- Agregar nuevo tipo de cliente = 1 clase nueva
- No requiere modificar código existente
- Principio Open/Closed

### 3. Testabilidad ⭐⭐⭐⭐⭐
- Cada estrategia testeable independientemente
- No es necesario mockear System.out
- Tests unitarios claros y directos

### 4. Reutilización ⭐⭐⭐⭐⭐
- Las estrategias pueden usarse en otros contextos
- El Command Pattern permite encolar, logging, auditoría
- Código modular y reutilizable

### 5. Rendimiento ⭐⭐⭐⭐
- Mismo rendimiento que antes
- Posibilidad de cachear estrategias
- Sin sobrecarga significativa

### 6. Documentación ⭐⭐⭐⭐⭐
- El código es auto-documentado
- Patrones de diseño bien conocidos
- Fácil para nuevos desarrolladores

---

## 🔄 Flujo de Ejecución

### Antes (Spaghetti)
```
Main
  ↓
ProcesadorPedidos.procesarPedido()
  ├─ Check tipo de cliente (if-else-if-else)
  ├─ Check monto total (if-else if-else)
  ├─ Check código promo (if-else if-else)
  ├─ Calcular descuento
  ├─ Imprimir resultado
  ├─ Check si es alto valor
  └─ Imprimir alerta
```

### Después (Patrones)
```
Main
  ├─ SelectorEstrategia
  │   └─ Map de estrategias
  │
  └─ Para cada Pedido:
      ├─ SelectorEstrategia.seleccionar(tipo)
      │   └─ Devuelve EstrategiaDescuento
      │
      ├─ new ComandoProcesarPedido(pedido, estrategia)
      │
      └─ ComandoPedido.ejecutar()
          ├─ EstrategiaDescuento.calcular()
          │   └─ Lógica específica del tipo
          ├─ Calcular total final
          └─ Imprimir resultado
```

---

## 📚 Referencias

### Patrones Usados

1. **Strategy Pattern** (Gang of Four)
   - Define una familia de algoritmos
   - Encapsula cada uno
   - Los hace intercambiables

2. **Command Pattern** (Gang of Four)
   - Encapsula una solicitud como objeto
   - Permite parametrizar clientes

3. **Factory Pattern** (Gang of Four)
   - Creación de objetos sin especificar clases concretas
   - Centraliza lógica de creación

### Principios SOLID

- **S**ingle Responsibility: Cada clase = 1 razón de cambio
- **O**pen/Closed: Abierto a extensión, cerrado a modificación
- **L**iskov Substitution: Subtipos intercambiables
- **I**nterface Segregation: Interfaces específicas
- **D**ependency Inversion: Depender de abstracciones

---

## ✨ Conclusión

La refactorización demuestra cómo los patrones de diseño transforman código difícil de mantener en una arquitectura profesional, escalable y mantenible. No es solo "bonito" - es **práctico y profesional**.

**Código spaghetti → Código limpio = Proyecto exitoso**

