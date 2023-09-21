/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.controllers;

import com.dvtd.commons.Breadcrumbs;
import com.dvtd.pojo.Promotions;
import com.dvtd.pojo.Users;
import com.dvtd.service.PromotionsService;
import com.dvtd.service.UsersService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ACER
 */
@Controller
@PropertySource("classpath:configs.properties")
public class UserController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private Environment env;

    @GetMapping("/users")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.usersService.getUsers(params));
        String sortOption = params.get("sort");
        model.addAttribute("sortOption", sortOption);

        List<Breadcrumbs> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumbs("Người dùng", ""));
        model.addAttribute("breadcrumbs", breadcrumbs);

        String currentPage = params.get("page");
        if (currentPage != null && !currentPage.isEmpty()) {
            model.addAttribute("currentPage", currentPage);
        } else {
            model.addAttribute("currentPage", 1);
        }

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.usersService.countUsers();
        model.addAttribute("countPromotions", Math.ceil(count * 1.0 / pageSize));
        return "users.index";
    }

    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("user", new Users());
        return "users.create";
    }

    @PostMapping("/users")
    public String store(@ModelAttribute("user") @Valid Users u, BindingResult rs, Model model, RedirectAttributes ra) {
        if (!rs.hasErrors()) {
            if (this.usersService.existUsername(u.getUsername()) != true) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                String formattedDate = sdf.format(now); //
                u.setCreatedDate(now);

                Map<String, String> user = new HashMap<>();

                user.put("username", u.getUsername());
                user.put("password", u.getPassword());
                user.put("phone", u.getPhone());
                user.put("email", u.getEmail());
                user.put("userRole", u.getRole());
                user.put("active", String.valueOf(u.getActive()));

                if (this.usersService.addUser(user, u.getFile()) == true) {
                    ra.addFlashAttribute("messageSuccess", "Thêm người dùng thành công");
                    return "redirect:/users";
                } else {
                    model.addAttribute("messageFalse", "Có lỗi xảy ra.");
                }
            } else {
                rs.rejectValue("username", "", "Tên đăng nhập đã tồn tại");
            }
        }

        return "users.create";
    }

    @GetMapping("/users/{id}")
    public String show(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", this.usersService.getUserDetail(id));
        return "users.show";
    }

    @PostMapping("/users/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") @Valid Users u, BindingResult rs, Model model, RedirectAttributes ra) {
        if (!rs.hasErrors()) {
            if (this.usersService.updateUser(u) == true) {
                ra.addFlashAttribute("messageSuccess", "Cập nhật người dùng thành công");
                return "redirect:/users";
            } else {
                model.addAttribute("messageFalse", "Có lỗi xảy ra.");
                
            }
        }else model.addAttribute("errorvl", rs.getAllErrors());

        return "users.show";
    }

}
