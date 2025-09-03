# Sistema de Concesionaria de Vehículos

## Descripción
Proyecto realizado con mis compañeros para la materia POO. Sistema de gestión de pedidos para una concesionaria de vehículos que implementa varios patrones de diseño:

- **Facade Pattern**: Para centralizar y simplificar el acceso a la lógica principal del sistema mediante una fachada (`ConcesionariaFacade`).
- **State Pattern**: Para manejar los diferentes estados del pedido (Ventas, Cobranzas, Impuestos, Embarque, Logística, Entrega)
- **Strategy Pattern**: Para calcular impuestos según el tipo de vehículo y formas de pago
- **Observer Pattern**: Para notificar a diferentes áreas cuando cambia el estado del pedido

## Estructura del Proyecto

> **Nota:** La estructura solo muestra archivos fuente relevantes. Los archivos `.class` generados por la compilación **no deben incluirse** en la estructura ni en el control de versiones.

```
src/main/java/concesionaria/
├── ConcesionariaMain.java          # Clase principal
├── exception/                      # Excepciones personalizadas
│   ├── DuplicateException.java
│   └── ProcessingException.java
├── facade/                         # Fachada del sistema
│   └── ConcesionariaFacade.java
├── menu/                           # Menús visuales y pruebas
│   ├── MenuPrincipal.java
│   └── TestConcesionaria.java
├── model/                          # Clases del modelo
│   ├── Auto.java
│   ├── Camion.java
│   ├── Camioneta.java
│   ├── Cliente.java
│   ├── Moto.java
│   ├── PedidoCompra.java
│   ├── Vehiculo.java
│   └── Vendedor.java
├── observer/                       # Patrón Observer
│   ├── NotificadorArea.java
│   └── Observer.java
├── state/                          # Patrón State
│   ├── EstadoCobranzas.java
│   ├── EstadoEmbarque.java
│   ├── EstadoEntrega.java
│   ├── EstadoImpuestos.java
│   ├── EstadoLogistica.java
│   ├── EstadoPedido.java
│   └── EstadoVentas.java
├── strategy/                       # Patrón Strategy
│   ├── CalculadoraImpuestos.java
│   ├── CalculadoraImpuestosAuto.java
│   ├── CalculadoraImpuestosCamion.java
│   ├── CalculadoraImpuestosCamioneta.java
│   ├── CalculadoraImpuestosMoto.java
│   ├── FormaPago.java
│   ├── PagoContado.java
│   ├── PagoTarjeta.java
│   └── PagoTransferencia.java
├── validation/                     # Validadores
│   ├── ValidadorCliente.java
│   ├── ValidadorDatosFacturacion.java
│   ├── ValidadorFormaPago.java
│   ├── ValidadorPedido.java
│   ├── ValidadorVehiculo.java
│   └── ValidadorVendedor.java
└── util/                           # Clases de utilidad
    ├── CambioEstado.java
    ├── ConfiguracionAdicional.java
    └── DatosFacturacion.java
```


### Compilación
```bash
# Navegar al directorio de código fuente
cd src/main/java

# Compilar
javac concesionaria/ConcesionariaMain.java

#Errores de encoding
javac -encoding UTF-8 concesionaria/ConcesionariaMain.java

# Ejecutar
java concesionaria.ConcesionariaMain
```

## Funcionalidades

### Gestión de Pedidos
- Creación de pedidos con cliente, vehículo, vendedor
- Cálculo automático de impuestos según tipo de vehículo
- Gestión de configuraciones adicionales
- Seguimiento de estados del pedido

### Tipos de Vehículos Soportados
- **Auto**: Impuestos 21% nacional + 5% provincial general + 1% provincial adicional (total 27%)
- **Camioneta**: Impuestos 10% nacional + 5% provincial general + 2% provincial adicional (total 17%)
- **Moto**: Impuestos 5% provincial general + 1% provincial adicional (total 6%)
- **Camión**: Impuestos 5% provincial general + 2% provincial adicional (total 7%)

### Estados del Pedido
1. **Ventas**: Estado inicial
2. **Cobranzas**: Procesamiento de pago
3. **Impuestos**: Cálculo y aplicación de impuestos
4. **Embarque**: Preparación para envío
5. **Logística**: Gestión de transporte
6. **Entrega**: Entrega final al cliente

### Formas de Pago
- Contado
- Tarjeta de crédito
- Transferencia bancaria

## Ejemplo de Uso

El sistema crea automáticamente un pedido de ejemplo y lo procesa a través de todos los estados, mostrando:
- Información del pedido
- Notificaciones a diferentes áreas
- Historial de cambios de estado
- Cálculos de impuestos y totales


## Funcionamiento de Testeos

El proyecto incluye una clase de pruebas automáticas llamada `TestConcesionaria` que verifica el correcto funcionamiento de las principales funcionalidades del sistema. Al ejecutar los tests, se realizan las siguientes comprobaciones:

- **Creación de pedidos válidos**: Se prueba que se puedan crear pedidos correctamente con datos válidos.
- **Validación de clientes y vehículos**: Se verifica que el sistema rechace pedidos con clientes o vehículos inexistentes.
- **Patrón Strategy (formas de pago)**: Se crean pedidos usando distintas formas de pago para asegurar que el cálculo y procesamiento es correcto.
- **Patrón State (cambio de estados del pedido)**: Se procesan pedidos para comprobar la transición entre los distintos estados.
- **Patrón Observer (notificaciones)**: Se valida que las notificaciones a las áreas correspondientes se realicen al cambiar el estado de un pedido.
- **Manejo de excepciones**: Se comprueba que el sistema arroje las excepciones adecuadas ante situaciones como clientes duplicados.
- **Búsquedas**: Se testean las búsquedas de clientes, vehículos y vendedores tanto existentes como inexistentes.

Al finalizar, se muestra un resumen con la cantidad de pruebas exitosas y el porcentaje de éxito. Si todas las pruebas pasan, se indica que el sistema funciona correctamente.

Para ejecutar los tests, simplemente ejecuta el método correspondiente desde el menú principal o desde la clase `TestConcesionaria`.

## Archivos `.class`

Cuando compilas los archivos `.java`, el compilador de Java genera archivos `.class` en el mismo directorio o en el directorio de salida configurado. Estos archivos contienen el bytecode que la Máquina Virtual de Java (JVM) ejecuta.  
**No incluyas los archivos `.class` en la estructura del proyecto ni en el control de versiones.**

