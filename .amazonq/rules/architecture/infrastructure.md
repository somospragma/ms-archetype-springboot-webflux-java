# Reglas de Arquitectura: Capa de Infraestructura

## 4.0 - Adaptadores Implementan Puertos

**Descripción:** Los adaptadores de infraestructura deben implementar interfaces de puertos o extender clases que implementen puertos.

**Verificación:**
- Todas las clases en la capa de infraestructura que actúen como adaptadores deben:
  - Implementar directamente al menos una interfaz definida en los paquetes de puertos, o
  - Extender una clase que implemente al menos una interfaz de puerto
- Esto aplica a repositorios, gateways, clientes de servicios externos y otros adaptadores
- No se permiten adaptadores que no implementen ningún puerto

## 4.1 - Responsabilidades de la Infraestructura

**Descripción:** La capa de infraestructura debe encargarse únicamente de las interacciones con sistemas externos.

**Verificación:**
- La capa de infraestructura debe contener:
  - Implementaciones de repositorios
  - Clientes de servicios externos
  - Configuraciones de frameworks
  - Adaptadores para sistemas externos
- No debe contener:
  - Lógica de negocio del dominio
  - Orquestación de casos de uso
  - Definiciones de entidades o reglas de negocio

## 4.2 - Mapeo de Datos

**Descripción:** La infraestructura debe mapear correctamente entre modelos externos y modelos del dominio.

**Verificación:**
- Las clases de infraestructura deben convertir:
  - Entidades de persistencia a modelos de dominio
  - Respuestas de APIs externas a modelos de dominio
- No deben exponer modelos externos (DTOs, entidades JPA) a las capas superiores