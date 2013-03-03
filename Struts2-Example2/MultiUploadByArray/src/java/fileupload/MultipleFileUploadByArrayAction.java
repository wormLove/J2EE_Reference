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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;

public class MultipleFileUploadByArrayAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest servletRequest;
    private File[] uploads;
    private String[] uploadFileNames;
    private String[] uploadContentTypes;

    public File[] getUpload() { return this.uploads; }
    public void setUpload(File[] upload) { this.uploads = upload; }

    public String[] getUploadFileName() { return this.uploadFileNames; }
    public void setUploadFileName(String[] uploadFileName) { this.uploadFileNames = uploadFileName; }

    public String[] getUploadContentType() { return this.uploadContentTypes; }
    public void setUploadContentType(String[] uploadContentType) { this.uploadContentTypes = uploadContentType; }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String upload() throws Exception {

        for (int i = 0; i < uploads.length; i++) {
            try {
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println("Server path:" + filePath);
                File fileToCreate = new File(filePath + "MyUploadedFile1", uploadFileNames[i]);

                FileUtils.copyFile(uploads[i], fileToCreate);
            } catch (Exception e) {
                addActionError(e.getMessage());
                return INPUT;
            }
        }
        return SUCCESS;
    }
}
