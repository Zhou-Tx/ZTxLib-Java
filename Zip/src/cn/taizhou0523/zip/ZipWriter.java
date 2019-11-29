package cn.taizhou0523.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class ZipWriter implements Closeable {
    private boolean isClosed = false;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

    @Override
    public void close() throws IOException {
        zipOutputStream.close();
        byteArrayOutputStream.close();
        isClosed = true;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() throws IOException {
        if (isClosed)
            return byteArrayOutputStream;
        else
            throw new IOException("Expected EOF");
    }

    public void add(InputStream inputStream, String zipEntry) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(zipEntry));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        int tag;
        while ((tag = inputStream.read()) != -1) {
            bufferedOutputStream.write(tag);
        }
        inputStream.close();
    }

    public void add(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
        int tag;
        while ((tag = inputStream.read()) != -1) {
            bufferedOutputStream.write(tag);
        }
        inputStream.close();
    }
}
