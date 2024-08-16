package org.hanumoka.sample.test;

/**
 * 클래스 설명란
 *
 * @fileName : TestController
 * @author : KYB
 * @since : 24. 8. 16.
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final ResourceLoader resourceLoader;

    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Operation(summary = "Post mixed content with keys", description = "Accepts JSON data and a list of files, returns a multipart mixed response")
    @ApiResponse(responseCode = "200", description = "Successful response",
            content = @Content(mediaType = "multipart/mixed",
                    schema = @Schema(implementation = MixedContentResponse.class)))
    @PostMapping(value = "/mixed-content-with-keys", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postMixedContentWithKeys(
            @Parameter(description = "JSON data", required = true)
            @RequestPart("json") MyDto dto,
            @Parameter(description = "List of files", required = false)
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        // Multipart 응답 구성
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        // JSON 파트 추가
        builder.part("json", dto).contentType(MediaType.APPLICATION_JSON);

        if(files == null || files.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.MULTIPART_MIXED)
                    .body(builder.build());
        }

        Resource resource = resourceLoader.getResource("classpath:file/");
        String uploadDir;
        try {
            uploadDir = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to get upload directory");
        }

        // 파일 파트와 메타데이터 추가
        for (int i = 0; i < files.size() && i < dto.getFileKeys().size(); i++) {
            String key = dto.getFileKeys().get(i);
            MultipartFile file = files.get(i);
            try {
                String filename = key + getFileExtension(file.getOriginalFilename());
                Path filePath = Paths.get(uploadDir, filename);

                System.out.println(filePath);

                // 기존 파일이 있다면 삭제
                Files.deleteIfExists(filePath);

                // 새 파일 저장
                file.transferTo(filePath.toFile());

                addFilePart(builder, key, filePath.toFile());
            } catch (IOException e) {
                // 에러 로깅
                System.err.println("Error saving file: " + e.getMessage());
                // 여기서는 에러를 무시하고 계속 진행합니다.
            }
        }

        // ResponseEntity 구성 및 반환
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_MIXED)
                .body(builder.build());
    }


    private void addFilePart(MultipartBodyBuilder builder, String key, File file) {
        // 파일 메타데이터 JSON 생성
        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("key", key);
        metadata.put("filename", file.getName());

        // 메타데이터 파트 추가
        builder.part(key + "-metadata", metadata).contentType(MediaType.APPLICATION_JSON);

        // 파일 파트 추가
        builder.part(key + "-file", file).filename(file.getName());
    }


    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}

@Schema(description = "Data transfer object for mixed content request")
class MyDto {
    @Schema(description = "Example data field")
    private String data;

    @Schema(description = "List of file keys corresponding to uploaded files")
    private List<String> fileKeys;

    // 기본 생성자
    public MyDto() {
    }

    // 모든 필드를 포함한 생성자
    public MyDto(String data, List<String> fileKeys) {
        this.data = data;
        this.fileKeys = fileKeys;
    }

    // Getters and setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getFileKeys() {
        return fileKeys;
    }

    public void setFileKeys(List<String> fileKeys) {
        this.fileKeys = fileKeys;
    }
}

@Schema(description = "Mixed content response")
class MixedContentResponse {
    @Schema(description = "JSON part of the response")
    private MyDto json;

    @Schema(description = "File metadata")
    private Map<String, FileMetadata> fileMetadata;

    @Schema(description = "File contents")
    private Map<String, Resource> fileContents;

    // 기본 생성자
    public MixedContentResponse() {
    }

    // 모든 필드를 포함한 생성자
    public MixedContentResponse(MyDto json, Map<String, FileMetadata> fileMetadata, Map<String, Resource> fileContents) {
        this.json = json;
        this.fileMetadata = fileMetadata;
        this.fileContents = fileContents;
    }

    // Getters and setters
    public MyDto getJson() {
        return json;
    }

    public void setJson(MyDto json) {
        this.json = json;
    }

    public Map<String, FileMetadata> getFileMetadata() {
        return fileMetadata;
    }

    public void setFileMetadata(Map<String, FileMetadata> fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public Map<String, Resource> getFileContents() {
        return fileContents;
    }

    public void setFileContents(Map<String, Resource> fileContents) {
        this.fileContents = fileContents;
    }
}

@Schema(description = "File metadata")
class FileMetadata {
    @Schema(description = "File key")
    private String key;

    @Schema(description = "File name")
    private String filename;

    // 기본 생성자
    public FileMetadata() {
    }

    // 모든 필드를 포함한 생성자
    public FileMetadata(String key, String filename) {
        this.key = key;
        this.filename = filename;
    }

    // Getters and setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

