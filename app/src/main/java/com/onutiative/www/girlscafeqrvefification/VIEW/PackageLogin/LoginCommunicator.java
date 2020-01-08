package com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin;

public interface LoginCommunicator {
    public interface LoginView{
        public void loginSuccessFull(String message);
        public void loginFailed(String message);
    }
    public interface LoginViewPresenter{
        public void loginHandler(String username,String password);
    }
}
