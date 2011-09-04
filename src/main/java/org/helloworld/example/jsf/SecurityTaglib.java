package org.helloworld.example.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.util.SimpleMethodInvocation;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import java.io.IOException;


public class SecurityTaglib
        extends TagHandler {

    private Log log = LogFactory.getLog(getClass());

    private final TagAttribute access;



    public SecurityTaglib(TagConfig config) {
		super(config);
		this.access = this.getAttribute("access");
	}


    /**
     * @param ctx
     * @param parent
     * @throws IOException
     */
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return;
		}

        // it should also be possible to switch to the bean "expressionVoter"


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // method handler doesn't work
        /*
        */
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        EvaluationContext expCtx = expressionHandler.createEvaluationContext(authentication, new SimpleMethodInvocation());


        // web expression handler
//        FilterInvocation fi = new FilterInvocation(getRequest(ctx), getResponse(ctx), new FilterChain());
        // this is just to keep it simple.
        // if you want "hasIpAddress" implement the above
        /*
        FilterInvocation fi = new FilterInvocation("contextPath", "servletPath", "pathInfo", "query", "method");
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        EvaluationContext expCtx = expressionHandler.createEvaluationContext(authentication, fi);
        */

        log.info("Expression-Value: " + access.getValue());
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("hasRole('ROLE_USER')");

        boolean isAuthorized =  ExpressionUtils.evaluateAsBoolean(expression, expCtx);
        log.info("isAuthorized: " + isAuthorized);

        if (isAuthorized) {
			this.nextHandler.apply(ctx, parent);
		}
    }

//    private ServletRequest getRequest(FaceletContext ctx) {
//		return (ServletRequest) ctx.getFacesContext().getExternalContext().getRequest();
//	}
//
//	private ServletResponse getResponse(FaceletContext ctx) {
//		return (ServletResponse) ctx.getFacesContext().getExternalContext().getResponse();
//	}



}
