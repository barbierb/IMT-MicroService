# IMT-MicroService - TODO List
## Présentation
L'appliation est une todo list

- element
  - 1 catégorie
  - date de début
  - date de fin
  - description
  - état complété ou non
  - qui est chargé de la faire, de base celle qui la crée
  - non important / important / urgent / non urgent
 
 - systeme de login
  - email
  - mot de passe
### Base de donnée
> CREATE TABLE category (
> id INT,
> id_user INT,
> name VARCHAR(45),
> emoji VARCHAR(1),
> PRIMARY KEY (id,id_user));

> CREATE TABLE element (
> id INT,
> id_user INT NOT NULL,
> id_category INT NOT NULL,
> id_priority INT NOT NULL,
> date_start BIGINT NULL,
> date_end BIGINT NOT NULL,
> description VARCHAR(255),
> state BIT NOT NULL,
> PRIMARY KEY (id));

> CREATE TABLE user (
> id INT,
> login VARCHAR(250),
> password VARCHAR(250),
> PRIMARY KEY (id));

> CREATE TABLE priority (
> id INT,
> name VARCHAR(45),
> PRIMARY KEY (id));

- interface web

- api rest
  - login
  - register
  - create element
  - remove element
  - modifier element
  - lister les elements
  - afficher les détails d'un element
  - lister les catégories
  - créer catégorie
  - supprimer catégorie
  - modifier catégorie

## Installation

## Documentation
### SpringBoot Initializer
https://start.spring.io/

### Notation
résumé app, schema bdd, openapi, répartition travail par personne (10-15mins)

## TODO
- [x] Trouver sujet du projet
- [ ] Rediger un court paragraphe ici de la présentation et l'objectif du projet
- [ ] faire un schema bdd
- [ ] faire une liste des routes de l'appli
- [ ] tester le projet git en local x2
- [ ] répartir le travail
