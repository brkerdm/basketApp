

package com.godoro.library;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


public class FacesUtilities {
    
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getRequest();
    }
    
    public static long getParameter(String parameterName, long defaultValue) {
        String parameterValue = getRequest().getParameter(parameterName);
        if (parameterValue != null) {
            return Long.parseLong(parameterValue);
        } else {
            return defaultValue;
        }
    }

}
