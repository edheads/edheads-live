package com.example.demo.models;

import com.example.demo.models.enums.AccountType;
import com.example.demo.models.enums.GradeRange;
import com.example.demo.models.enums.USAState;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professional extends User {

    private boolean approvedByAdmin = false;
    private String careerTitle;
    private String careerDescription;
    private String educationDescription;
    private String jobBestDescription;
    private String jobWorstDescription;
    private String pastChangeDesc;
    private String futureChangeDesc;
    private String careerPathChallenge;

    private AccountType accountType;
    private String company;
    private String university;

    private boolean gameHelpInd;
    private boolean gameFundingInd;
    private boolean socialMediaInd;
    private boolean volunteerInd;


    public Professional() {
    }

    public Professional(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public Professional(String email, String firstName, String lastName, boolean approvedByAdmin, String careerTitle, String careerDescription, String educationDescription, String jobBestDescription, String jobWorstDescription, String pastChangeDesc, String careerPathChallenge, String futureChangeDesc, AccountType accountType, String company, String university, boolean gameHelpInd, boolean gameFundingInd, boolean socialMediaInd, boolean volunteerInd) {
        super(email, firstName, lastName);
        this.approvedByAdmin = approvedByAdmin;
        this.careerTitle = careerTitle;
        this.careerDescription = careerDescription;
        this.educationDescription = educationDescription;
        this.jobBestDescription = jobBestDescription;
        this.jobWorstDescription = jobWorstDescription;
        this.pastChangeDesc = pastChangeDesc;
        this.careerPathChallenge = careerPathChallenge;
        this.futureChangeDesc = futureChangeDesc;
        this.accountType = accountType;
        this.company = company;
        this.university = university;
        this.gameHelpInd = gameHelpInd;
        this.gameFundingInd = gameFundingInd;
        this.socialMediaInd = socialMediaInd;
        this.volunteerInd = volunteerInd;
    }


    public boolean isApprovedByAdmin() {
        return approvedByAdmin;
    }

    public String getCareerTitle() {
        return careerTitle;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public String getEducationDescription() {
        return educationDescription;
    }

    public String getJobBestDescription() {
        return jobBestDescription;
    }

    public String getJobWorstDescription() {
        return jobWorstDescription;
    }

    public String getPastChangeDesc() {
        return pastChangeDesc;
    }

    public String getCareerPathChallenge() {
        return careerPathChallenge;
    }

    public String getFutureChangeDesc() {
        return futureChangeDesc;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getCompany() {
        return company;
    }

    public String getUniversity() {
        return university;
    }

    public boolean isGameHelpInd() {
        return gameHelpInd;
    }

    public boolean isGameFundingInd() {
        return gameFundingInd;
    }

    public boolean isSocialMediaInd() {
        return socialMediaInd;
    }

    public boolean isVolunteerInd() {
        return volunteerInd;
    }
}
