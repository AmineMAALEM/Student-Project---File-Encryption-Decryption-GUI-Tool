/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRYPTO_System_APP;

/**
 *
 * @author Amine
 */


import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptor {
    private static final String ALGORITHM = "AES";
    private static final byte[] AES128_KEY = "MySuperSecretKey".getBytes(); // 16 bytes for AES-128
    private static final byte[] AES256_KEY = "MySuperDuperSecretKeyMySuperDupe".getBytes(); // 32 bytes for AES-256
    private static final byte[] DES_KEY = "MySecret".getBytes(); // 8 for des
    
    public void encrypt(File inputFile, File outputFile, String algorithm) throws Exception {
        Key secretKey;
        Cipher cipher;

        switch (algorithm) {
            case "AES128":
                secretKey = new SecretKeySpec(AES128_KEY, "AES");
                cipher = Cipher.getInstance("AES");
                break;
            case "AES256":
                secretKey = new SecretKeySpec(AES256_KEY, "AES");
                cipher = Cipher.getInstance("AES");
                break;
            case "DES":
                secretKey = new SecretKeySpec(DES_KEY, "DES");
                cipher = Cipher.getInstance("DES");
                break;
                /*
            case "RSA1024":
            case "RSA2048":
            case "RSA4096":
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(Integer.parseInt(algorithm.substring(3))); // Utilisez 1024, 2048 ou 4096 pour RSA1024, RSA2048 ou RSA4096
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                secretKey = keyPair.getPublic();
                cipher = Cipher.getInstance("RSA");
                break;
                */
            default:
                throw new IllegalArgumentException("Algorithme non supporté: " + algorithm);
        }

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        byte[] outputBytes = cipher.doFinal(inputBytes);

        Files.write(outputFile.toPath(), outputBytes);
    }
    
    public void decrypt(File inputFile, File outputFile, String algorithm) throws Exception {
        Key secretKey;
        Cipher cipher;

        switch (algorithm) {
            case "AES128":
                secretKey = new SecretKeySpec(AES128_KEY, "AES");
                cipher = Cipher.getInstance("AES");
                break;
            case "AES256":
                secretKey = new SecretKeySpec(AES256_KEY, "AES");
                cipher = Cipher.getInstance("AES");
                break;
            case "DES":
                secretKey = new SecretKeySpec(DES_KEY, "DES");
                cipher = Cipher.getInstance("DES");
                break;
            default:
                throw new IllegalArgumentException("Algorithme non supporté: " + algorithm);
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
        byte[] outputBytes = cipher.doFinal(inputBytes);

        Files.write(outputFile.toPath(), outputBytes);
    }

    
    
}

