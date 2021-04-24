package pl.platrykp.cubeformservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.platrykp.cubeformservice.responseentities.HttpCodeResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestErrorController implements ErrorController {


    @RequestMapping("/error")
    public HttpCodeResponse handleError(HttpServletRequest request, HttpServletResponse response){
        int errorCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); //"javax.servlet.error.status_code"
        response.setStatus(errorCode);
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String message = throwable != null ? throwable.getMessage() : HttpStatus.resolve(errorCode).toString();
        String path = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        String method = request.getMethod();
        return new HttpCodeResponse(errorCode, message, method, path);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
