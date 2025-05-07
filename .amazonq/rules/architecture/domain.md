# Reglas de Arquitectura: Capa de Dominio

## 2.0 - Dependencias Externas Limitadas en el Dominio

**Descripción:** Las clases en la capa de dominio deben mantener independencia de tecnologías externas, limitando sus importaciones a paquetes permitidos.

**Verificación:**
- Las clases del dominio solo pueden importar:
  - Paquetes de Java (`java.*`)
  - Paquetes de Lombok (`lombok.*`)
  - Anotaciones básicas de Spring Framework (`org.springframework.*`)
  - Anotaciones básicas de Project Reactor (`reactor.core.*`)
  - Paquetes del propio dominio (`*.domain.*`)
- No deben importar frameworks de persistencia, web, ni tecnologías específicas de infraestructura

## 2.1 - Nomenclatura Agnóstica a Tecnología

**Descripción:** Las clases del dominio no deben tener sufijos tecnológicos para mantener una nomenclatura pura del dominio.

**Verificación:**
- Las clases del dominio no deben terminar con sufijos como:
  - `Dto`, `Request`, `Response`, `Entity`, `Dao`
  - `Repository`, `Service`, `Controller`
  - Cualquier otro sufijo que indique una tecnología específica

## 2.2 - Sin Referencias a Herramientas Tecnológicas

**Descripción:** Las clases del dominio no deben contener nombres de herramientas o tecnologías específicas.

**Verificación:**
- Las clases del dominio no deben contener en su nombre palabras como:
  - `Kafka`, `Redis`, `Mongo`, `Hibernate`, `Spring`
  - Cualquier otra referencia a tecnologías específicas

## 2.3 - Responsabilidades del Dominio

**Descripción:** La capa de dominio debe contener únicamente el modelado y la lógica de negocio fundamental.

**Verificación:**
- La capa de dominio debe contener:
  - Entidades y objetos de valor
  - Reglas de negocio intrínsecas al dominio
  - Interfaces de repositorios (puertos)
- No debe contener:
  - Lógica de orquestación de casos de uso
  - Implementaciones de repositorios
  - Código relacionado con UI o servicios externos