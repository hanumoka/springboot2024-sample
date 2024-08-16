package org.hanumoka.sample.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 클래스 설명란
 *
 * @author : KYB
 * @fileName : TestController
 * @since : 24. 8. 16.
 */
@RestController
public class TestController {

    private final ResourceLoader resourceLoader;

    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Operation(summary = "Get mixed content with keys", description = "Returns a multipart mixed response containing JSON data and files")
    @ApiResponse(responseCode = "200", description = "Successful response",
            content = @Content(mediaType = "multipart/mixed",
                    schema = @Schema(implementation = MixedContentResponse.class)))
    @GetMapping("/mixed-content-with-keys")
    public ResponseEntity<?> getMixedContentWithKeys() {
        // JSON 데이터 생성
        MyDto dto = new MyDto("example data");

        // 파일 정보와 키값을 담은 Map 생성
        Map<String, FileInfo> fileInfoMap = new LinkedHashMap<>();
        fileInfoMap.put("A1", new FileInfo("file/file1.pdf", "file1.pdf"));
        fileInfoMap.put("B1", new FileInfo("file/file2.jpg", "file2.png"));

        // Multipart 응답 구성
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        // JSON 파트 추가
        builder.part("json", dto).contentType(MediaType.APPLICATION_JSON);

        // 파일 파트와 메타데이터 추가
        for (Map.Entry<String, FileInfo> entry : fileInfoMap.entrySet()) {
            String key = entry.getKey();
            FileInfo fileInfo = entry.getValue();

            try {
                // 리소스 로드
                Resource fileResource = resourceLoader.getResource("classpath:file/" + fileInfo.getFilename());

                if (!fileResource.exists()) {
                    throw new IOException("File not found: " + fileInfo.getFilename());
                }

                // 파일 메타데이터 JSON 생성
                Map<String, Object> metadata = new LinkedHashMap<>();
                metadata.put("key", key);
                metadata.put("filename", fileInfo.getFilename());

                // 메타데이터 파트 추가
                builder.part(key + "-metadata", metadata).contentType(MediaType.APPLICATION_JSON);

                // 파일 파트 추가
                builder.part(key + "-file", fileResource).filename(fileInfo.getFilename());
            } catch (IOException e) {
                // 로그 기록
                System.err.println("Error loading file: " + e.getMessage());
                // 에러 처리 로직 추가 (예: 해당 파일 스킵 또는 에러 응답)
            }
        }

        // ResponseEntity 구성 및 반환
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_MIXED)
                .body(builder.build());
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

    // getters and setters
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

    // getters and setters
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

class MyDto {
    private String data;

    public MyDto(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class FileInfo {
    private String path;
    private String filename;

    public FileInfo(String path, String filename) {
        this.path = path;
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }
}