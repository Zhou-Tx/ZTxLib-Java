package cn.taizhou0523.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Asymmetric {

    byte[] getPublicKey();

    byte[] getPrivateKey();

    void setPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void setPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void setPublicKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void setPrivateKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException;

    byte[] encrypt(byte[] content) throws Exception;

    byte[] decrypt(byte[] content) throws Exception;

    String encrypt(String content) throws Exception;

    String decrypt(String content) throws Exception;

    /**
     * @param algorithm 算法名称
     * @return 算法实例
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    static Asymmetric getInstance(String algorithm) throws NoSuchAlgorithmException {
        switch (algorithm.toUpperCase()) {
            case "RSA":
                return new RSA();
            case "":
                return null;
            default:
                throw new NoSuchAlgorithmException();
        }
    }
}