package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


@Controller
public class BasicsController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads/users";

    @Resource
    UserRepository userRepo;

    @RequestMapping("/")
    public String displayRoot() {
        return "welcome";
    }

    @RequestMapping("/welcome")
    public String displayWelcomePage() {
        return "welcome";
    }

    @RequestMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @RequestMapping("/career_signup")
    public String showCareerRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "career_signup";
    }

    @RequestMapping("/teacher_signup")
    public String showTeacherRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "teacher_signup";
    }

    @RequestMapping("/register_success")
    public String showSuccessfulRegistrationForm() {
        return "register_success";
    }

//    @PostMapping("/process_register")
//    public String processRegister(@ModelAttribute("user") User user) {
//        System.out.println("Log process_register step: " + user.getFirstName());
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        System.out.println(user.getFirstName() + "  " + user.getEmail() + "    " + user.getPassword());
//
//        userRepo.save(user);
//        return "redirect:register_success";
//    }

    @PostMapping("/navigate_register")
    public String navigateRegister(@RequestParam("role") String role) {
        if(role.equalsIgnoreCase("Teacher")) {
            return "redirect:teacher_signup";
        } else {
            return "redirect:career_signup";
        }
    }

    @PostMapping("/register_teacher")
    public String processRegister(@ModelAttribute User incomingUser) {

        User userToAdd = new User(incomingUser.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(incomingUser.getPassword());
        userToAdd.setPassword(encodedPassword);

        userToAdd.setRole(incomingUser.getRole());
        userToAdd.setFirstName(incomingUser.getFirstName());
        userToAdd.setLastName(incomingUser.getLastName());
        userToAdd.setSubscribeToNewsInd(incomingUser.isSubscribeToNewsInd());

        userToAdd.setSchoolOrganization(incomingUser.getSchoolOrganization());
        userToAdd.setDistrict(incomingUser.getDistrict());
        userToAdd.setCountry(incomingUser.getCountry());
        userToAdd.setState(incomingUser.getState());
        userToAdd.setCity(incomingUser.getCity());
        userToAdd.setGradeRange(incomingUser.getGradeRange());
        userToAdd.setSchoolType(incomingUser.getSchoolType());
        userToAdd.setNumberOfStudents(incomingUser.getNumberOfStudents());
        userToAdd.setPercentOfFreeLunches(incomingUser.getPercentOfFreeLunches());
        userToAdd.setDescription(incomingUser.getDescription());

        userToAdd.setGameHelpInd(incomingUser.isGameHelpInd());
        userToAdd.setGameFundingInd(incomingUser.isGameFundingInd());
        userToAdd.setSocialMediaInd(incomingUser.isSocialMediaInd());
        userToAdd.setVolunteerInd(incomingUser.isVolunteerInd());

        userToAdd.setDateCreated(LocalDate.now());
        userToAdd.setDateUpdate(LocalDate.now());
        userRepo.save(userToAdd);

        return "redirect:register_success";
    }

    @PostMapping("/register_professional")
    public String processRegister(@ModelAttribute User incomingUser, @RequestParam("file") MultipartFile profileImage) {

        User userToAdd = new User(incomingUser.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(incomingUser.getPassword());
        userToAdd.setPassword(encodedPassword);

        userToAdd.setRole(incomingUser.getRole());
        userToAdd.setFirstName(incomingUser.getFirstName());
        userToAdd.setLastName(incomingUser.getLastName());
        userToAdd.setSubscribeToNewsInd(incomingUser.isSubscribeToNewsInd());

        userToAdd.setCountry(incomingUser.getCountry());
        userToAdd.setState(incomingUser.getState());
        userToAdd.setCity(incomingUser.getCity());

        userToAdd.setApprovedByAdmin(false);
        userToAdd.setCareerTitle(incomingUser.getCareerTitle());
        userToAdd.setCareerDescription(incomingUser.getCareerDescription());
        userToAdd.setEducationDescription(incomingUser.getEducationDescription());
        userToAdd.setCareerPathChallenge(incomingUser.getCareerPathChallenge());
        userToAdd.setJobBestDescription(incomingUser.getJobBestDescription());
        userToAdd.setJobWorstDescription(incomingUser.getJobWorstDescription());
        userToAdd.setJobExcitingDescription(incomingUser.getJobExcitingDescription());
        userToAdd.setMemorableCareerMoment(incomingUser.getMemorableCareerMoment());
        userToAdd.setPastChangeDesc(incomingUser.getPastChangeDesc());
        userToAdd.setFutureChangeDesc(incomingUser.getFutureChangeDesc());
        userToAdd.setDescription(incomingUser.getDescription());
        userToAdd.setCompany(incomingUser.getCompany());
        userToAdd.setUniversity(incomingUser.getUniversity());

        userToAdd.setGameHelpInd(incomingUser.isGameHelpInd());
        userToAdd.setGameFundingInd(incomingUser.isGameFundingInd());
        userToAdd.setSocialMediaInd(incomingUser.isSocialMediaInd());
        userToAdd.setVolunteerInd(incomingUser.isVolunteerInd());

        Path profileImagePath = Paths.get(uploadDirectory, LocalDate.now()+"-" + profileImage.getOriginalFilename());
        try {
            Files.write(profileImagePath, profileImage.getBytes());
            incomingUser.setProfileImage(LocalDate.now()+"-" + profileImage.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userToAdd.setProfileImage(LocalDate.now()+"-" + profileImage.getOriginalFilename());

        userToAdd.setDateCreated(LocalDate.now());
        userToAdd.setDateUpdate(LocalDate.now());
        userRepo.save(userToAdd);

            return "redirect:register_success";
    }

//    @RequestMapping("/career_images")
//    public String showCareerImagesForm() {
//        return "career_images";
//    }

//    @PostMapping("/career_images") //, consumes = { "multipart/form-data" }
//    public String submitCareerImages(@ModelAttribute User incomingUser,
//            @RequestParam MultipartFile profileImage, @RequestParam MultipartFile[] careerImages, ModelMap modelMap) {
//        StringBuilder profileImageString = new StringBuilder();
//        StringBuilder careerImageString = new StringBuilder();
//
//        Path profileImagePath = Paths.get(uploadDirectory, incomingUser.getId() + "-" + profileImage.getOriginalFilename());
//        try {
//            Files.write(profileImagePath, profileImage.getBytes());
//            System.out.println("******************************"+profileImagePath.getFileName().toString());
//            incomingUser.setProfileImage(profileImagePath.getFileName().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        for (MultipartFile image : careerImages) {
//            Path careerImagesPath = Paths.get(uploadDirectory, incomingUser.getId() + "-" + image.getOriginalFilename());
//            try {
//                Files.write(careerImagesPath, image.getBytes());
//                System.out.println(careerImagesPath.getFileName().toString());
//                careerImageString.append(file.getOriginalFilename()+" ");
//                //incomingUser.setProfileImage(careerImagesPath.getFileName().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        userRepo.save(incomingUser);
//        modelMap.addAttribute("profileImage", profileImage);
//        modelMap.addAttribute("careerImages", careerImages);
//        return "redirect:register_success";
//    }

    @RequestMapping("/about-us")
    public String displayAboutUsPage() {

        return "aboutUs";
    }

    @GetMapping("/login")
    public String displayLoginPage() {

        return "login";
    }

    @RequestMapping("/faq")
    public String displayFaqPage() {

        return "faq";
    }

    @RequestMapping("/awards")
    public String displayAwardsPage() {

        return "awards";
    }

    @RequestMapping("/privacy")
    public String displayPrivacyPage() {

        return "privacy";
    }

    @RequestMapping("/terms-of-use")
    public String displayTermsOfUsePage() {

        return "termsOfUse";
    }

    @RequestMapping("/activity-help")
    public String displayActivityHelpPage() {

        return "activityHelp";
    }


    @RequestMapping("/our-games")
    public String displayOurGamesPage() {

        return "allGamesView";
    }

    @RequestMapping("/nano-start-up")
    public String displayNanoStartUpGamePage() {

        return "nanoStartUp";
    }

    @RequestMapping("/manufacturing-technician")
    public String displayManufacturingTechnicianGamePage() {

        return "manufacturingTechnician";
    }

    @RequestMapping("/manufacturing-quiz")
    public String displayManufacturingQuizPage() {

        return "manufacturingQuiz";
    }


    @RequestMapping("/simple-machines")
    public String displaySimpleMachinesGamePage() {

        return "simpleMachines";
    }

    @RequestMapping("/get-involved")
    public String displayGetInvolvedPage() {

        return "getInvolved";
    }

    @RequestMapping("/donate")
    public String displayDonatePage() {

        return "donate";
    }

    @RequestMapping("/teachers")
    public String displayTeachersPage() {

        return "teacherResources";
    }

    @RequestMapping("/basic-page")
    public String displayBasicPage() {

        return "basicPage";
    }

    @RequestMapping("/header-test")
    public String displayHeaderTestPage() {

        return "newHeader";
    }

    @RequestMapping("/more-fun-stuff")
    public String displayMoreFunStuffPage() {

        return "moreFunStuff";
    }
}
