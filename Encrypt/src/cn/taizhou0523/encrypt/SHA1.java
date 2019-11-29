package cn.taizhou0523.encrypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA1 implements Hash {

    private MessageDigest md5 = null;

    SHA1() {
        try {
            md5 = MessageDigest.getInstance("SHA1");
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
        md5.reset();
    }

    @Override
    public void set(String string) {
        reset();
        update(string);
    }

    @Override
    public void update(String string) {
        md5.update(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return new BigInteger(1, md5.digest()).toString(16);
    }
}
