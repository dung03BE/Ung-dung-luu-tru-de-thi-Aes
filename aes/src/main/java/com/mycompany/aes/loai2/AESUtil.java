/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aes.loai2;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author dungi
 */
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "mysecretkey12345"; // Key phải dài 16 ký tự

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] original = cipher.doFinal(decodedBytes);
            return new String(original);
        } catch (IllegalArgumentException e) {
            System.err.println("Decryption error: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            String originalData = "This is a test.";
            String encryptedData = encrypt(originalData);
            String decryptedData = decrypt(encryptedData);
            System.out.println("Original Data: " + originalData);
            System.out.println("Encrypted Data: " + encryptedData);
            System.out.println("Decrypted Data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
