package com.aditya.wishnetlogin.prefs;

/**
 * Created by Aditya on 10/12/2016.
 */
public class LoginSelectionDTO {

    private String userName;

    private String password;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isValid(){


        if(userName==null || userName.trim().length()==0){
            return  false;
        }

        if(password==null || password.trim().length()==0){
            return  false;
        }


        return true;



    }
}
