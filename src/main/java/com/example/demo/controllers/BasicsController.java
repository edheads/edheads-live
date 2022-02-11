package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.time.LocalDate;


@Controller
public class BasicsController {
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

    @RequestMapping("/not-allowed")
    public String pageViewNotAllowed() {
        return "not-allowed";
    }

    @RequestMapping("/about-us")
    public String displayAboutUsPage() {

        return "aboutUs";
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


    /*
     * LOGIN & SIGN UP ROUTES
     */
    @GetMapping("/login")
    public String displayLoginPage() {

        return "login";
    }

    @RequestMapping("/sign-up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/sign-up/signup_form";
    }

    @RequestMapping("/career_signup")
    public String showCareerRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/sign-up/career_signup";
    }

    @RequestMapping("/teacher_signup")
    public String showTeacherRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/sign-up/teacher_signup";
    }

    @RequestMapping("/register_success")
    public String showSuccessfulRegistrationForm() {
        return "/sign-up/register_success";
    }

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
        userToAdd.setPrivileges(1);
        userRepo.save(userToAdd);

        return "redirect:register_success";
    }

    @PostMapping("/register_professional")
    public String processRegister(@ModelAttribute User incomingUser, String test) {

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

        userToAdd.setDateCreated(LocalDate.now());
        userToAdd.setDateUpdate(LocalDate.now());
        userToAdd.setPrivileges(1);
        userRepo.save(userToAdd);

        return "redirect:register_success";
    }
}
