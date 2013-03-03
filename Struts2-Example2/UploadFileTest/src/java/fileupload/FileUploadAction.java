/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileupload;

/**
 *
 * @author Administrator
 */
import java.io.File;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.commons.io.FileUtils;
/**
 * Show case File Upload example's action. <code>FileUploadAction</code>
 *
 */
public class FileUploadAction extends ActionSupport implements ServletRequestAware {

    private String contentType;
    private File upload;
    private String fileName;
    private HttpServletRequest servletRequest;

    @Override
    public String execute() {
        try {
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
			System.out.println("Server path:" + filePath);
            File fileToCreate = new File(filePath/*+"myfile"*/, this.fileName);

            FileUtils.copyFile(this.upload, fileToCreate);
        } catch (Exception e) {
            addActionError(e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

    // since we are using <s:file name="upload" .../> the file name will be
    // obtained through getter/setter of <file-tag-name>FileName
    public String getUploadFileName() {
        return fileName;
    }

    public void setUploadFileName(String fileName) {
        this.fileName = fileName;
    }

    // since we are using <s:file name="upload" ... /> the content type will be
    // obtained through getter/setter of <file-tag-name>ContentType
    public String getUploadContentType() {
        return contentType;
    }

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    // since we are using <s:file name="upload" ... /> the File itself will be
    // obtained through getter/setter of <file-tag-name>
    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String input() throws Exception {
        return SUCCESS;
    }
}
