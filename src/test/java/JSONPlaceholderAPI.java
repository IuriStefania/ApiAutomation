import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;


public class JSONPlaceholderAPI {

    @BeforeClass
    public void setup() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    //solicitud GET al endpoint /posts
    public void getPost(){

        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(100))
            .body("[0].title", notNullValue())
            .body("[0].userId", equalTo(1));
    }

    @Test
    //solicitud POST al endpoint /posts
    public void createPost() {

        String titulo = "Modificacion del titulo";
        String cuerpo = "Modificacion del cuerpo";
        int userId = 2;

        // Crear un map con los datos que se enviarán en la solicitud POST
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", titulo);
        newPost.put("body", cuerpo);
        newPost.put("userId", userId);

        given()
            .header("Content-type", "application/json")
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo(titulo))
            .body("body", equalTo(cuerpo))
            .body("userId", equalTo(userId))
            .body("id", notNullValue());
    }

    @Test
    // Hacer la solicitud DELETE al endpoint /posts/1 para eliminar el post con ID 1
    public void deletePost() {

        int id = 1;
        given()
                .when()
                .delete("/posts/"+id)
                .then()
                .statusCode(200)
                .body("size()", is(0));
    }

    @Test
    //Hacer la solicitud PUT al endpoint /posts/1 para actualizar el post con ID 2
    public void updatePost() {
        String titulo = "Modificacion del titulo";
        String cuerpo = "Modificacion del cuerpo";
        int id = 2;
        int userId = 2;

        // Crear un map con los datos que se enviarán en la solicitud PUT (actualización del post)
        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("id", id);  // El ID del post a actualizar
        updatedPost.put("title",titulo);
        updatedPost.put("body", cuerpo);
        updatedPost.put("userId", userId);

        given()
            .header("Content-type", "application/json")
            .body(updatedPost)
        .when()
            .put("/posts/2")
        .then()
            .statusCode(200)
            .body("id", equalTo(id))
            .body("title", equalTo(titulo))
            .body("body", equalTo(cuerpo))
            .body("userId", equalTo(userId));
    }

}




