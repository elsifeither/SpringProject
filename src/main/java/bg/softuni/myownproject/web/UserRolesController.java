package bg.softuni.myownproject.web;


import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserRolesController {

    private final UserService userService;

    public UserRolesController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/roles")
    public String getAllUsers(Model model) {
        List<UserEntity> users = userService.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRole().equals(UserRoleEnum.USER) || role.getRole().equals(UserRoleEnum.MODERATOR)))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "user-roles-list";
    }

    @PostMapping("/roles/{userId}/changeRole")
    public String changeUserRole(@PathVariable Long userId, @RequestParam String role) {
        userService.changeUserRole(userId, UserRoleEnum.valueOf(role.toUpperCase()));
        return "redirect:/roles";
    }
}