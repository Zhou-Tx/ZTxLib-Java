package cn.taizhou0523.encrypt.hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA256 implements Hash {

    private MessageDigest sha256 = null;

    SHA256() {
        try {
            sha256 = MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    SHA256(String string) {
        this();
        update(string);
    }

    @Override
    public void reset() {
        sha256.reset();
    }

    @Override
    public void set(String string) {
        reset();
        update(string);
    }

    @Override
    public void update(String string) {
        sha256.update(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return new BigInteger(1, sha256.digest()).toString(16);
    }
}
