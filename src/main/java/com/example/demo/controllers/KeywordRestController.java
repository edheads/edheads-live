package com.example.demo.controllers;

import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Keyword;
import com.example.demo.repositories.KeywordRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class KeywordRestController {

    @Resource
    KeywordRepository keywordRepo;

    //INDEX
    @RequestMapping("/keywords/admin")
    public ModelAndView getAllKeywordsList(Model model) {
        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            Collection<Keyword> keywords = (Collection<Keyword>)keywordRepo.findByOrderByDisplayValueAsc();
            ModelAndView mv = new ModelAndView("careers/careers-keywords");//setting view name here
            mv.addObject("keywords", keywords);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("not-allowed");
            return mv;
        }
    }

    @RequestMapping(value="/stem-careers/tags")
    public ModelAndView getStemKeywordsPage(Model model) {
        Collection<Keyword> keywords = (Collection<Keyword>)keywordRepo.findByOrderByDisplayValueAsc();
        ModelAndView mv = new ModelAndView("keywords");//setting view name here
        mv.addObject("keywords", keywords);
        return mv;
    }

    //NEW (form)
    @RequestMapping("/keywords/new")
    public String showNewKeywordForm(Model model) {

        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            model.addAttribute("keyword", new Keyword());
            return "/careers/careers-keyword-add";
        } else {
            return "not-allowed";
        }
    }

    //NEW (submit)
    @PostMapping("/keywords")
    public String addKeywordToDatabase(@ModelAttribute Keyword keyword) {

        Keyword keywordToAdd = new Keyword();

        String keywordString = keyword.getDisplayValue();

        keywordString = keywordString.replaceAll(", ", ",");
        keywordString = keywordString.replaceAll(" ", "-");
        keywordString = keywordString.toLowerCase();

        keywordToAdd.setDisplayValue(keyword.getDisplayValue());
        keywordToAdd.setCodeValue(keywordString);

        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            keywordRepo.save(keywordToAdd);
            return "redirect:/keywords/admin";
        } else {
            return "redirect:/not-allowed";
        }
    }

    @PostMapping("/keywords/{id}/delete")
    public String deleteKeywordToDatabase(@PathVariable("id") Long id) {

        String role = getLoggedInUserRole();
        int privileges = getLoggedInUserPrivileges();

        if(role.equalsIgnoreCase("Admin") || (privileges >= 5)) {
            if (keywordRepo.existsById(id)) {
                keywordRepo.deleteById(id);
            }
            return "redirect:/keywords/admin";
        } else {
            return "redirect:/not-allowed";
        }
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

}
