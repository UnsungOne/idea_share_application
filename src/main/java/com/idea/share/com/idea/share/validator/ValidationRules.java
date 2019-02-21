package com.idea.share.com.idea.share.validator;


public class ValidationRules {

    public final static String IDEA_PATTERN = "[0-9A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*";
    public final static String PASSSWORD_LENGTH = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public final static String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
}
