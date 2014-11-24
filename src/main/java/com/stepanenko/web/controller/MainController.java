package com.stepanenko.web.controller;

import com.stepanenko.logic.Product;
import com.stepanenko.service.ProductService;
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
    private ProductService productService;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage(Map<String, Object> map) {

        List allProducts = productService.getAllProducts();
        ModelAndView model = getModelAndView(map, allProducts, new Product());
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This is default page");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(Map<String, Object> map) {

        List allProducts = productService.getAllProducts();
        ModelAndView model = getModelAndView(map, allProducts, new Product());

        model.setViewName("admin");

        return model;

    }

    private ModelAndView getModelAndView(Map<String, Object> map, List allProducts, Product product) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only");
        map.put("productList", allProducts);
        map.put("product", product);
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
    public ModelAndView accessDenied() {

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
    public String addContact(@ModelAttribute("product") Product product,
                             BindingResult result, HttpServletRequest request) throws SQLException {
            productService.addProduct(product);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{good_id}")
    public String deleteProduct(@PathVariable("good_id") int good_id) throws SQLException {

        productService.deleteProduct(good_id);

        return "redirect:/admin";
    }

    @RequestMapping("/edit/{name}")
    public String editPage(@PathVariable("name") String name, Map<String, Object> map) throws SQLException {

        List allProducts = productService.getAllProducts();
        ModelAndView model = getModelAndView(map, allProducts, productService.search(name).get(0));

        model.setViewName("admin");

        return "update";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, Map<String, Object> map) throws SQLException {

        String snippet = request.getParameter("snippet");
        List foundProducts;
        if (snippet == null || snippet.equals("")) {
            foundProducts = productService.getAllProducts();
        } else {
            foundProducts = productService.search(snippet);
        }


        ModelAndView model = getModelAndView(map, foundProducts, new Product());
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "edit/update", method = RequestMethod.GET)
    public String updatePage(@ModelAttribute("product") Product product, BindingResult result) throws SQLException {

        productService.updateProduct(product);
        return "redirect:/admin";
    }

}