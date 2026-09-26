# TP1 – Architecture MVC avec Spring MVC

Projet réalisé dans le cadre du TP1 du module *Architectures Applications* (ISET Sfax, Département Technologies de l'Informatique).

Il illustre l'architecture **MVC** (Modèle - Vue - Contrôleur) à travers une application de gestion de produits, développée avec **Spring Boot**, puis étendue avec une **API REST** pour les opérations CRUD.

## Stack technique

- Java 17
- Spring Boot 3.x
- Spring Web (MVC + REST)
- Spring Data JPA
- Thymeleaf (moteur de vues)
- Base de données H2 (en mémoire)
- Maven

## Architecture du projet

```
src/
├── main/
│   ├── java/org/iset/application/
│   │   ├── controller/
│   │   │   ├── ProduitController.java       # Contrôleur MVC (vues Thymeleaf)
│   │   │   └── ProduitRestController.java   # API REST (JSON)
│   │   ├── model/
│   │   │   └── Produit.java                 # Entité JPA
│   │   ├── repository/
│   │   │   └── ProduitRepository.java       # Accès aux données (JpaRepository)
│   │   ├── service/
│   │   │   └── ProduitService.java          # Logique métier
│   │   └── Application.java                 # Classe principale Spring Boot
│   └── resources/
│       ├── templates/
│       │   └── produits.html                # Vue Thymeleaf
│       └── application.properties           # Configuration (H2, JPA)
└── test/
```

Chaque couche ne communique qu'avec la couche immédiatement inférieure : `controller → service → repository → base de données`. Cette séparation permet de brancher deux contrôleurs différents (`ProduitController` pour les vues, `ProduitRestController` pour l'API) sur le **même** service, sans dupliquer la logique métier.

## Lancer le projet

```bash
mvn spring-boot:run
```

L'application démarre sur `http://localhost:8080`.

## Interface web (MVC)

Ouvrir dans un navigateur :

```
http://localhost:8080/produits
```

Formulaire d'ajout et tableau listant les produits en base.

## API REST

Base URL : `http://localhost:8080/api/produits`

| Méthode | Endpoint | Description |
|---|---|---|
| GET | `/api/produits` | Récupérer tous les produits |
| GET | `/api/produits/{id}` | Récupérer un produit par son id |
| POST | `/api/produits` | Ajouter un nouveau produit |
| PUT | `/api/produits/{id}` | Mettre à jour un produit existant |
| DELETE | `/api/produits/{id}` | Supprimer un produit |

### Exemples de test avec curl

```bash
# Récupérer tous les produits
curl http://localhost:8080/api/produits

# Récupérer un produit par son ID
curl http://localhost:8080/api/produits/1

# Ajouter un nouveau produit
curl -X POST http://localhost:8080/api/produits \
  -H "Content-Type: application/json" \
  -d '{"libelle": "Produit Test", "prix": 100.0, "qteStock": 10}'

# Mettre à jour un produit
curl -X PUT http://localhost:8080/api/produits/1 \
  -H "Content-Type: application/json" \
  -d '{"libelle": "Produit Modifie", "prix": 150.0, "qteStock": 5}'

# Supprimer un produit
curl -X DELETE http://localhost:8080/api/produits/1
```

> Sous Windows PowerShell, `curl` est un alias de `Invoke-WebRequest` et gère mal l'échappement des guillemets JSON. Préférer `curl.exe` pour un GET simple, ou `Invoke-RestMethod` pour POST/PUT/DELETE :
> ```powershell
> Invoke-RestMethod -Uri http://localhost:8080/api/produits -Method Post -ContentType "application/json" -Body '{"libelle": "Produit Test", "prix": 100.0, "qteStock": 10}'
> ```

## Console H2

Accessible sur `http://localhost:8080/h2-console` :

- JDBC URL : `jdbc:h2:mem:testdb`
- User : `sa`
- Password : *(vide)*

⚠️ La base est en mémoire : son contenu est réinitialisé à chaque redémarrage de l'application.

## Auteur

Anis Elloumi – ISET Sfax