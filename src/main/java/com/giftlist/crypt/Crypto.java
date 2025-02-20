package com.giftlist.crypt;

public interface Crypto {
    void prepareSecreteKey(String myKey);
    String encrypt(String strToEncrypt, String secret);
    String decrypt(String strToDecrypt, String secret);
}
