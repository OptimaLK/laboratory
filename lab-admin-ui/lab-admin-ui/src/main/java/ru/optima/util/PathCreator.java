package ru.optima.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PathCreator {

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
        StringBuilder path = new StringBuilder();
        Collection<? extends GrantedAuthority> authorities = auth.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
//          костыль
            if (authorities.size() > 1) {
                path.append("chief");
                break;
            }

            switch (authority.getAuthority()) {
                case "ROLE_ADMIN": {
                    path.append("admin");
                    break;
                }
                case "ROLE_CHIEF":{
                    path.append("chief");
                    break;
                }
                case "ROLE_DIRECTOR": {
                    path.append("director");
                    break;
                }
                case "ROLE_EXECUTOR": {
                    path.append("executor");
                    break;
                }
                case "ROLE_SECRETARY": {
                    path.append("secretary");
                    break;
                }
            }
        }
        return path.toString();
    }
}