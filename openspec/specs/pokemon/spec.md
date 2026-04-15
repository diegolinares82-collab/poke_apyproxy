# Pokemon API Spec

## GET /pokemon/{name}

### Description
Returns simplified information of a Pokémon by name.

### Path Parameters
- name (string, required): Pokémon name

### Response 200
```json
{
  "id": 25,
  "name": "pikachu",
  "types": ["electric"],
  "abilities": ["static", "lightning-rod"]
}
```
### Response 404
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Pokémon not found"
}
```
### Response 502
```json
{
  "status": 502,
  "error": "Bad Gateway",
  "message": "Error calling external API"
}
```