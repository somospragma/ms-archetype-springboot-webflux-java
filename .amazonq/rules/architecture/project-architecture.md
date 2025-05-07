# Reglas de Arquitectura: Estructura del Proyecto

## 1.0 - Separación de Responsabilidades por Capa

**Descripción:** El proyecto debe mantener una clara separación de responsabilidades entre las tres capas principales.

**Verificación:**
- **Capa de Dominio:**
  - Debe contener solo modelado del dominio, entidades, objetos de valor y lógica de negocio fundamental
  - No debe contener lógica de infraestructura ni orquestación de casos de uso
  - No debe importar clases de las capas de aplicación o infraestructura

- **Capa de Aplicación:**
  - Debe contener la lógica específica de los casos de uso del sistema
  - Puede orquestar la interacción entre objetos del dominio
  - No debe contener lógica de modelado del dominio ni conexiones directas a servicios externos
  - No debe importar directamente clases de la capa de infraestructura

- **Capa de Infraestructura:**
  - Debe contener únicamente la lógica de conexiones e interacciones con el mundo exterior
  - Implementa los detalles técnicos de interacciones externas
  - No debe contener lógica de negocio del dominio ni casos de uso
  - Puede importar clases del dominio y aplicación, pero no viceversa

## 1.1 - Estructura de Paquetes Obligatoria

**Descripción:** El proyecto debe tener exactamente tres paquetes raíz con nombres específicos.

**Verificación:**
- Deben existir exactamente tres paquetes raíz:
  - `application`
  - `domain`
  - `infrastructure`
- La nomenclatura debe ser exacta (sensible a mayúsculas y minúsculas)
- No deben existir otros paquetes en el nivel raíz que puedan generar confusión