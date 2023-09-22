package com.local.localgram.service;

import com.local.localgram.config.auth.PrincipalDetails;
import com.local.localgram.domain.image.Image;
import com.local.localgram.domain.image.ImageRepository;
import com.local.localgram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    // 5. yml 파일에 지정한 경로 호출
    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto,PrincipalDetails principalDetails){

        // 2. UUID 객체 생성
        UUID uuid = UUID.randomUUID();

        // 1. 업로드 되는 원본 파일명을 imageFileName이라고 지정
        // 3. UUID를 더한값으로 지정
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        log.info("imageFileName: {}",imageFileName);

        // 4. image 저장 경로 지정
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 6. 파일 업로드하기
        // 통신,I/O -> 예외가 발생할 수 있다.
        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        // Image 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // UUID+OriginalFilename
        Image imageEntity = imageRepository.save(image);

        log.info("imageEntity : {}",imageEntity);
    }
}
