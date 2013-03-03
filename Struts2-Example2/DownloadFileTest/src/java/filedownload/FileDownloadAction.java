package filedownload;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

/**
 * Demonstrates file resource download.
 * Set filePath to the local file resource to download,
 * relative to the application root ("/images/struts.gif").
 *
 */
public class FileDownloadAction implements Action {

    private String inputPath;
    public void setInputPath(String value) {
        inputPath = value;
    }

    public InputStream getInputStream() throws Exception {
        return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

}
