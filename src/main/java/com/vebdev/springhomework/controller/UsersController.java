package com.vebdev.springhomework.controller;

import com.vebdev.springhomework.domain.Role;
import com.vebdev.springhomework.domain.User;
import com.vebdev.springhomework.servise.jpa.RoleService;
import com.vebdev.springhomework.servise.jpa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController extends BaseSecurityController{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user/users")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("/user/users");

        modelAndView.addObject("users", userService.getAllUsers());

        return modelAndView;
    }

    @GetMapping("/user/delete")
    public String deleteManufactorerUser(@RequestParam long userId) {
        if (userService.exists(userId)) {
            User user = userService.getById(userId);
            userService.delete(user);
        }
        return "redirect:/user/users";
    }

    @GetMapping("/user/add")
    public ModelAndView createUser(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("/user/create");
        modelAndView.addObject("allRoles", roleService.getAllRoles());
        return modelAndView;
    }

    @PostMapping("/user/add")
    public String createUser(@RequestParam(value = "email", required = true) String email,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "firstName", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName,
                             @RequestParam(value = "roles") String[] roles) {
        User user = new User();

        System.out.println(email);
        System.out.println(password);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(roles.toString());

            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setActive(1);
            userService.setPassword(user, password);
            for(String roleName: roles) {
                Role role = roleService.getRoleByName(roleName);
                user.getRoles().add(role);
            }

            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getRoles().toArray());

            userService.addNewUser(user);


        return "redirect:/user/users";
    }

    @GetMapping("/user/edit")
    public ModelAndView editUser(@RequestParam long userId) {
        ModelAndView result = modelAndViewSecurityBase("user/edit");

        User user = userService.getById(userId);
        result.addObject("editUser", user);
        result.addObject("allRoles", roleService.getAllRoles());
        return result;
    }

    @PostMapping("/user/edit")
    public String editUser(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "roles") String[] roles) {

        User user = userService.getByEmail(email);

        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userService.setPassword(user, password);
            user.getRoles().clear();
            for(String roleName: roles) {
                Role role = roleService.getRoleByName(roleName);
                user.getRoles().add(role);
            }

            userService.update(user);
        }

        return "redirect:/user/users";
    }

}
