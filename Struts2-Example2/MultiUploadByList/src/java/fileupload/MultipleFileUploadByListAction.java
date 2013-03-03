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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;

public class MultipleFileUploadByListAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest servletRequest;
    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();

    public List<File> getUpload() {
        return this.uploads;
    }

    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileName() {
        return this.uploadFileNames;
    }

    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<String> getUploadContentType() {
        return this.uploadContentTypes;
    }

    public void setUploadContentType(List<String> contentTypes) {
        this.uploadContentTypes = contentTypes;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String upload() throws Exception {

        for (int i = 0; i < uploads.size(); i++) {
            try {
                String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                System.out.println("Server path:" + filePath);
                File fileToCreate = new File(filePath + "MyUploadedFile", uploadFileNames.get(i));

                FileUtils.copyFile(uploads.get(i), fileToCreate);
            } catch (Exception e) {
                addActionError(e.getMessage());
                return INPUT;
            }
        }
        return SUCCESS;
    }
}
