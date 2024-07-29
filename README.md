# Proyecto de Gestión de Usuarios en Java con SQLite

## Descripción

Este proyecto implementa un sistema CRUD (Crear, Leer, Actualizar y Eliminar) para la gestión de usuarios utilizando Java SE 21, el IDE Eclipse y SQLite. La solución está diseñada para permitir la administración eficiente de usuarios, agrupados por roles específicos, y se centra en proporcionar una interfaz gráfica intuitiva utilizando Swing.

## Tecnologías Utilizadas

- **Java SE 21**: Lenguaje de programación utilizado para el desarrollo de la aplicación.
- **Eclipse IDE**: Entorno de desarrollo integrado que facilita la codificación y depuración del proyecto.
- **SQLite**: Sistema de gestión de bases de datos ligero y sin servidor que almacena los datos de la aplicación.
- **Swing**: Biblioteca de Java para construir la interfaz gráfica de usuario.

## Estructura del Proyecto

### Paquetes

1. **`connection`**: Contiene la clase `SQLite.java`, que maneja la conexión con la base de datos SQLite (`database.db`) ubicada en el directorio raíz del proyecto.

2. **`model`**: Define los modelos de datos utilizados en la aplicación. Incluye la clase `User` que representa la estructura de un usuario.

3. **`dao`**: Implementa el patrón DAO (Data Access Object) para encapsular la lógica de acceso a la base de datos y realizar operaciones CRUD en la tabla de usuarios.

4. **`controller`**: Actúa como el intermediario entre la vista y el modelo, gestionando la lógica de negocio y las interacciones del usuario.

5. **`view`**: Contiene las clases que definen la interfaz gráfica de usuario utilizando Swing. Incluye formularios y componentes para interactuar con los datos.

## Funcionalidades

- **Crear**: Permite agregar nuevos usuarios a la base de datos.
- **Leer**: Muestra los usuarios existentes en la interfaz gráfica y permite realizar búsquedas filtradas.
- **Actualizar**: Permite modificar la información de los usuarios existentes.
- **Eliminar**: Permite eliminar usuarios de la base de datos.

## Interfaz de Usuario

La interfaz gráfica ha sido diseñada para ser intuitiva, con controles bien organizados y funcionalidad clara para el usuario final. Las opciones incluyen:

- **Botón de Buscar**: Filtra los usuarios según el cargo seleccionado.
- **Botón Restaurar**: Elimina cualquier filtro aplicado y muestra todos los usuarios.

## Instrucciones de Uso

1. **Configuración Inicial**:
   - Asegúrate de tener Java SE 21 y Eclipse instalados en tu sistema.
   - Importa el proyecto en Eclipse.

2. **Ejecución**:
   - Ejecuta el proyecto.
   - La aplicación se conectará automáticamente a la base de datos `database.db` y abrirá la interfaz gráfica.

3. **Operaciones**:
   - Utiliza los formularios proporcionados para crear, leer, actualizar y eliminar usuarios.
   - Utiliza los botones de búsqueda y restauración para gestionar la visualización de los datos.

## Notas

- El archivo `database.db` se encuentra en el directorio raíz del proyecto y contiene toda la información de los usuarios.
- La aplicación está diseñada para ser portable y ejecutarse en cualquier entorno sin necesidad de configuraciones adicionales de base de datos.

## Conclusión

El proyecto proporciona una solución completa para la gestión de usuarios, combinando una interfaz gráfica intuitiva con una arquitectura robusta basada en Java y SQLite. La implementación de los patrones de diseño MVC y DAO asegura una estructura limpia y mantenible.
