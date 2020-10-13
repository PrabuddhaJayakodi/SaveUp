package com.myproject.myapplication3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean is_Validmail(final String email)
    {
        String StringTosearch = email;

        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = p.matcher(StringTosearch);


        if (m.find())
            return true;
        else
            return false;
    }

    public static boolean is_password_same(String password,String repassword)
    {
        if (password.equals(repassword))
            return true;
        else
            return false;
    }
}
