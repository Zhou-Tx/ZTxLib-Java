package cn.taizhou0523.encrypt;

import java.security.NoSuchAlgorithmException;

public interface Symmetric {

    byte[] encrypt(byte[] content) throws Exception;

    byte[] decrypt(byte[] content) throws Exception;

    String encrypt(String content) throws Exception;

    String decrypt(String content) throws Exception;

    /**
     * @param algorithm 算法名称
     * @param key       加解密密钥
     * @return 算法实例
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    static Symmetric getInstance(String algorithm, String key) throws NoSuchAlgorithmException {
        switch (algorithm.toUpperCase()) {
            case "AES":
                return new AES(key);
            case "":
                return null;
            default:
                throw new NoSuchAlgorithmException();
        }
    }
}
