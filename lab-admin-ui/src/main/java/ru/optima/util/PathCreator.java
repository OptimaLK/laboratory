package ru.optima.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PathCreator {

    public String getUserLogin(SecurityContextHolder auth) {
        Authentication authentication = auth.getContext().getAuthentication();
        return authentication.getName();
    }

    public String getRole (SecurityContextHolder auth) {
        return takePartPathString(auth);
    }

//    возвращает redirect: index - шаблон, в зависимости от роли
//    метод возвращает строку "[redirect:] + [chief]" если роль chief
    public String createPath (SecurityContextHolder auth){
        StringBuilder path = new StringBuilder();
        path.append("redirect:").append(takePartPathString(auth));
        return path.toString();
    }
//    вовращает путь к шаблону, в аргументы имя шаблона
//    добавляет первую часть пути до папки с шаблонами в зависимости от роли,
//    метод с аргументом partOfPath возвращает строку "[chief] + [/] + [partOfPath]" если роль chief
    public String createPath (SecurityContextHolder auth, String partOfPath){
        StringBuilder path = new StringBuilder();
        path.append(takePartPathString(auth)).append("/").append(partOfPath);
        return path.toString();
    }

    private String takePartPathString (SecurityContextHolder auth){
        Collection<? extends GrantedAuthority> authorities = auth.getContext().getAuthentication().getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        if (roles.contains("ROLE_CHIEF")) {
            return ("chief");
        }
        if (roles.contains("ROLE_DIRECTOR")) {
            return ("director");
        }
        if (roles.contains("ROLE_SECRETARY")) {
            return ("secretary");
        }
        if (roles.contains("ROLE_EXECUTOR")) {
            return ("executor");
        }
        return ("roles not found");
    }
}