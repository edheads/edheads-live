package com.example.demo.controllers;


import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Image;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ImageController imageController;

    /*
     **********************
     * ADMIN METHODS
     **********************
     */

    //INDEX (all, admin only)
    @RequestMapping(value="/admin")
    public ModelAndView getAdminDashboard(Model model) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            ModelAndView mv = new ModelAndView("admin/admin-dashboard");//setting view name here
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("not-allowed");
            return mv;
        }
    }

    //INDEX (all, admin only)
    @RequestMapping(value="/stem-careers/admin")
    public ModelAndView getAllCareers(Model model) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            Collection<User> professionals = (Collection<User>) userRepo.findAllByRoleOrderByDateCreated("Professional");
            ModelAndView mv = new ModelAndView("careers/careers-admin");//setting view name here
            mv.addObject("careers", professionals);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("not-allowed");
            return mv;
        }
    }

    //INDEX (all, admin only)
    @RequestMapping(value="/teachers/admin")
    public ModelAndView getAllTeachers(Model model) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            Collection<User> professionals = (Collection<User>) userRepo.findAllByRoleOrderByDateCreated("Teacher");
            ModelAndView mv = new ModelAndView("admin/teachers-admin");//setting view name here
            mv.addObject("careers", professionals);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("not-allowed");
            return mv;
        }
    }

    //INDEX (filter approvedByAdmin)
    @RequestMapping(value="/stem-careers/list")
    public ModelAndView getAllApprovedCareers() {
        Collection<User> professionals = (Collection<User>)userRepo.findByApprovedByAdminTrue();
        for(User pro : professionals) {
            pro.setImages(imageController.getImagesForParent(pro.getId()));
            for(Image im : pro.getImages()) {
                if ("Profile".equals(im.getImagePosition())) {
                    pro.setProfileImageInd(true);
                } else if ("Career1".equals(im.getImagePosition())
                            || "Career2".equals(im.getImagePosition())
                            || "Career3".equals(im.getImagePosition())
                            || "Career4".equals(im.getImagePosition())
                            || "Career5".equals(im.getImagePosition())) {
                    pro.setCareerImageInd(true);
                }
            }
        }

        ModelAndView mv = new ModelAndView("careers/careers-index");//setting view name here
        mv.addObject("careers", professionals);
        return mv;
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
        for(User pro : matchingPros) {
            pro.setImages(imageController.getImagesForParent(pro.getId()));

            for(Image im : pro.getImages()) {
                if ("Profile".equals(im.getImagePosition())) {
                    pro.setProfileImageInd(true);
                } else if ("Career1".equals(im.getImagePosition())
                        || "Career2".equals(im.getImagePosition())
                        || "Career3".equals(im.getImagePosition())
                        || "Career4".equals(im.getImagePosition())
                        || "Career5".equals(im.getImagePosition())) {
                    pro.setCareerImageInd(true);
                }
            }
        }

        ModelAndView mv = new ModelAndView("careers/careers-index");
        mv.addObject("careers", matchingPros);
        return mv;
    }

    //EDIT (approve only) for Edheads admin
    @PostMapping("/stem-careers/{id}/approve")
    public String approveCareer(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setApprovedByAdmin(true);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }

    //EDIT (disable only) for Edheads admin
    @PostMapping("/stem-careers/{id}/disable")
    public String disableCareer(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >=5)) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setApprovedByAdmin(false);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }

    //EDIT (give admin privileges) for Edheads admin
    @PostMapping("/stem-careers/{id}/admin")
    public String makeUserAdmin(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 9)) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setPrivileges(5);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }

    //EDIT (revoke admin privileges) for Edheads admin
    @PostMapping("/stem-careers/{id}/remove-admin")
    public String removeUserAdmin(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 9)) {
            Optional<User> user = (Optional<User>) userRepo.findById(id);
            user.ifPresent(founduser -> {
                        founduser.setPrivileges(1);
                        userRepo.save(founduser);
                    }
            );
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }

    //EDIT (form) for Edheads admin
    @RequestMapping("/stem-careers/{id}/edit")
    public ModelAndView showEditCareerForm(@PathVariable("id") Long id) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();
        boolean profileInd = false;

        Optional<User> user = (Optional<User>)userRepo.findById(id);
        Collection<Image> images =  imageController.getImagesForParent(id);
        for (Image image : images) {
            if("Profile" == image.getImagePosition()) {
                profileInd = true;
            }
        }

        ModelAndView mv = new ModelAndView("careers/careers-edit");
        mv.addObject("user", user);
        mv.addObject("role", role);
        mv.addObject("images", images);

        return mv;
    }

    //EDIT (submit) for Edheads admin and user self-edit
    @PostMapping("/stem-careers/edit")
    public String editCareerToDatabase(@ModelAttribute("user") User userToEdit) throws IOException {
        String role = getLoggedInUserRole();
        Long id = getLoggedInUserId();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {

            String keywordString = userToEdit.getKeywords() == null ? "" : userToEdit.getKeywords();

            keywordString = keywordString.replaceAll(", ", ",");
            keywordString = keywordString.replaceAll(" ", "-");
            keywordString = keywordString.toLowerCase();

            User user = userRepo.findById(userToEdit.getId()).get();
                        userToEdit.setKeywords(keywordString);
                        userToEdit.setEmail(user.getEmail());
                        userToEdit.setPassword(user.getPassword()); //do not clear from database
                        userToEdit.setDateUpdate(LocalDate.now());
                        userRepo.save(userToEdit);

            return "redirect:/stem-careers/admin";
        } else if (id.toString().equals(userToEdit.getId().toString())) {
            Optional<User> user = (Optional<User>) userRepo.findById(userToEdit.getId());
            user.ifPresent(founduser -> {
                        userToEdit.setApprovedByAdmin(false);
                        userToEdit.setEmail(founduser.getEmail());
                        userToEdit.setPassword(founduser.getPassword()); //do not clear from database
                        userToEdit.setDateUpdate(LocalDate.now());
                        userRepo.save(userToEdit);
                    }
            );
            return "redirect:/user";

        } else {
            return "redirect:/not-allowed";
        }
    }

    //DELETE (submit) for Edheads admin
    @PostMapping("/stem-careers/delete-user")
    public String deleteUserFromDatabase(@ModelAttribute("user") User userToDelete) throws IOException {

        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            if (userRepo.existsById(userToDelete.getId())) {
                userRepo.deleteById(userToDelete.getId());
            }
            return "redirect:/stem-careers/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }


    /*
     **********************
     * USER METHODS
     **********************
     */

    //SHOW (form) for user self-edit
    @RequestMapping("/user")
    public ModelAndView showUserProfile() {
       String role = getLoggedInUserRole();
       Long id = getLoggedInUserId();
       boolean profileInd = false;

       if (role.equalsIgnoreCase("Professional")) {
           if (id != null) {
               Optional<User> user = (Optional<User>) userRepo.findById(id);
               Collection<Image> images = imageController.getImagesForParent(id);
               for (Image image : images) {
                   if("Profile".equals(image.getImagePosition())) {
                       profileInd = true;
                   }
               }
               ModelAndView mv = new ModelAndView("users/users-career-show");
               mv.addObject("user", user);
               mv.addObject("images", images);
               mv.addObject("profileInd", profileInd);
               return mv;
           } else {
               return new ModelAndView("not-allowed");
           }
        } else if (role.equalsIgnoreCase("Teacher")) {
           if (id != null) {
               Optional<User> user = (Optional<User>) userRepo.findById(id);
               ModelAndView mv = new ModelAndView("users/users-teach-show");
               mv.addObject("user", user);
               return mv;
           } else {
               return new ModelAndView("not-allowed");
           }
       } else if (role.equalsIgnoreCase("Admin")) {
           if (id != null) {
               Optional<User> user = (Optional<User>) userRepo.findById(id);
               ModelAndView mv = new ModelAndView("users/users-admin-show");
               mv.addObject("user", user);
               return mv;
           } else {
               return new ModelAndView("not-allowed");
           }
       } else {
           return new ModelAndView("not-allowed");
       }

    }

    //EDIT (form) for Edheads admin
    @RequestMapping("/user/edit")
    public ModelAndView showEditUserForm() {
        Long id = getLoggedInUserId();

        Optional<User> user = userRepo.findById(id);
        ModelAndView mv = new ModelAndView("users/users-edit");
        mv.addObject("user", user.get());
        return mv;
    }

    /*
     **********************
     * HELPER METHODS
     **********************
     */
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

    public int getLoggedInUserPrivileges() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int privileges = 1;
        if (principal instanceof CustomUserDetails) {
            privileges = ((CustomUserDetails)principal).getPrivileges();
        }
        return privileges;
    }

    public Long getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = null;
        if (principal instanceof CustomUserDetails) {
            id = ((CustomUserDetails)principal).getId();
        }
        return id;
    }

}
