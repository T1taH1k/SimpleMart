package com.stepanenko.web.controller;

import com.stepanenko.DAO.UserDAO;
import com.stepanenko.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This is default page!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(Map<String, Object> map) throws SQLException {

        List allUsers = userDAO.getAllUsers();
        ModelAndView model = getModelAndView(map, allUsers, new User());

        model.setViewName("admin");

        return model;

    }

    private ModelAndView getModelAndView(Map<String, Object> map, List allUsers, User user) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        map.put("userList", allUsers);
        map.put("user", user);
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContact(@ModelAttribute("user") User user,
                             BindingResult result, HttpServletRequest request) throws SQLException {
        String role = request.getParameter("roleCheck");
        if (role.equals("USER")) {
            userDAO.addUser(user);
        } else {
            userDAO.addAdmin(user);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) throws SQLException {

        userDAO.deleteUser(username);

        return "redirect:/admin";
    }

    @RequestMapping("/edit/{username}")
    public String editPage(@PathVariable("username") String username, Map<String, Object> map) throws SQLException {

        List allUsers = userDAO.getAllUsers();
        ModelAndView model = getModelAndView(map, allUsers, userDAO.search(username).get(0));

        model.setViewName("admin");

        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, Map<String, Object> map) throws SQLException {

        String snippet = request.getParameter("snippet");
        List foundUsers;
        if (snippet == null || snippet.equals("")) {
            foundUsers = userDAO.getAllUsers();
        } else {
            foundUsers = userDAO.search(snippet);
        }


        ModelAndView model = getModelAndView(map, foundUsers, new User());
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "edit/update", method = RequestMethod.GET)
    public String updatePage(@ModelAttribute("user") User user, BindingResult result) throws SQLException {

        userDAO.updateUser(user);
        return "redirect:/admin";
    }

}