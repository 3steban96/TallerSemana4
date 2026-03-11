# Test Cases

| HU Original| HU refinada por la instrucción | Diferencias detectadas |
|----|-------------|-------------------|
| Como sistema,quiero restringir el acceso a las páginas del dashboard y reservas solo a usuarios autenticados, para garantizar la seguridad de la información. | Como [usuario/administrador], quiero que solo los usuarios autenticados puedan acceder al dashboard y a las páginas de reservas, para proteger la información sensible de la plataforma. | La instruccion añadio el rol de usuario/administrador para ser mas descriptivo  |
| Como usuario autenticado,quiero ver la lista de espacios disponibles,para elegir el más adecuado para mi reserva. | Verificación de registro con correo ya existente | El sistema muestra un mensaje de error indicando que el correo ya está en uso. |
| TC3 | Verificación de búsqueda de productos por nombre | El sistema muestra una lista de productos que coinciden con el criterio de búsqueda. |
