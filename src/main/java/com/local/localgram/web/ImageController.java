package com.local.localgram.web;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.handler.ex.CustomValidationException;
import com.local.localgram.service.ImageService;
import com.local.localgram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/","/image/story"})
    public String story(){
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(){
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload(){
        return "image/upload";
    }

    // 이미지등록
    @PostMapping("/image")
    public String imageUpload(
            ImageUploadDto imageUploadDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        log.info("imageUploadDto : {}",imageUploadDto);
        if (imageUploadDto.getFile().isEmpty()){
            log.info("이미지가 첨부되지 않았습니다.");
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.",null);
        } else {
            imageService.사진업로드(imageUploadDto,principalDetails);
        }
        return "redirect:/user/" + principalDetails.getUser().getId();
    }
}
