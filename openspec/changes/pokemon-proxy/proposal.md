# Propuesta: API Proxy de Pokémon

## Resumen

Esta propuesta introduce un servicio backend que actúa como un proxy para la API pública https://pokeapi.co/. El servicio expondrá un endpoint REST simplificado y estable para consultar información de Pokémon por nombre.

## Motivación

Consumir directamente la PokéAPI desde aplicaciones cliente genera un fuerte acoplamiento y reduce la flexibilidad. Cualquier cambio en la API externa puede afectar a los consumidores.

Al implementar una capa proxy, se obtiene control total sobre el contrato expuesto, permitiendo mejorar la estabilidad, el rendimiento y la mantenibilidad del sistema.

## Alcance

La implementación inicial incluirá:

- Un endpoint: `GET /pokemon/{name}`
- Integración con PokéAPI (`/api/v2/pokemon/{name}`)
- Transformación de la respuesta externa a un formato simplificado:
  - id
  - name
  - types
  - abilities
- Manejo centralizado de errores:
  - 404 cuando el Pokémon no exista
  - 502 cuando falle la API externa

## Fuera de Alcance

Las siguientes funcionalidades no están incluidas en esta fase:

- Autenticación y autorización
- Caché
- Rate limiting
- Endpoints adicionales (abilities, types, etc.)

## Resultado Esperado

Después de la implementación:

- Los clientes podrán consultar Pokémon mediante una API simple y estable
- Se elimina el acoplamiento con la API externa
- Se obtendrán respuestas consistentes y controladas ante errores