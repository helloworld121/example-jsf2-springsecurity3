package org.helloworld.example.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
     * TODO switch to real SPel {@link org.springframework.security.access.expression.method.MethodSecurityExpressionRoot}
     *
     * @param ctx
     * @param parent
     * @throws IOException
     */
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return;
		}

        String hasRoleKey = "hasRole";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // In the moment only hasRole is supported
        if(access.getValue() == null || !access.getValue().startsWith(hasRoleKey)) {
            log.error("In the moment only hasRole is supported. Current value = '" + access.getValue() + "'");
            throw new IllegalArgumentException("In the moment only hasRole is supported. Current value = '" + access.getValue() + "'");
        }

        String accessValue = access.getValue().substring(hasRoleKey.length());

        boolean isAuthorized = false;
        if(accessValue.substring(0, 2).equals("('")
                && accessValue.substring(accessValue.length()-2, accessValue.length()).equals("')")) {
            accessValue = accessValue.substring(2, accessValue.length()-2);

            isAuthorized = existGroup(authentication, accessValue);

        } else {
            log.error("Expected pattern is '('*')'. But found: '" + accessValue + "'");
            throw new IllegalArgumentException("Expected pattern is '(*)'. But found: '" + accessValue + "'");
        }


//		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag(faceletContext, access, url, method, ifAllGranted,
//				ifAnyGranted, ifNotGranted);
//		boolean isAuthorized = authorizeTag.authorize();

		if (isAuthorized) {
			this.nextHandler.apply(ctx, parent);
		}

//		if (this.var != null) {
//			faceletContext.setAttribute(var.getValue(faceletContext), Boolean.valueOf(isAuthorized));
//		}
    }



    private boolean existGroup(Authentication auth, String role) {
        for(GrantedAuthority authroity : auth.getAuthorities()) {
            if(authroity.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }


}
