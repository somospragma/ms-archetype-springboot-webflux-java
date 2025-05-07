@workspace 

# Reporte de Cumplimiento de Reglas de Arquitectura

Por favor, analiza mi proyecto y genera un reporte detallado sobre el cumplimiento de las reglas de arquitectura configuradas en `.amazonq/rules/`. 

## Instrucciones:

1. Analiza solo el código dentro de la carpeta `src`, ignorando la carpeta `target`
2. Verifica el cumplimiento de todas las reglas definidas en los archivos:
   - `.amazonq/rules/architecture/project-architecture.md`
   - `.amazonq/rules/architecture/domain.md`
   - `.amazonq/rules/architecture/application.md`
   - `.amazonq/rules/architecture/infrastructure.md`
   - `.amazonq/rules/bestpractice/best-practices.md`
   - `.amazonq/rules/bestpractice/documentation.md`

3. Para cada regla, proporciona:
   - Si se cumple o no
   - Ejemplos específicos de incumplimiento (si los hay)
   - Recomendaciones para corregir los problemas

4. Organiza el reporte por categorías de reglas:
   - Estructura del Proyecto
   - Reglas de Dominio
   - Reglas de Aplicación
   - Reglas de Infraestructura
   - Mejores Prácticas

5. Incluye un resumen ejecutivo al inicio con estadísticas de cumplimiento en un archivo Markdown con nombre architecture-compliance-report.md, el archivo tendria  un tabla por cada regla con el porcentaje de cumplimiento, cuantas reglas cumple y cuantas regla no cumple y una breve descripción de cada regla. y donde se indique si el proyecto cumple o no con la regla. y cuales reeglas se incumplen en donde se incumple 
   - Porcentaje de cumplimiento por categoría
   - Total de reglas en el proyecto
   - Total de reglas cumplidas y no cumplidas
6. Usa un formato claro y conciso, evitando jerga técnica innecesaria.
7. Asegúrate de que el reporte sea fácil de entender para un desarrollador con conocimientos básicos de arquitectura de software.
8. Genera el reporte en un archivo Markdown llamado architecture-compliance-report.md.
9. Incluye ejemplos de código donde sea relevante para ilustrar los incumplimientos.
10. Proporciona recomendaciones prácticas para corregir los problemas encontrados.
11. Asegúrate de que el reporte sea legible y bien estructurado, utilizando encabezados, listas y tablas según sea necesario.
12. No incluyas información sensible o privada del proyecto.
13. Asegúrate de que el reporte esté en español.
14. No incluyas información de configuración de AmazonQ en el reporte.
15. Recomienda el uso del agente de AmazonQ reviewer para facilitar la rtevisión de vulnerabilidades en código o dependencias.
16. Recomienda el uso de herramientas de análisis estático de código para detectar problemas de arquitectura y mejores prácticas.
17. Recomienda el uso de herramientas de análisis de dependencias para detectar problemas de arquitectura y mejores prácticas. con herramientas como OWASP Dependency-Check, Snyk, trivy.