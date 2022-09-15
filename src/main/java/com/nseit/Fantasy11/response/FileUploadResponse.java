package com.nseit.Fantasy11.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse extends APIResponse {
    private String fileName;
    private String downloadUri;
    private long size;
}