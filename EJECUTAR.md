# 🚀 Guía de Ejecución del Proyecto

## Estructura del Proyecto

```
Cruz--post2-u6/
├── src/main/java/com/universidad/antipatrones/
│   ├── Main.java                           ← Punto de entrada
│   ├── Pedido.java                         ← Modelo de datos
│   ├── EstrategiaDescuento.java            ← Interfaz Strategy
│   ├── DescuentoVIP.java                   ← Estrategia VIP
│   ├── DescuentoPremium.java               ← Estrategia Premium
│   ├── DescuentoEstandar.java              ← Estrategia Estándar
│   ├── SelectorEstrategia.java             ← Factory
│   ├── ComandoPedido.java                  ← Interfaz Command
│   ├── ComandoProcesarPedido.java          ← Comando concreto
│   └── ProcesadorPedidos.java              ← Código original (referencia)
├── pom.xml                                  ← Configuración Maven
├── README.md                                ← Documentación principal
├── ANALISIS_DETALLADO.md                    ← Análisis profundo
└── EJECUTAR.md                              ← Este archivo
```

---

## 📋 Requisitos Previos

- **Java**: Versión 25 o superior
- **Maven** (opcional): Para compilación con Maven
- **IDE**: IntelliJ IDEA, Eclipse, VS Code o similar (opcional)

### Verificar Versión de Java

```bash
java -version
```

Debe mostrar algo como:
```
java version "25" 2025-XX-XX
Java(TM) SE Runtime Environment (build 25+XX)
Java HotSpot(TM) 64-Bit Server VM (build 25+XX, mixed mode, sharing)
```

---

## 🖥️ Método 1: Compilación Manual (Terminal)

### Paso 1: Navegar al Directorio

```bash
cd /Users/mariajosecruzduarte/IdeaProjects/Cruz--post2-u6
```

### Paso 2: Compilar el Proyecto

```bash
javac -d target/classes src/main/java/com/universidad/antipatrones/*.java
```

**Explicación**:
- `javac`: Compilador Java
- `-d target/classes`: Directorio de salida para los .class compilados
- `src/main/java/com/universidad/antipatrones/*.java`: Todos los archivos .java

### Paso 3: Ejecutar el Programa

```bash
java -cp target/classes com.universidad.antipatrones.Main
```

**Explicación**:
- `java`: Ejecutor Java
- `-cp target/classes`: Classpath donde están los archivos compilados
- `com.universidad.antipatrones.Main`: Clase main a ejecutar

### Salida Esperada

```
Procesando pedido: P001
  Estrategia: VIP  Descuento: 45%
  Total final: $660.00
 [ALERTA] Pedido de alto valor: P001
Pedido P001 procesado.
Procesando pedido: P002
  Estrategia: VIP  Descuento: 30%
  Total final: $420.00
Pedido P002 procesado.
Procesando pedido: P003
  Estrategia: PREMIUM  Descuento: 15%
  Total final: $255.00
Pedido P003 procesado.
Procesando pedido: P004
  Estrategia: ESTANDAR  Descuento: 8%
  Total final: $138.00
Pedido P004 procesado.
Procesando pedido: P005
  Estrategia: ESTANDAR  Descuento: 0%
  Total final: $80.00
Pedido P005 procesado.
```

---

## 🔨 Método 2: Compilación con Maven

### Paso 1: Navegar al Directorio

```bash
cd /Users/mariajosecruzduarte/IdeaProjects/Cruz--post2-u6
```

### Paso 2: Limpiar y Compilar

```bash
mvn clean compile
```

**Explicación**:
- `clean`: Elimina compilaciones anteriores
- `compile`: Compila el proyecto

### Paso 3: Ejecutar

**Opción A: Con Maven**
```bash
mvn exec:java -Dexec.mainClass="com.universidad.antipatrones.Main"
```

**Opción B: Manual después de compilar**
```bash
java -cp target/classes com.universidad.antipatrones.Main
```

---

## 🏛️ Método 3: IntelliJ IDEA (Recomendado para macOS)

### Paso 1: Abrir el Proyecto

1. Abre IntelliJ IDEA
2. `File` → `Open`
3. Navega a `/Users/mariajosecruzduarte/IdeaProjects/Cruz--post2-u6`
4. Click en `Open`

### Paso 2: Configurar el SDK de Java

1. `IntelliJ IDEA` → `Preferences` (o `Cmd + ,`)
2. `Project Settings` → `Project` → `SDK`
3. Selecciona Java 25 (o más reciente)
4. Click `Apply`

### Paso 3: Compilar

1. `Build` → `Build Project` (o `Cmd + B`)
2. Espera a que termine la compilación

### Paso 4: Ejecutar

**Opción A: Ejecutar Main directamente**
1. Abre `src/main/java/com/universidad/antipatrones/Main.java`
2. Haz clic en el icono de play verde junto a `public static void main`
3. Selecciona `Run 'Main.main()'`

**Opción B: Usar la terminal integrada**
1. `View` → `Tool Windows` → `Terminal`
2. Ejecuta:
```bash
java -cp target/classes com.universidad.antipatrones.Main
```

### Paso 5: Ver Salida

La consola de ejecución mostrará la salida del programa.

---

## 🐧 Método 4: VS Code (Con Java Extension Pack)

### Paso 1: Instalar Extensiones

1. Abre VS Code
2. `Extensions` (Cmd + Shift + X)
3. Busca e instala:
   - "Extension Pack for Java" (Microsoft)
   - "Maven for Java" (Microsoft)

### Paso 2: Abrir la Carpeta del Proyecto

1. `File` → `Open Folder`
2. Selecciona `/Users/mariajosecruzduarte/IdeaProjects/Cruz--post2-u6`

### Paso 3: Compilar

