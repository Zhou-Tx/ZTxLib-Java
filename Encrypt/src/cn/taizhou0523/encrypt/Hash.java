package cn.taizhou0523.encrypt;

import java.security.NoSuchAlgorithmException;

public interface Hash {

    void reset();

    void set(String string);

    void update(String string);

    @Override
    String toString();

    static Hash getInstance(String algorithm) throws NoSuchAlgorithmException {
        switch (algorithm.toUpperCase()) {
            case "MD5":
                return new MD5();
            case "SHA1":
                return new SHA1();
            default:
                throw new NoSuchAlgorithmException();
        }
    }

    static String md5(String string) {
        return new MD5(string).toString();
    }

    static String sha1(String string) {
        return new SHA1(string).toString();
    }
}
