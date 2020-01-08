package cn.taizhou0523.encrypt.asymmetric;

import cn.taizhou0523.encrypt.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称密钥加密算法 RSA
 */
class RSA implements Asymmetric {

    // 加密公钥
    private PublicKey publicKey;
    // 解密私钥
    private PrivateKey privateKey;

    RSA() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    @Override
    public byte[] getPublicKey_bytes() {
        return publicKey.getEncoded();
    }

    @Override
    public byte[] getPrivateKey_bytes() {
        return privateKey.getEncoded();
    }

    @Override
    public String getPublicKey() {
        return Base64.encode(getPublicKey_bytes());
    }

    @Override
    public String getPrivateKey() {
        return Base64.encode(getPrivateKey_bytes());
    }

    @Override
    public void setPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
        setPublicKey(Base64.decode(key));
    }

    @Override
    public void setPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        setPrivateKey(Base64.decode(key));
    }

    @Override
    public void setPublicKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
    }

    @Override
    public void setPrivateKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(keySpec);
    }

    /**
     * @param content 明文字节数组
     * @return 密文字节数组
     */
    @Override
    public byte[] encrypt(byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    /**
     * @param content 密文字节数组
     * @return 明文字节数组
     */
    @Override
    public byte[] decrypt(byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
