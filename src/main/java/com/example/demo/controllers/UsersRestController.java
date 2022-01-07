package com.example.demo.controllers;


import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Game;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersRestController {

    @Resource
    UserRepository userRepo;

//    @RequestMapping("/fetch-all-users")
//    public Collection<User> getAllUsersList() {
//        return (Collection<User>) userRepo.findAll();
//    }

    //INDEX (all, admin only)
    @RequestMapping(value="/stem-careers/admin")
    public ModelAndView getAllCareers(Model model) {
        String role = getLoggedInUserRole();

        if(role.equalsIgnoreCase("Admin")) {
            Collection<User> professionals = (Collection<User>) userRepo.findAllByRoleOrderByDateCreated("Professional");
            ModelAndView mv = new ModelAndView("careers/careers-admin");//setting view name here
            mv.addObject("careers", professionals);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("login");
            return mv;
        }
    }

    //INDEX (filter approvedByAdmin)
    @RequestMapping(value="/stem-careers/list")
    public ModelAndView getAllApprovedCareers() {
        Collection<User> professionals = (Collection<User>)userRepo.findByApprovedByAdminTrue();
        ModelAndView mv = new ModelAndView("careers/careers-index");//setting view name here
        mv.addObject("careers", professionals);
        return mv;
    }

    //INDEX (filter approvedByAdmin, return by keyword)
    @RequestMapping(value="/stem-careers/tags")
    public String getKeywordsPage() {
        return "keywords";
    }

    //INDEX (filter approvedByAdmin, return by keyword)
    @RequestMapping(value="/stem-careers/tags/{tag}")
    public ModelAndView getAllApprovedCareersByKeyword(@PathVariable("tag") String keyword) {
        Collection<User> professionals = (Collection<User>)userRepo.findByApprovedByAdminTrue();
        List<User> matchingPros = new ArrayList<>();
        for(User pro : professionals) {
            if(pro.getKeywords().contains(keyword)) {
                matchingPros.add(pro);
            }
        }
        ModelAndView mv = new ModelAndView("careers/careers-keyword");
        mv.addObject("careers", matchingPros);
        return mv;
    }

//    @PostMapping("/add-user")
//    public Collection<User> addUserToDatabase(@RequestBody User userToAdd) throws IOException {
//        User newUser = new User(userToAdd.getEmail(), userToAdd.getFirstName(), userToAdd.getLastName());
//        userRepo.save(newUser);
//        return (Collection<User>) userRepo.findAll();
//    }

    //SHOW (id)
//    @RequestMapping(value="/stem-careers/{id}")
//    public ModelAndView getUserById(@PathVariable("id") Long id) {
//        String role = getLoggedInUserRole();
//
//        Optional<User> user = (Optional<User>)userRepo.findById(id);
//        ModelAndView mv = new ModelAndView("careers/careers-show");
//        mv.addObject("user ", user);
//        mv.addObject("role", role);
//        return mv;
//    }

    //EDIT (form)
    @RequestMapping("/stem-careers/{id}/edit")
    public ModelAndView showEditGameForm(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();

        Optional<User> user = (Optional<User>)userRepo.findById(id);
        ModelAndView mv = new ModelAndView("careers/careers-edit");
        mv.addObject("user", user);
        mv.addObject("role", role);
        return mv;
    }

    //EDIT (approve only)
    @PostMapping("/stem-careers/{id}/approve")
    public String approveCareer(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();

        if(role.equalsIgnoreCase("Admin")) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setApprovedByAdmin(true);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:login";
        }
    }

    //EDIT (disable only)
    @PostMapping("/stem-careers/{id}/disable")
    public String disableCareer(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();

        if(role.equalsIgnoreCase("Admin")) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setApprovedByAdmin(false);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:login";
        }
    }

    //EDIT (submit)
    @PostMapping("/stem-careers/edit")
    public String editCareerToDatabase(@ModelAttribute("user") User userToEdit) throws IOException {
        String role = getLoggedInUserRole();

        String keywordString = userToEdit.getKeywords();
        System.out.println("*********************ORIG:"+keywordString);

        keywordString = keywordString.replaceAll(", ", ",");
        keywordString = keywordString.replaceAll(" ", "-");
        keywordString = keywordString.toLowerCase();


        //engineering,aerospace,forces,motion

        if(role.equalsIgnoreCase("Admin")) {
            Optional<User> user = (Optional<User>) userRepo.findById(userToEdit.getId());
            user.ifPresent(founduser -> {
                userToEdit.setEmail(founduser.getEmail());
                userToEdit.setPassword(founduser.getPassword()); //do not clear from database
                        userToEdit.setProfileImage(founduser.getProfileImage()); //do not clear from database
                        userToEdit.setDateUpdate(LocalDate.now());
                        userRepo.save(userToEdit);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:login";
        }
    }

    @PostMapping("/delete-user")
    public String deleteUserFromDatabase(@ModelAttribute("user") User userToDelete) throws IOException {

        String role = getLoggedInUserRole();

        if(role.equalsIgnoreCase("Admin")) {
            if (userRepo.existsById(userToDelete.getId())) {
                userRepo.deleteById(userToDelete.getId());
            }
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:login";
        }
    }

    public String getLoggedInUserRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = "";
        if (principal instanceof CustomUserDetails) {
            role = ((CustomUserDetails)principal).getRole();
        } else {
            role = principal.toString();
        }

        return role;
    }


}
