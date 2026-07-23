# diti4_spring_mvc

Application web CRUD **Spring MVC** (sans Spring Boot) de gestion de **produits** et de **types de produits**, avec persistance **JPA/Hibernate** sur **PostgreSQL**. Expose une interface JSP et une API REST documentée via **Swagger UI**.

## Stack technique

| Composant | Version |
|-----------|---------|
| Java | 25 (compilé via Maven) |
| Spring (context / webmvc / orm) | 5.3.30 |
| Hibernate | 5.6.15.Final |
| PostgreSQL (driver) | 42.7.11 |
| API Servlet | javax (4.0.1) — Tomcat **9** |
| Packaging | WAR |

> ⚠️ Le projet utilise l'API **`javax`** (Java EE). Il faut un **Tomcat 9** — Tomcat 10+ (`jakarta`) est incompatible.

## Prérequis

- JDK 17+ (testé avec JDK 25)
- Maven 3.9+
- PostgreSQL en écoute locale

## Configuration base de données

La configuration est en dur dans `src/main/java/diti/config/AppConfig.java`, méthode `dataSource()` :

```java
ds.setUrl("jdbc:postgresql://localhost:5433/diti4_spring_mvc");
ds.setUsername("postgres");
ds.setPassword("****");
```

Adapte l'URL (hôte, port, nom de base), l'utilisateur et le mot de passe à ton installation.
La base `diti4_spring_mvc` doit exister ; les tables (`products`, `type_produits`) sont créées/mises à jour automatiquement au démarrage (`hibernate.hbm2ddl.auto = update`).

## Démarrer l'application

Un plugin **Cargo** embarque un Tomcat 9 : aucune installation de serveur n'est nécessaire.

```bash
mvn package cargo:run
```

L'application est alors disponible sur **http://localhost:8080** (`Ctrl+C` pour arrêter).

## API REST

### Produits — `/api/produit`

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/produit` | Lister les produits |
| GET | `/api/produit/{id}` | Détail d'un produit |
| POST | `/api/produit` | Créer un produit |
| DELETE | `/api/produit/delete/{id}` | Supprimer un produit |

Exemple de corps (POST) — le `typeProduit` est optionnel :

```json
{ "libelle": "Laptop", "prix": 999.9, "typeProduit": { "id": 1 } }
```

### Types de produit — `/api/typeproduit`

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/typeproduit` | Lister les types |
| GET | `/api/typeproduit/{id}` | Détail d'un type |
| POST | `/api/typeproduit` | Créer un type |
| DELETE | `/api/typeproduit/delete/{id}` | Supprimer un type |

Exemple de corps (POST) :

```json
{ "libelle": "Electronique" }
```

## Documentation Swagger

Interface Swagger UI (spec OpenAPI 3) :

### http://localhost:8080/swagger-ui.html

La spec brute est servie sur `/openapi.json` (fichier `src/main/resources/openapi.json`).

## Interface web (JSP)

| URL | Description |
|-----|-------------|
| `/produit` | Liste des produits |
| `/produit/new` | Formulaire d'ajout |
| `/produit/edit/{id}` | Formulaire d'édition |

## Structure du projet

```
src/main/java/diti/
├── config/        AppConfig (DataSource, JPA), SwaggerController
├── entity/        Produit, TypeProduit
├── repository/    interfaces + impl/ (accès EntityManager)
├── service/       interfaces + impl/ (logique métier)
├── controller/    ProduitController (MVC / JSP)
└── REST/          ProduitRestController, TypeProduitRestController
src/main/resources/openapi.json      spec OpenAPI
src/main/webapp/WEB-INF/
├── web.xml                          DispatcherServlet + HiddenHttpMethodFilter
├── spring-servlet.xml               component-scan, view resolver, webjars
└── views/                           produit.jsp, form-product.jsp
```
