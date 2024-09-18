A continuación se detalla lo realizado en cada una de las clases del proyecto "API_Automation"

Dentro del path src/test/java se encuentran las clases:
- JSONPlaceholderAPI
- TestAPI

Ambas tienen en comun que ejecutan el metodo setUp, en donde configura la URI base para todas las solicitudes de prueba. Se utiliza la anotacion @BeforeClass de TestNG.

- JSONPlaceholderAPI.java se implementan distintos metedos que permite probar diferentes peticiones utilizando una API publica
* getPost(): muestra cómo hacer una solicitud simple a la API para obtener post, validar el código de estado y verifica:
que el código de estado es 200 (OK), que se reciben 100 publicaciones (por default), que en el primer post el titulo no es nulo

* createPost(): en primer lugar genera un map con los datos que se enviarán en la solicitud POST.
Luego, muestra cómo realizar una solicitud POST a la API lo cual crea un nuevo post, enviando datos en formato JSON y validando la respuesta.
Basicamente especifica a traves del header() que el cuerpo sera un JSON y luego en body() envia el mapa con los datos.
Finalmente verifica: que termino en estado 201 (CREATED), que el titulo y el body coincide con lo que se envio y que se ha generado un nuevo post
NOTA: al tratarse de una api publica de prueba no guarda los datos permanentemente, pero simula correctamente la respuesta para fines de prueba.

* deletePost(): elimina el post correspondiente al id que se indica. Finalmente verifica el codigo 200 lo cual indica operacion exitosa y que el cuerpo de la respuesta este vacio.
NOTA: simula correctamente la operación de eliminación, la API devolverá una respuesta con un cuerpo vacío y el código de estado 200 (OK).
No elimina de manera permanente, debido a que es una API publica de prueba.

* updatePost(): lo que hace es atraves de un PUT actualizar un post existente.
Lo que hace es en el header especificar que el cuerpo es un JSON y luego a traves del body se pasan los datos actualizados.
Verifica que se realizo correctamente (200), y tambien que los datos actualizados coincidan con los que se ingresaron.
NOTA: simula correctamente la actualización, los datos no se guardan permanentemente ya que es una API de prueba.


- TestAPI.java:
* getUsers(): envia una solicitud GET a la url y obtiene el listado de usuarios, luego valida el codigo de respuesta y un valor especifico.

* postUsers(): se crea un map para almacenar los datos que luego se enviaran en la solicitud POST.
Se configura la solicitud para que se registren todos los detalles y lego verifica que el codigo de status sea el correcto.

* deletUser(): elimina un usuario. En esta oportunidad se valida la operacion con el codigo 204 lo cual indica que la solicitud fue procesada correctamente, 
pero no hay datos adicionales que devolver.
NOTA: la elección entre 200 y 204 depende de si el servidor necesita devolver información adicional al cliente después de una operación DELETE.

* putUser(): se modifica un usuario con los datos proporcionados. 

A modo de prueba se utiliza log().all() para obtener una vision detallada de lo que ocurre durante la ejecucion de las pruebas.