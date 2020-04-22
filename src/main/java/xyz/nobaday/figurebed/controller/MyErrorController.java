package xyz.nobaday.figurebed.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MyErrorController implements ErrorController {

    private String errorPath;

    @RequestMapping("/error")
    public void errorHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        errorPath = request.getContextPath();
        response.sendRedirect(request.getContextPath() + "/index");
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
