# Reglas de Arquitectura: Capa de Aplicación

## 3.0 - Inmutabilidad en Casos de Uso

**Descripción:** Las clases de casos de uso deben garantizar inmutabilidad para evitar problemas de concurrencia.

**Verificación:**
- Todos los atributos de instancia en clases de casos de uso deben ser declarados como `final`
- No se permiten atributos de instancia mutables
- Las constantes estáticas (`static final`) no están sujetas a esta regla

## 3.1 - Dependencias Restringidas

**Descripción:** Las clases de la capa de aplicación solo deben interactuar con la capa de dominio y otras capas permitidas.

**Verificación:**
- Las clases de aplicación solo pueden importar:
  - Clases de la capa de dominio
  - Interfaces de puertos
  - Utilidades estándar de Java
- No deben importar clases de la capa de infraestructura
- No deben importar implementaciones concretas de repositorios o servicios externos

## 3.2 - Convención de Nombres para Interfaces de Puertos

**Descripción:** Las interfaces en los paquetes de puertos deben seguir una convención de nombres específica.

**Verificación:**
- Todas las interfaces en paquetes llamados `port` o `ports` deben comenzar con el prefijo "I"
- Ejemplos correctos: `IUserRepository`, `INotificationService`

## 3.3 - Puertos como Interfaces

**Descripción:** Todos los puertos deben ser definidos como interfaces, no como clases concretas.

**Verificación:**
- Todos los archivos en paquetes llamados `port` o `ports` deben ser interfaces
- No se permiten clases concretas, enumeraciones u otros tipos en estos paquetes