1. Abre la terminal integrada (Ctrl + `)
2. Ejecuta:
```bash
javac -d target/classes src/main/java/com/universidad/antipatrones/*.java
```

### Paso 4: Ejecutar

1. En la terminal, ejecuta:
```bash
java -cp target/classes com.universidad.antipatrones.Main
```

---

## 🛠️ Método 5: Eclipse IDE

### Paso 1: Crear Proyecto

1. Abre Eclipse
2. `File` → `New` → `Java Project`
3. Nombre: `Cruz--post2-u6`
4. Finish

### Paso 2: Importar Código

1. Copia el contenido de `src/main/java/com/universidad/antipatrones/` a `src/` del proyecto en Eclipse
2. Eclipse detectará automáticamente los archivos

### Paso 3: Compilar

1. Eclipse compila automáticamente al guardar

### Paso 4: Ejecutar

1. Abre `Main.java`
2. Click derecho → `Run As` → `Java Application`

---

## 📊 Script de Ejecución Rápida (Bash)

Crea un archivo `run.sh` en la raíz del proyecto:

```bash
#!/bin/bash

echo "=== Compilando proyecto ==="
javac -d target/classes src/main/java/com/universidad/antipatrones/*.java

if [ $? -ne 0 ]; then
    echo "❌ Error en compilación"
    exit 1
fi

echo "✅ Compilación exitosa"
echo ""
echo "=== Ejecutando programa ==="
java -cp target/classes com.universidad.antipatrones.Main

echo ""
echo "=== Ejecución completada ==="
```

### Usar el Script

1. Guarda como `run.sh`
2. Dale permisos:
```bash
chmod +x run.sh
```
3. Ejecuta:
```bash
./run.sh
```

---

## 🧪 Ejecutar Tests (Estructura para agregar)

Si deseas agregar tests con JUnit 5:

### 1. Crear estructura de tests

```
src/test/java/com/universidad/antipatrones/
├── DescuentoVIPTest.java
├── DescuentoPremiumTest.java
├── DescuentoEstandarTest.java
└── SelectorEstrategiaTest.java
```

### 2. Ejemplo de Test

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DescuentoVIPTest {
    
    @Test
    void testDescuentoVIPAlto() {
        EstrategiaDescuento vip = new DescuentoVIP();
        Pedido pedido = new Pedido("P001", "VIP", 1200, "VIPEXTRA");
        assertEquals(0.45, vip.calcular(pedido));
    }
    
    @Test
    void testDescuentoVIPMedio() {
        EstrategiaDescuento vip = new DescuentoVIP();
        Pedido pedido = new Pedido("P002", "VIP", 600, "VIP20");
        assertEquals(0.30, vip.calcular(pedido));
    }
}
```

### 3. Ejecutar Tests

```bash
# Con Maven
mvn test

# Con gradle
gradle test
```

---

## 🐛 Solución de Problemas

### Error: "Java is not recognized"

**Problema**: Java no está instalado o no está en PATH

**Solución**:
1. Descarga Java 25 desde [oracle.com](https://www.oracle.com/java/technologies/downloads/)
2. Instala siguiendo las instrucciones
3. Reinicia la terminal

### Error: "cannot find symbol"

**Problema**: Archivos no compilados o errores de sintaxis

**Solución**:
```bash
# Verifica que estés en el directorio correcto
cd /Users/mariajosecruzduarte/IdeaProjects/Cruz--post2-u6

# Recompila todo desde cero
rm -rf target/classes
mkdir -p target/classes
javac -d target/classes src/main/java/com/universidad/antipatrones/*.java
```

### Error: "Could not find or load main class"

**Problema**: Classpath incorrecto o archivo Main no compilado

**Solución**:
```bash
# Verifica que target/classes/com/universidad/antipatrones/Main.class existe
ls -la target/classes/com/universidad/antipatrones/

# Si no existe, recompila
javac -d target/classes src/main/java/com/universidad/antipatrones/*.java
```

### IntelliJ: "SDK not configured"

**Problema**: SDK de Java no está configurado

**Solución**:
1. `Cmd + ,` (Preferences)
2. `Project` → `Project Settings` → `SDK`
3. Click en `+` y selecciona tu instalación de Java 25
4. Click `Apply`

---

## ✅ Checklist de Ejecución

- [ ] Java 25+ instalado: `java -version`
- [ ] Navegación al directorio correcto
- [ ] Compilación exitosa sin errores
- [ ] Archivo `Main.class` existe en `target/classes`
- [ ] Ejecución produces salida esperada
- [ ] Todos los 5 pedidos se procesan
- [ ] Se muestra alerta para pedido P001

---

## 📝 Notas Importantes

1. **Directorio de trabajo**: Todos los comandos se ejecutan desde la raíz del proyecto
2. **Classpath**: El `-cp target/classes` es importante para que Java encuentre las clases compiladas
3. **Java 25+**: El proyecto requiere características de Java 25 (Text Blocks, etc.)
4. **Compilación**: Es necesario compilar antes de ejecutar

---

## 🎯 Próximos Pasos

1. ✅ Ejecutar el programa exitosamente
2. 📖 Leer el [README.md](README.md) para entender la arquitectura
3. 📚 Revisar [ANALISIS_DETALLADO.md](ANALISIS_DETALLADO.md) para el análisis profundo
4. 🔍 Explorar el código fuente en `src/main/java/com/universidad/antipatrones/`
5. 🧪 Agregar tests unitarios si deseas

---

## 📞 Soporte

Si encuentras problemas:
1. Verifica que estés en el directorio correcto
2. Comprueba la versión de Java: `java -version`
3. Intenta limpiar compilaciones anteriores: `rm -rf target/classes`
4. Recompila desde cero

¡Éxito! 🚀

