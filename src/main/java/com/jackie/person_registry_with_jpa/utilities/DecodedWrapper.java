package com.jackie.person_registry_with_jpa.utilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class DecodedWrapper implements MultipartFile {

    private byte[] decoded;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return decoded == null || decoded.length == 0;
    }

    @Override
    public long getSize() {
        return decoded.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return decoded;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(decoded);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(decoded);
    }

    public byte[] getDecoded() {
        return decoded;
    }

    public void setDecoded(byte[] decoded) {
        this.decoded = decoded;
    }
}
