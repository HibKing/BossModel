package com.example.myapplication.account;

public class UserManager {
    private static volatile UserManager sInstance;
    private String mUser;
    private String mPassword;
    private String mUserNickName;

    public static UserManager getInstance() {
        if (sInstance == null) {
            synchronized (UserManager.class) {
                if (sInstance == null) {
                    sInstance = new UserManager();
                }
            }
        }
        return sInstance;
    }

    public UserManager(){
        mUser="a36403692";
        mPassword="1370694";
        mUserNickName="王壮壮";
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String mUser) {
        this.mUser = mUser;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getUserNickName() {
        return mUserNickName;
    }

    public void setUserNickName(String mUserNickName) {
        this.mUserNickName = mUserNickName;
    }
}
