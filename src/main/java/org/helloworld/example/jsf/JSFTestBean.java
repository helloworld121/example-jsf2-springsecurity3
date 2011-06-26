package org.helloworld.example.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="JSFTestBean")
@SessionScoped
public class JSFTestBean {

    private Log log = LogFactory.getLog(getClass());

    private String someOutput;

    public JSFTestBean() {
        someOutput = "MY TMP SOMETHING";
    }

    public String getSomeOutput() {
        return someOutput;
    }

    public void setSomeOutput(String someOutput) {
        this.someOutput = someOutput;
    }


    public String gotoHome() {
        log.info("Calling: gotoHome");
        return "home";
    }

    
}
