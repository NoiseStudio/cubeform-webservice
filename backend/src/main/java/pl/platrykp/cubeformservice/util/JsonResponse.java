package pl.platrykp.cubeformservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pl.platrykp.cubeformservice.responses.MessageResponse;

import java.util.Collection;

public class JsonResponse {

    private JsonResponse() { }

    /** 200 OK */
    public static ResponseEntity<?> ok(String message) {
        return newResponse(200, message);
    }

    /** 201 Created */
    public static ResponseEntity<?> created(String message) {
        return newResponse(201, message);
    }

    /** 202 Accepted */
    public static ResponseEntity<?> accepted(String message) {
        return newResponse(202, message);
    }

    /** 204 No Content */
    public static ResponseEntity<?> noContent(String message) {
        return newResponse(204, message);
    }

    /** 300 Multiple Choice */
    public static ResponseEntity<?> multipleChoice(String message) {
        return newResponse(300, message);
    }

    /** 302 Found */
    public static ResponseEntity<?> found(String message) {
        return newResponse(302, message);
    }

    /** 400 Bad Request */
    public static ResponseEntity<?> badRequest(String message) {
        return newResponse(400, message);
    }

    /** 401 Unauthorized */
    public static ResponseEntity<?> unauthorized(String message) {
        return newResponse(401, message);
    }

    /** 403 Forbidden */
    public static ResponseEntity<?> forbidden(String message) {
        return newResponse(403, message);
    }

    /** 404 Not Found */
    public static ResponseEntity<?> notFound(String message) {
        return newResponse(404, message);
    }

    /** 406 Not Acceptable */
    public static ResponseEntity<?> notAcceptable(String message) {
        return newResponse(406, message);
    }

    /** 409 Conflict */
    public static ResponseEntity<?> conflict(String message) {
        return newResponse(409, message);
    }

    /** 500 Internal Server Error */
    public static ResponseEntity<?> internalServerError(String message) {
        return newResponse(500, message);
    }

    /** 418 Im A Teapot */
    public static ResponseEntity<?> imATeapot() {
        return imATeapot("I'm a teapot");
    }
    /** 418 Im A Teapot */
    public static ResponseEntity<?> imATeapot(String message) {
        return newResponse(418, message);
    }


    public static ResponseEntity<?> newResponse(HttpStatus code, String message) {
        return newResponse(code.value(), message);
    }
    public static ResponseEntity<?> newResponse(int code, String message){
        return ResponseEntity
                .status(code)
                .contentType(MediaType.APPLICATION_JSON)
                .body(getFullMessageJSon(message));
    }

    /** Returned JSON contains single field 'list' */
    public static <T> Wrapper<T> listObject(Collection<T> collection){
        return  new Wrapper<T>(collection);
    }

    private static String getFullMessageJSon(String message){
        ObjectMapper mapper = new ObjectMapper();
        MessageResponse response = new MessageResponse(message);
        try {
            return mapper.writeValueAsString(response);
        }
        catch (JsonProcessingException er){
            er.printStackTrace();
            return "{\"message\":\"null\"}";
        }
    }

    public static class Wrapper<T> {
        public Collection<T> list;
        public Wrapper(Collection<T> collection){
            this.list = collection;
        }
    }

}
