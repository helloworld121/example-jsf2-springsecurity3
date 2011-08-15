package org.helloworld.example.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="UserAdministratorBean")
@SessionScoped
public class UserAdministratorBean {

    private Log log = LogFactory.getLog(getClass());

    private String oldPassword;
    private String newPassword = "change password";


    public UserAdministratorBean() {
        log.info("Initializing Bean: UserAdministratorBean");
    }


    public String submitChangePasswordPage() {
        log.info("Accessing submitChangePasswordPage");

        log.info("New Password: " + newPassword);
        return "/login.xhtml";
    }


    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
