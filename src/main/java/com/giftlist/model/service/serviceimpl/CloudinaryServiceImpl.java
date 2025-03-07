package com.giftlist.model.service.serviceimpl;

import com.cloudinary.Cloudinary;
import com.giftlist.model.dto.response.CloudinaryResponse;
import com.giftlist.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryServiceImpl {

    private final Cloudinary cloudinary;

    public CloudinaryResponse uploadFile(final MultipartFile file, final String fileName) {
        try {
            final Map result = cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", "trainee/" + fileName));
            final String url = result.get("url").toString();
            final String publicId = result.get("public_id").toString();
            return CloudinaryResponse.builder().publicId(publicId).url(url).build();
        } catch (IOException e) {
            log.error("Failed to upload file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String[] uploadImage(final MultipartFile file) {
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse cloudinaryResponse = uploadFile(file, fileName);
        return new String[] {cloudinaryResponse.getUrl(), cloudinaryResponse.getPublicId()}; /*TODO: [0]: image url, [1]: cloudinary image id*/
    }

    public void deleteFile(final String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            log.error("Failed to delete file: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /* TODO: write it into controller in holidays
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestPart final MultipartFile file) {
        return ResponseEntity.ok(service(request));
    }*/

   /* TODO: write it into service of entity
    public  void uploadImage(*//*parameters*//*) {
        final String cloudinaryImageId = entity.getCloudinaryImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.delete(cloudinaryImageId);
        }
    }
    */

}
