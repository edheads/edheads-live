package com.example.demo.controllers;

import com.example.demo.repositories.MediaManagementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class MediaManagementController {

    @Resource
    MediaManagementRepository mediaManagementRepository;

    @RequestMapping("/media-management")
    public String displayMediaEntryFormPage() {

        return "mediaManagementView";
    }


}
