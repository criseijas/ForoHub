
# 🧠 Foro Hub - API REST

**Foro Hub** es una API RESTful para un foro de discusión, desarrollada con Java, Spring Boot y seguridad con JWT. Permite gestionar tópicos, autenticar usuarios y proteger endpoints de forma robusta.

---

## 📌 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Flyway (Migraciones)
- MySQL
- Lombok
- Maven

---

## 🚀 Funcionalidades principales

- ✅ Registro de nuevos tópicos
- ✅ Listado paginado de tópicos activos
- ✅ Visualización de tópico por ID
- ✅ Actualización de tópico
- ✅ Eliminación lógica (`activo = false`)
- ✅ Autenticación de usuarios con Spring Security
- ✅ Generación de token JWT
- ✅ Protección de endpoints mediante JWT

---

## 🔐 Seguridad

El sistema utiliza **Spring Security** + **JWT**:

- Los usuarios se autentican mediante `/login`, enviando su login y contraseña.
- Si son válidos, se les devuelve un JWT.
- Este token debe enviarse en las siguientes peticiones en el header:

```
Authorization: Bearer <token>
```

---

## 🧪 Endpoints

| Método | URL                  | Descripción                          | Autenticación |
|--------|----------------------|--------------------------------------|----------------|
| POST   | `/login`             | Iniciar sesión (devuelve JWT)        | ❌             |
| POST   | `/topicos`           | Crear nuevo tópico                   | ✅             |
| GET    | `/topicos`           | Listar tópicos activos (paginado)    | ✅             |
| GET    | `/topicos/{id}`      | Obtener tópico por ID                | ✅             |
| PUT    | `/topicos/{id}`      | Actualizar un tópico                 | ✅             |
| DELETE | `/topicos/{id}`      | Eliminar tópico (lógicamente)        | ✅             |

---

## 📄 Estructura del proyecto

```
src/main/java
└── com/aluracursos/foro_hub/
    ├── controller/
    │   └── TopicoController.java
    │   └── AutenticacionController.java
    ├── domain/
    │   └── topico/
    │       ├── Topico.java
    │       ├── StatusTopico.java
    │       ├── DTOs...
    │       └── usuario/
    │           ├── Usuario.java
    │           ├── UsuarioRepository.java
    │           ├── AutenticacionService.java
    ├── infra/
    │   └── security/
    │       ├── SecurityConfigurations.java
    │       ├── TokenService.java
    │       └── FiltroDeSeguridad.java
```

---

## 🧰 Requisitos

- Java 17+
- MySQL
- Maven

---

## 🔧 Configuración local

1. Cloná el proyecto:

```bash
git clone https://github.com/criseijas/foro-hub.git
cd foro-hub
```

2. Configurá tu base de datos MySQL y archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=root
spring.datasource.password=tu_clave
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true
api.security.secret=mi-clave-secreta-supersegura
```

3. Ejecutá el proyecto:

```bash
./mvnw spring-boot:run
```

---

## 🗃️ Migraciones con Flyway

Las migraciones SQL están en:

```
src/main/resources/db/migration/
```

Ejemplo:

```sql
-- V1__create-table-topicos.sql
create table topicos (...);

-- V2__alter-table-topicos-add-column-activo.sql
alter table topicos add column activo tinyint;
update topicos set activo = 1;
```

---

## 📬 Ejemplo de login (Insomnia/Postman)

**POST** `/login`  
Body (JSON):

```json
{
  "login": "cristina.se@gmail.com",
  "contrasena": "123456"
}
```

Devuelve:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 🧑‍💻 Autora

> 💡 Proyecto desarrollado por **Cristina Seijas** como parte del curso de back-end con Java y Spring en [Alura Latam](https://www.aluracursos.com/).

📎 GitHub: [@criseijas](https://github.com/criseijas)
