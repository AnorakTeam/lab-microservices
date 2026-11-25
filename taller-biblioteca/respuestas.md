Respuestas:

1. **¿Por qué Préstamos tiene su propio `record` del libro?**
   Porque cada microservicio debe depender del contrato de la API, no de las clases internas de otro servicio. Préstamos define solo los datos que necesita, así Libros puede cambiar su entidad sin obligar a Préstamos a cambiar también.

2. **¿Por qué guarda una copia del título?**
   Para conservar qué título tenía el libro cuando se hizo el préstamo. Si Libros cambia el título o elimina el libro, el historial del préstamo sigue siendo comprensible.

3. **¿Por qué un libro inexistente responde 400 y no 404?**
   Porque sí existe el endpoint /prestamos, pero la solicitud contiene un libroId inválido. El 404 de Libros se traduce a 400 para indicar que la petición del cliente no se puede aceptar.

4. **¿Qué cambia en Docker y por qué no hubo que tocar Java?**
   En Docker, Libros se encuentra por el nombre del servicio, http://libros:8091, en vez de localhost. La dirección se configura mediante LIBROS_URL como variable de entorno en compose.yaml, para que Java lea libros.url, y así el cliente no necesite cambios.

5. **¿Quién debería descontar ejemplares y qué pasa si falla el guardado?**
   Libros debería descontarlos, porque es dueño de ese inventario. Como cada servicio tiene su propia base de datos, no hay una transacción única que cubra ambos. Si Libros descuenta y luego Préstamos falla al guardar, el ejemplar queda descontado sin préstamo registrado.
