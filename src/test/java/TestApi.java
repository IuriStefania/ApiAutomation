import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;

public class TestApi {

    @BeforeClass
    public static void setup() {
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void getUsers(){

        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("data[2].last_name", equalTo("Wong"));

    }

    @Test
    public void postUser(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Stefania");
        map.put("job", "Systems Engineer");

        given()
            .log().all()
            .body(map.toString())
        .when()
            .post("/users")
        .then()
            .log().all()
            .statusCode(201);
    }

    @Test
    public void putUser(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Stefania");
        map.put("job", "Systems Engineer");

        given()
            .body(map.toString())
        .when()
            .put("/users/2")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void deleteUser(){

        when()
            .delete("/users/2")
        .then()
            .log().all()
            .statusCode(204);
    }

}
