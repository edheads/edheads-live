package com.example.demo.models;


import com.example.demo.models.enums.AccountType;
import com.example.demo.models.enums.USAState;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private LocalDate dateCreated;
    private LocalDate dateUpdate;
    private boolean subscribeToNewsInd;

    private String schoolOrganization;
    private String district;
    private String country;
    private String state;
    private String city;

    private String gradeRange;
    private String schoolType;
    private String numberOfStudents;
    private String percentOfFreeLunches;

    private String description;

    private boolean approvedByAdmin;
    private String careerTitle;
    private String careerDescription;
    private String educationDescription;
    private String careerPathChallenge;
    private String jobBestDescription;
    private String jobWorstDescription;
    private String jobExcitingDescription;
    private String memorableCareerMoment;
    private String pastChangeDesc;
    private String futureChangeDesc;

    private String profileImage;
    private ArrayList<String> careerImages;
    private String keywords;

    private String company;
    private String university;

    private boolean gameHelpInd;
    private boolean gameFundingInd;
    private boolean socialMediaInd;
    private boolean volunteerInd;


    public User() {}

    public User(String email) {
        this.email = email;
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String role, String email, String password, String firstName, String lastName,
                LocalDate dateCreated, LocalDate dateUpdate, boolean subscribeToNewsInd, String schoolOrganization,
                String district, String country, String state, String city, String gradeRange, String schoolType,
                String numberOfStudents, String percentOfFreeLunches, String description, boolean approvedByAdmin,
                String careerTitle, String careerDescription, String educationDescription, String careerPathChallenge,
                String jobBestDescription, String jobWorstDescription, String jobExcitingDescription, String memorableCareerMoment, String pastChangeDesc,
                String futureChangeDesc, String company, String university, boolean gameHelpInd, boolean gameFundingInd,
                boolean socialMediaInd, boolean volunteerInd, String profileImage) {

        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = dateCreated;
        this.dateUpdate = dateUpdate;
        this.subscribeToNewsInd = subscribeToNewsInd;

        //teacher attributes
        this.schoolOrganization = schoolOrganization;
        this.district = district;
        this.country = country;
        this.state = state;
        this.city = city;
        this.gradeRange = gradeRange;
        this.schoolType = schoolType;
        this.numberOfStudents = numberOfStudents;
        this.percentOfFreeLunches = percentOfFreeLunches;
        this.description = description;

        //career attributes
        this.approvedByAdmin = approvedByAdmin;
        this.careerTitle = careerTitle;
        this.careerDescription = careerDescription;
        this.educationDescription = educationDescription;
        this.careerPathChallenge = careerPathChallenge;
        this.jobBestDescription = jobBestDescription;
        this.jobWorstDescription = jobWorstDescription;
        this.jobExcitingDescription = jobExcitingDescription;
        this.memorableCareerMoment = memorableCareerMoment;
        this.pastChangeDesc = pastChangeDesc;
        this.futureChangeDesc = futureChangeDesc;
        this.profileImage = profileImage;
//        this.careerImages = careerImages;
        this.keywords = keywords;

        this.company = company;
        this.university = university;

        //Edheads support atrributes
        this.gameHelpInd = gameHelpInd;
        this.gameFundingInd = gameFundingInd;
        this.socialMediaInd = socialMediaInd;
        this.volunteerInd = volunteerInd;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isSubscribeToNewsInd() {
        return subscribeToNewsInd;
    }

    public void setSubscribeToNewsInd(boolean subscribeToNewsInd) {
        this.subscribeToNewsInd = subscribeToNewsInd;
    }

    public String getSchoolOrganization() {
        return schoolOrganization;
    }

    public void setSchoolOrganization(String schoolOrganization) {
        this.schoolOrganization = schoolOrganization;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGradeRange() {
        return gradeRange;
    }

    public void setGradeRange(String gradeRange) {
        this.gradeRange = gradeRange;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(String numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getPercentOfFreeLunches() {
        return percentOfFreeLunches;
    }

    public void setPercentOfFreeLunches(String percentOfFreeLunches) {
        this.percentOfFreeLunches = percentOfFreeLunches;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApprovedByAdmin() {
        return approvedByAdmin;
    }

    public void setApprovedByAdmin(boolean approvedByAdmin) {
        this.approvedByAdmin = approvedByAdmin;
    }

    public String getCareerTitle() {
        return careerTitle;
    }

    public void setCareerTitle(String careerTitle) {
        this.careerTitle = careerTitle;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public String getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
    }

    public String getCareerPathChallenge() {
        return careerPathChallenge;
    }

    public void setCareerPathChallenge(String careerPathChallenge) {
        this.careerPathChallenge = careerPathChallenge;
    }

    public String getJobBestDescription() {
        return jobBestDescription;
    }

    public void setJobBestDescription(String jobBestDescription) {
        this.jobBestDescription = jobBestDescription;
    }

    public String getJobWorstDescription() {
        return jobWorstDescription;
    }

    public void setJobWorstDescription(String jobWorstDescription) {
        this.jobWorstDescription = jobWorstDescription;
    }

    public String getJobExcitingDescription() {
        return jobExcitingDescription;
    }

    public void setJobExcitingDescription(String jobExcitingDescription) {
        this.jobExcitingDescription = jobExcitingDescription;
    }

    public String getMemorableCareerMoment() {
        return memorableCareerMoment;
    }

    public void setMemorableCareerMoment(String memorableCareerMoment) {
        this.memorableCareerMoment = memorableCareerMoment;
    }

    public String getPastChangeDesc() {
        return pastChangeDesc;
    }

    public void setPastChangeDesc(String pastChangeDesc) {
        this.pastChangeDesc = pastChangeDesc;
    }

    public String getFutureChangeDesc() {
        return futureChangeDesc;
    }

    public void setFutureChangeDesc(String futureChangeDesc) {
        this.futureChangeDesc = futureChangeDesc;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public boolean isGameHelpInd() {
        return gameHelpInd;
    }

    public void setGameHelpInd(boolean gameHelpInd) {
        this.gameHelpInd = gameHelpInd;
    }

    public boolean isGameFundingInd() {
        return gameFundingInd;
    }

    public void setGameFundingInd(boolean gameFundingInd) {
        this.gameFundingInd = gameFundingInd;
    }

    public boolean isSocialMediaInd() {
        return socialMediaInd;
    }

    public void setSocialMediaInd(boolean socialMediaInd) {
        this.socialMediaInd = socialMediaInd;
    }

    public boolean isVolunteerInd() {
        return volunteerInd;
    }

    public void setVolunteerInd(boolean volunteerInd) {
        this.volunteerInd = volunteerInd;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<String> getCareerImages() {
        return careerImages;
    }

    public void setCareerImages(ArrayList<String> careerImages) {
        this.careerImages = careerImages;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
