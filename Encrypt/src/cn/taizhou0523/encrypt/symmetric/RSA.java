package cn.taizhou0523.encrypt.symmetric;

import cn.taizhou0523.encrypt.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称密钥加密算法 AES
 */
class RSA implements Symmetric {

    // 加解密密钥
    private SecretKeySpec key = null;

    /**
     * @param key 密钥值
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    @Override
    public void setKey(String key) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        this.key = new SecretKeySpec(enCodeFormat, "AES");
    }

    /**
     * @param content 明文字节数组
     * @return 密文字节数组
     */
    @Override
    public byte[] encrypt(byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(content);
    }

    /**
     * @param content 密文字节数组
     * @return 明文字节数组
     */
    @Override
    public byte[] decrypt(byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(content);
    }

    /**
     * @param content 明文字节串
     * @return 密文字节串
     */
    @Override
    public String encrypt(String content) throws Exception {
        byte[] bytes = encrypt(content.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(bytes);
    }

    /**
     * @param content 密文字节串
     * @return 明文字节串
     */
    @Override
    public String decrypt(String content) throws Exception {
        byte[] bytes = Base64.decode(content);
        return new String(decrypt(bytes), StandardCharsets.UTF_8);
    }
}
