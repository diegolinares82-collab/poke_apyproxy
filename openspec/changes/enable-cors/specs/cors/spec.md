## NEW Requirements

### Requirement: Soporte para CORS Global
El sistema DEBE permitir peticiones desde orígenes cruzados (CORS) para todos los endpoints de la API.

#### Scenario: Petición desde un origen distinto
- **GIVEN** la API está ejecutándose en `localhost:8080`
- **WHEN** se recibe una petición `OPTIONS` desde `host:3000`
- **THEN** el sistema responde con los headers de CORS apropiados (Access-Control-Allow-Origin, etc.)

### Requirement: Orígenes Configurables
La lista de orígenes permitidos DEBE ser configurable a través de archivos de propiedades.

#### Scenario: Configuración de orígenes
- **GIVEN** `cors.allowed-origins` está configurado como `*`
- **THEN** cualquier origen puede consumir la API.
