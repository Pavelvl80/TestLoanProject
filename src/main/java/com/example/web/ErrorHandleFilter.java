package com.example.web;

import com.example.service.FilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Component
public class ErrorHandleFilter implements Filter {

    private final FilterService filterService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ErrorHandleFilter(FilterService filterService) {
        this.filterService = filterService;
    }


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Locale locale = request.getLocale();
        if (!filterService.filer(locale != null ? locale.getCountry() : "lv")) {
            chain.doFilter(request, response);
        } else {
            response.getOutputStream().write(
                    mapper.writeValueAsBytes(new Error("Exceed execute from this country"))
            );
        }
    }
}
