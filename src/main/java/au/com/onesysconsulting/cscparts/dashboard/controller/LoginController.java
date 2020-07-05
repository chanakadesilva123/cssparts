package au.com.onesysconsulting.cscparts.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import au.com.onesysconsulting.cscparts.dashboard.model.User;
import au.com.onesysconsulting.cscparts.dashboard.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(ChartController.class);
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user",
                    "There is already a user registered with the login name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/home","/screen/home"}, method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        LOG.info("dashboardHome-->"+request.getServletPath());
        modelAndView.addObject("isFullScreen", request.getServletPath().startsWith("/screen/"));
        
        modelAndView.addObject("lastUpdated", new Date());
        
        modelAndView.setViewName("admin/dashboardHome");
        return modelAndView;
    }
}
