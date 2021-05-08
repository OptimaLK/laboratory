package ru.optima.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.optima.repr.UserRepr;
import ru.optima.persist.model.User;
import ru.optima.persist.repo.RoleRepository;
import ru.optima.service.UserService;
import ru.optima.service.UserServiceImpl;
import ru.optima.util.PathCreator;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final PathCreator pathCreator;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping({"/", ""})
    public String usersPage(Model model, SecurityContextHolder auth) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "users");
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userServiceImpl.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "user_form");
    }

    @GetMapping("/create")
    public String createUser(Model model, SecurityContextHolder auth) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserRepr());
        model.addAttribute("roles", roleRepository.findAll());
        return pathCreator.createPath(auth, "user_form");
    }

    @PostMapping("/create")
    public String createUser(SecurityContextHolder auth, @ModelAttribute("user") @Validated UserRepr user, BindingResult bindingResult, Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("create", true);
        model.addAttribute("roles", roleRepository.findAll());

        if (bindingResult.hasErrors()) {
            return pathCreator.createPath(auth, "user_form");
        }
        Optional<User> existing = userService.findByOName(user.getLastName());
        if (existing.isPresent()){
            model.addAttribute("user", user);
            model.addAttribute("registrationError", "Пользователь с такой фамилией уже существует");
            return pathCreator.createPath(auth, "user_form");
        }
        userService.save(user);
        return pathCreator.createPath(auth) + "chief/users";
    }

//    @PostMapping("/admin/user/edit")
//    public String adminUpdateUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model) {
//        model.addAttribute("activePage", "Users");
//        model.addAttribute("edit", true);
//
//        if (bindingResult.hasErrors()) {
//            return "user_form";
//        }
//        Optional<User> existing = userService.findByOName(user.getLastName());
//        if (existing.isPresent()){
//            model.addAttribute("user", user);
//            model.addAttribute("registrationError", "Пользователь с такой фамилией уже существует");
//            return "user_form";
//        }
//
//        userService.edit(user);
//        return "redirect:/admin/users";
//    }

    @DeleteMapping("/{id}/delete")
    public String adminDeleteUser(Model model, SecurityContextHolder auth, @PathVariable("id") Long id) {
        userService.delete(id);
        return pathCreator.createPath(auth) + "/users";
    }

//
//    @GetMapping("/roles")
//    public String adminRolesPage(Model model) {
//        model.addAttribute("activePage", "Roles");
//        return "index";
//    }
}
