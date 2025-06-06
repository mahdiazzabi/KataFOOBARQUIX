# 🧪 Kata FOOBARQUIX

Écrire un algorithme qui transforme un nombre (compris entre 0 et 100) en une chaîne de caractères selon les règles **Foo**, **Bar**, **Quix** :

- Si le nombre est divisible par 3 → `Foo`
- Si le nombre est divisible par 5 → `Bar`
- Si le nombre est divisible par 7 → `Quix`
- Pour chaque chiffre contenu dans le nombre :
  - 3 → `Foo`
  - 5 → `Bar`
  - 7 → `Quix`

**Exemple** : `15` est divisible par 3 et 5 → `FooBar`, contient les chiffres 1 et 5 → `FooBarBar`  
Résultat final : `"15" -> FooBarBar`

---

# ⚙️ Projet Spring Boot : REST API + Batch

Ce projet fournit :
- Une **API REST** pour tester les transformations en direct
- Un **traitement batch** qui lit un fichier d'entrée et génère un fichier de sortie
- Un **Docker Compose** pour simplifier l'exécution locale

---

## 🚀 Lancement avec Docker

```bash
docker compose up --build
```

Cela démarre l'application à l'adresse :
```
http://localhost:8080
```

---

## 🔁 API REST - Tester une transformation

### 🛠️ Endpoint
```
GET /api/transform/{number}
```

### ✅ Exemple d’appel :

```bash
curl -X GET http://localhost:8080/api/transform/15
```

### 📤 Réponse attendue :

```
FooBarBar
```

> ⚠️ Le nombre doit être compris entre 0 et 100  
> Sinon : réponse `400 Bad Request`

---

## 📦 Traitement Batch - Fichier vers fichier

### ▶️ Lancer le traitement batch

```bash
curl -X POST http://localhost:8080/batch/run
```

### 📚 Fonctionnement

- 📥 Lit : `input/input.txt`
- 🔄 Transforme chaque ligne avec les règles FooBarQuix
- 📤 Écrit dans : `output/output.txt`

---

## 📁 Arborescence attendue

```
.
├── input/
│   └── input.txt        # Fichier d'entrée à créer manuellement
├── output/
│   └── output.txt       # Fichier généré automatiquement
```

---

## 📝 Exemple

### Contenu du fichier `input/input.txt`

```
1
3
5
15
13
```

### Résultat dans `output/output.txt`

```
1 "1"
3 "FooFoo"
5 "BarBar"
15 "FooBarBar"
13 "FooFoo"
```

---

## 🧼 Rappels importants

- Le fichier `output.txt` est **réinitialisé** à chaque exécution du batch
- Le fichier `input.txt` est **relu entièrement** à chaque lancement

---

## 🛠️ Stack technique

- Java 17
- Spring Boot
- Spring Batch
- Docker / Docker Compose
