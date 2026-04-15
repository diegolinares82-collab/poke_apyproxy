## NEW Requirements

### Requirement: Imagen Docker de Producción
El sistema DEBE ser capaz de empaquetarse en una imagen Docker funcional.

#### Scenario: Construcción de la imagen
- **WHEN** se ejecuta `docker build -t pokeproxy .`
- **THEN** el proceso termina exitosamente y genera una imagen que contiene el JAR ejecutable.

#### Scenario: Ejecución del contenedor
- **GIVEN** la imagen construida
- **WHEN** se ejecuta el contenedor pasando `-e PORT=9000`
- **THEN** la aplicación Spring Boot debe iniciar y escuchar en el puerto 9000.
