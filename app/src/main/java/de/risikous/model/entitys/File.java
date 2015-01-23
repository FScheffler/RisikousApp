package de.risikous.model.entitys;

/**
 * Created by Franz on 08.01.2015.
 */
public class File {
    String fileName;
    String fileEncoding;
    String fileContent;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileEncoding() {
        return fileEncoding;
    }
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }
    public String getFileContent() {
        return fileContent;
    }
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
