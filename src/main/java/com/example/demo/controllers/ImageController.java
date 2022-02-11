package com.example.demo.controllers;

import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping("/sorry")
    public String imageUploadSorry() {
        return "/images/sorry";
    }

    @RequestMapping("/image/view")
    public String viewSingleImage(Model model, @RequestParam("image") String id) {
        Image image = imageRepository.findById(id).get();
        model.addAttribute("image", image);
        return "/images/image-view";
    }

    @RequestMapping("/user/upload")
    public String displayImageUploadForm(Model model, @RequestParam("position") String position) {
        model.addAttribute("image", new Image());
        model.addAttribute("position", position);
        return "/images/image-upload";
    }

    @PostMapping("/user/upload")
    public String genericUpload(@ModelAttribute Image image, @RequestParam("file") MultipartFile file,
                                @RequestParam("position") String position) throws MaxUploadSizeExceededException {

        //concert uploaded file size to MB
        double fileSizeMB = file.getSize() * 0.00000095367432;
        if(fileSizeMB > 4) {
            //do not allow file larger than 4MB to be uploaded
            return "redirect:/sorry";
        } else{
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                image.setName(fileName);
                image.setType(file.getContentType());
                image.setDisplayData(Base64.getEncoder().encodeToString(file.getBytes()));
                image.setCaption(image.getCaption());
                image.setParentType("User");
                image.setParentId(getLoggedInUserId());

                if(position.equals("Profile")
                        || position.equals("Career1")
                        || position.equals("Career2")
                        || position.equals("Career3")
                        || position.equals("Career4")
                        || position.equals("Career5")){

                    Collection<Image> images = getImagesForParent(getLoggedInUserId());
                    for (Image i : images) {
                        if (position.equals(i.getImagePosition())){
                            deleteImageFromDatabase(i);
                        }
                    }
                    image.setImagePosition(position);
                    imageRepository.save(image);
                }

            } catch (Exception e) {
                return "redirect:/sorry";
            }
            return "redirect:/user";
        }
    }

    @PostMapping("/user/delete-image")
    public String deleteImageFromDatabase(@ModelAttribute("image") Image imageToDelete) throws IOException {
            System.out.println("Deleting image" + imageToDelete.getId());
            if (imageRepository.existsById(imageToDelete.getId())) {
                imageRepository.deleteById(imageToDelete.getId());
            }
            return "redirect:/user";
    }

    public Collection<Image> getImagesForParent(Long parentId) {
        Collection<Image> images = imageRepository.findAllByParentId(parentId);
        return images;
    }

    public Long getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = null;
        if (principal instanceof CustomUserDetails) {
            id = ((CustomUserDetails)principal).getId();
        }
        return id;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleSizeExceededException(HttpServletRequest request, Exception ex) {
        return "redirect:/sorry";
    }
}
