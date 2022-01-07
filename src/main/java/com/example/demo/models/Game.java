package com.example.demo.models;

import com.example.demo.models.enums.GradeRange;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameTitle;
    private String gameDescription;
    private String targetGradeRange;
    private String gameLink;
    private String launchLink;
    private String gameGuideLink;
    private String gameQuizLink;
    private String gameImage;
    private String playImage;

    public Game() {
    }

    public Game(String gameTitle, String targetGradeRange, String gameDescription, String gameLink, String launchLink) {
        this.gameTitle = gameTitle;
        this.targetGradeRange = targetGradeRange;
        this.gameDescription = gameDescription;
        this.gameLink = gameLink;
        this.launchLink = launchLink;
    }

    public Game(String gameTitle, String gameDescription, String targetGradeRange, String gameLink, String launchLink,
                String gameGuideLink, String gameQuizLink, String gameImage, String playImage) {
        this.gameTitle = gameTitle;
        this.gameDescription = gameDescription;
        this.targetGradeRange = targetGradeRange;
        this.gameLink = gameLink;
        this.launchLink = launchLink;
        this.gameGuideLink = gameGuideLink;
        this.gameQuizLink = gameQuizLink;
        this.gameImage = gameImage;
        this.playImage = playImage;
    }

    public Long getId() {
        return id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public String getTargetGradeRange() {
        return targetGradeRange;
    }

    public String getGameLink() {
        return gameLink;
    }

    public String getLaunchLink() {
        return launchLink;
    }

    public String getGameGuideLink() {
        return gameGuideLink;
    }

    public String getGameQuizLink() {
        return gameQuizLink;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public void setTargetGradeRange(String targetGradeRange) {
        this.targetGradeRange = targetGradeRange;
    }

    public void setGameLink(String gameLink) {
        this.gameLink = gameLink;
    }

    public void setLaunchLink(String launchLink) {
        this.launchLink = launchLink;
    }

    public void setGameGuideLink(String gameGuideLink) {
        this.gameGuideLink = gameGuideLink;
    }

    public void setGameQuizLink(String gameQuizLink) {
        this.gameQuizLink = gameQuizLink;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getPlayImage() {
        return playImage;
    }

    public void setPlayImage(String gameImage) {
        this.playImage = playImage;
    }
}
