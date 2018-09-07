package com.codeformas.miranda_server.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Juan {
    public static void main(String args[]){
        String password = "H94BgwFuMXeMZuF7orlAFg==";
        System.out.println("==============>" + decrypt(password));
    }

    public static String encrypt(String clave){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("segst");
        return encryptor.encrypt(clave);
    }

    //descriptar clave
    public static String decrypt(String clave){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("segst");
        return encryptor.decrypt(clave);
    }
}
