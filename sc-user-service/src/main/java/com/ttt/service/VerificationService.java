package com.ttt.service;


import com.ttt.http.Result;



public interface VerificationService{

    String generateCode();

    Result sendVerificationEmail(String email);

    boolean verifyCode(String email, String code);

}