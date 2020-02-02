package com.java.config.filter;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class JsonToUrlEncodedAuthorizationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        
        if (Objects.equals(httpServletRequest.getServletPath(), "/oauth/token") && Objects.equals(httpServletRequest.getContentType(), "application/json")) {
            
            byte[] json = ByteStreams.toByteArray(httpServletRequest.getInputStream());
            
            Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);
            ;
            Map<String, String[]> parameters =
                    jsonMap.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> new String[]{e.getValue()})
                            );
            HttpServletRequest requestWrapper = new RequestWrapper(httpServletRequest, parameters);
            filterChain.doFilter(requestWrapper, httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
    
    private class RequestWrapper extends HttpServletRequestWrapper {
        
        private final Map<String, String[]> params;
        
        RequestWrapper (HttpServletRequest request, Map<String, String[]> params) {
            
            super(request);
            this.params = params;
        }
        
        @Override
        public String getParameter (String name) {
            
            if (this.params.containsKey(name)) {
                return this.params.get(name)[0];
            }
            return "";
        }
        
        @Override
        public Map<String, String[]> getParameterMap () {
            
            return this.params;
        }
        
        @Override
        public Enumeration<String> getParameterNames () {
            
            return new Enumerator<>(params.keySet());
        }
        
        @Override
        public String[] getParameterValues (String name) {
            
            return params.get(name);
        }
    }
}
