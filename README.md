[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10574713&assignment_repo_type=AssignmentRepo)
# API de Atrativos Turísticos

Esta API permite o cadastro e gerenciamento de atrativos turísticos, bem como a associação desses atrativos a uma cidade.


## Entidades
A   API possui duas entidades principais:

## Atrativo
O Atrativo é o elemento principal da API. Cada Atrativo é composto por:
* id (obrigatório): identificador único do atrativo, em formato Long.
* nome (obrigatório): nome do atrativo.
* endereço (obrigatório): endereço do atrativo.
* descrição (obrigatório): descrição detalhada do atrativo.
* categoria(obrigatório): categoria do atrativo, em formato ENUM.
* cidade (obrigatório): cidade à qual o atrativo pertence.

## Cidade
A Cidade representa a localização geográfica do Atrativo. Cada Cidade é composta por:

* id (obrigatório): identificador único da cidade, em formato Long.
* nome (obrigatório): nome da cidade.
* estado(obrigatório): estado da cidade.
* atrativos: lista de atrativos daquela cidade.


## Endpoints
A API disponibiliza os seguintes endpoints para manipulação das entidades:
+ Atrativos
  * GET /api/attractive
  * GET /api/attractive/all
  * POST /api/attractive
  * PUT /api/attractive/{id}
  * DELETE /api/attractive/{id}
+ Cidade
  * GET /api/city
  * GET /api/city/all
  * POST /api/city
  * PUT /api/city/{id}
  * DELETE /api/city/{id}
