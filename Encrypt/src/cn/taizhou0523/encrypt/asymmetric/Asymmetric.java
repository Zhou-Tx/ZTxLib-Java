package cn.taizhou0523.encrypt.asymmetric;

import cn.taizhou0523.encrypt.Algorithms;

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
     * @param algorithms 算法名称
     * @return 算法实例
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    static Asymmetric getInstance(Algorithms algorithm) throws NoSuchAlgorithmException {
        switch (algorithm) {
            case RSA:
                return new RSA();
            default:
                throw new NoSuchAlgorithmException();
        }
    }
}
