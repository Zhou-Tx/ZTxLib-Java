package cn.taizhou0523.encrypt.hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA1 implements Hash {

    private MessageDigest sha1 = null;

    SHA1() {
        try {
            sha1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    SHA1(String string) {
        this();
        update(string);
    }

    @Override
    public void reset() {
        sha1.reset();
    }

    @Override
    public void set(String string) {
        reset();
        update(string);
    }

    @Override
    public void update(String string) {
        sha1.update(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return new BigInteger(1, sha1.digest()).toString(16);
    }
}
