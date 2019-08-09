package com.artfactory.project01.todayart.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;

public class PrincipalUtil {
    public static UserDetails from(Principal principal){
        UserDetails memberDetails = null;
        try{
            UsernamePasswordAuthenticationToken token = null;
            if(principal instanceof OAuth2Authentication) {
                token = (UsernamePasswordAuthenticationToken)
                        ((OAuth2Authentication) principal).getUserAuthentication();
            }else if(principal instanceof UsernamePasswordAuthenticationToken){
                token = (UsernamePasswordAuthenticationToken) principal;

            }
            memberDetails = (UserDetails) token.getPrincipal();
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
        return memberDetails;
    }
}
