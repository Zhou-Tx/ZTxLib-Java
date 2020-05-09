package cn.taizhou0523.encrypt.hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA512 implements Hash {

    private MessageDigest sha512 = null;

    SHA512() {
        try {
            sha512 = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    SHA512(String string) {
        this();
        update(string);
    }

    @Override
    public void reset() {
        sha512.reset();
    }

    @Override
    public void set(String string) {
        reset();
        update(string);
    }

    @Override
    public void update(String string) {
        sha512.update(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return new BigInteger(1, sha512.digest()).toString(16);
    }
}
