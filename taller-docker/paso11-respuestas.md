Estudiantes: José Manuel Pérez Rodríguez - 1152375
Juan Diego Sarmiento Barroyeta - 1152376

1. ¿Por qué Pedidos no puede usar localhost:8081 dentro de su contenedor? ¿Qué lo reemplazó?
Dentro de un contenedor, localhost apunta al propio contenedor, no puede apuntar a otro puesto que localhost es propio de cada contenedor. Lo reemplazó la red de Docker Compose usando el nombre del servicio (http://catalogo:8081) por medio de la variable de entorno CATALOGO_URL


2. ¿Qué ventaja tiene haber puesto la dirección de Catálogo en application.properties y no escrita en el código Java?
Permite cambiar la dirección según el entorno usando variables de entorno o archivos de configuración sin tener que modificar y recompilar el código


3. ¿Por qué el Dockerfile tiene dos etapas? ¿Qué pasaría con el tamaño de la imagen si fuera una sola?
La primera etapa compila el proyecto con Maven y la segunda copia solo el .jar a una imagen base solo con Java. Si fuera una sola etapa, la imagen final sería mucho más pesada porque conservaría Maven, el JDK completo, el código fuente y dumps de compilación innecesarios para ejecutar la app


4. ¿Qué pasó con el producto que crearon después de reiniciar Catálogo? ¿Por qué? ¿Qué se necesitaría para que no se pierda?
Se pierde al reiniciar porque los datos están en una base de datos en memoria o no persistente dentro del contenedor. Para que no se pierda se necesita una base de datos externa o separada (como PostgreSQL del reto que hicimos) conectada a un volumen de Docker que guarde los datos en disco


5. depends_on arranca Catálogo antes que Pedidos. ¿Garantiza eso que Catálogo ya esté listo para responder cuando Pedidos recibe su primer pedido?
No, depends_on solo espera a que el contenedor inicie, no a que la aplicación Spring Boot termine de cargar y esté lista para atender
