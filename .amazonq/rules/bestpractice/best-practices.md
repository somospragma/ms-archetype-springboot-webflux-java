# Reglas de Mejores Prácticas

## 1.0 - Programación Reactiva

**Descripción:** El código debe seguir los principios de programación reactiva cuando se utiliza WebFlux.

**Verificación:**
- Usar tipos reactivos (Mono/Flux) en lugar de tipos bloqueantes
- Evitar operaciones bloqueantes dentro de flujos reactivos
- Utilizar operadores reactivos adecuados para transformaciones
- No mezclar código imperativo y reactivo

## 2.0 - Manejo de Errores

**Descripción:** El código debe implementar un manejo de errores adecuado y consistente.

**Verificación:**
- Usar operadores como `onErrorResume`, `onErrorMap` para manejar errores en flujos reactivos
- Implementar manejo de errores centralizado
- Evitar capturar excepciones genéricas sin rethrow
- Proporcionar mensajes de error significativos

## 3.0 - Pruebas

**Descripción:** El código debe tener pruebas unitarias y de integración adecuadas.

**Verificación:**
- Cada caso de uso debe tener pruebas unitarias
- Las clases de dominio críticas deben tener pruebas unitarias
- Los adaptadores de infraestructura deben tener pruebas de integración
- Usar StepVerifier para probar código reactivo

## 4.0 - Nomenclatura

**Descripción:** El código debe seguir convenciones de nomenclatura consistentes y claras. todos los nombres de clases, métodos y variables deben ser descriptivos y seguir las convenciones de nomenclatura de Java en inglés.

**Verificación:**
- Clases deben usar PascalCase
- Métodos y variables deben usar camelCase
- Evitar abreviaciones innecesarias
- Usar nombres descriptivos que reflejen la funcionalidad
- Evitar nombres genéricos como "temp", "data", etc.
- Usar nombres de paquetes en minúsculas y separados por puntos
- Evitar nombres de paquetes que sean demasiado generales o vagos
- Usar nombres de paquetes que reflejen la estructura del proyecto
- Evitar nombres de paquetes que sean demasiado largos o complicados
- Usar nombres de paquetes que sean fáciles de recordar y entender
- Nombres de paquetes, clases metodos y variables deben ser en inglés.
- Evitar nombres de paquetes, clases metodos y variables en español.