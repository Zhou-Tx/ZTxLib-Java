package cn.taizhou0523.encrypt.symmetric;

import cn.taizhou0523.encrypt.Algorithms;

import java.security.NoSuchAlgorithmException;

public interface Symmetric {

    void setKey(String key) throws NoSuchAlgorithmException;

    byte[] encrypt(byte[] content) throws Exception;

    byte[] decrypt(byte[] content) throws Exception;

    String encrypt(String content) throws Exception;

    String decrypt(String content) throws Exception;

    /**
     * @param algorithm 算法名称
     * @param key 加解密密钥
     * @return 算法实例
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    static Symmetric getInstance(Algorithms algorithm) throws NoSuchAlgorithmException {
        switch (algorithm) {
            case AES:
                return new RSA();
            default:
                throw new NoSuchAlgorithmException();
        }
    }
}
