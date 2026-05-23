<div align="center">
  <h1>📚 EduLend</h1>
  <p><strong>Sistema moderno para gestionar préstamos e inventario de recursos educativos.</strong></p>

  [![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
  [![Java](https://img.shields.io/badge/Java-21-orange.svg?logo=java)](https://www.java.com/)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg?logo=postgresql)](https://www.postgresql.org/)
</div>

---

## 🌟 ¿Qué hace esta aplicación?

En los entornos académicos y escolares es sumamente común prestar recursos valiosos (como computadores, proyectores o libros). Sin embargo, llevar el rastro de dónde está cada recurso o cuántas unidades hay disponibles puede volverse caótico sin una herramienta centralizada.

**EduLend** soluciona esto proveyendo un Backend robusto (API REST) que permite:
- **Administrar un inventario** detallado, con control automatizado de cuántas unidades están disponibles, prestadas o dañadas.
- **Gestionar usuarios**, perfiles y actualización segura de contraseñas.
- **Registrar y supervisar préstamos**, asignando recursos a los usuarios, indicando fechas de entrega y garantizando que el stock se debite o devuelva en tiempo real de acuerdo con el estado del préstamo.

Todo esto está listo para ser integrado y consumido por aplicaciones web o aplicaciones móviles.

---

## 🚀 Instalación

Para correr este proyecto en tu entorno local, asegúrate de tener instalados:
- **Java 21** o superior.
- **Maven**.
- Una base de datos **PostgreSQL**.

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/wigek/inventario-edulend-grupo1.git
cd inventario-edulend-grupo1
```

### 2️⃣ Configurar Variables de Entorno
Crea un archivo llamado `.env` en la raíz del proyecto con la siguiente estructura para conectar tu base de datos:

```env
DB_URL=jdbc:postgresql://<TU_HOST>:<TU_PUERTO>/<TU_DATABASE>?sslmode=require
DB_USERNAME=<TU_USUARIO>
DB_PASSWORD=<TU_PASSWORD>
```

### 3️⃣ Construir y Ejecutar
Compila las dependencias y levanta el servidor integrado de Spring Boot:

```bash
mvn clean compile
mvn spring-boot:run
```
La aplicación se iniciará en `http://localhost:8080`.

---

## 📖 Instructivo de Uso

Una vez que la aplicación esté corriendo, tienes múltiples formas de interactuar con ella y gestionar los datos.

### Opción A: A través de Swagger UI (Recomendado)
EduLend cuenta con documentación viva generada mediante OpenAPI. Abre tu navegador y visita:
> **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Desde esta interfaz podrás ver todos los *Endpoints* disponibles, entender su estructura (JSON requeridos en base a nuestros `DTOs`) y ejecutar peticiones directamente desde la web para probar el comportamiento de la aplicación sin usar herramientas externas.

### Opción B: Uso a través de los Endpoints
El sistema divide su lógica central en los siguientes módulos para que puedas integrarlo en un Frontend usando Postman, cURL o librerías HTTP (como Axios o Fetch):

- **`/api/login`**: Valida las credenciales de un usuario y devuelve su información sin comprometer datos sensibles.
- **`/api/users`**: Gestión de usuarios. Incluye registro, consulta y un sub-endpoint protegido mediante `PUT /api/users/{id}/password` para cambiar contraseñas requiriendo validación.
- **`/api/categories`**: Permite organizar los recursos en familias (ej: Electrónicos, Bibliográficos). Se requiere para crear artículos.
- **`/api/articles`**: Creación del equipamiento escolar. Al registrar un artículo defines su cantidad total y el sistema inferirá su disponibilidad inicial.
- **`/api/loans`**: Gestión de préstamos. Envías el ID del usuario y el ID del artículo. El sistema validará el stock y restará automáticamente 1 unidad de la disponibilidad. Si eliminas el préstamo o lo marcas como inactivo, la unidad será devuelta al inventario global automáticamente.

---

## 🏗️ Tecnologías y Arquitectura

El backend fue refactorizado y estructurado con un enfoque de **Arquitectura Hexagonal**, separando estrictamente la lógica central, los servicios y los controladores HTTP. Hemos reemplazado el uso crudo de entidades por un fuerte manejo de **DTOs** (Data Transfer Objects) usando `records` inmutables de Java.

| Capa | Responsabilidad |
|---|---|
| **Models** | Representación de entidades JPA mapeadas hacia PostgreSQL. |
| **DTOs** | Objetos de transferencia que ocultan o anidan información, purificando los Request y Response de los endpoints. |
| **Services** | Reglas de negocio duras (conversiones, validación de contraseñas, deducción matemática de inventario). |
| **Controllers** | API REST y enrutamiento HTTP (sólo interactúan mediante DTOs). |
| **Datasource** | Capa de persistencia soportada por `Spring Data JPA` (interfaces Repository). |

---

## 👥 Equipo de Desarrollo

| Nombre | Rol Principal | GitHub |
|--------|--------------|--------|
| **Wilder Garcia** | Backend / Arquitectura | [@Wigek](https://github.com/Wigek) |
| **Jean Medina** | Backend / Arquitectura | [@Jcmedinah](https://github.com/Jcmedinah) |
| **Alejandra Alvarez**| Frontend | |
| **Carlos Andres Perez**| Base de Datos / Backend | [@Andres0818](https://github.com/Andres0818) |

<br>
<div align="center">
  <i>Desarrollado como proyecto final.</i>
</div>
