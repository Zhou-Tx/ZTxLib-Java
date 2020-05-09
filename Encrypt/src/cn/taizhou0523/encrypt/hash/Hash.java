package cn.taizhou0523.encrypt.hash;

import cn.taizhou0523.encrypt.Algorithms;

import java.security.NoSuchAlgorithmException;

public interface Hash {

    void reset();

    void set(String string);

    void update(String string);

    @Override
    String toString();

    static Hash getInstance(Algorithms algorithm) throws NoSuchAlgorithmException {
        switch (algorithm) {
            case MD5:
                return new MD5();
            case SHA1:
                return new SHA1();
            case SHA256:
                return new SHA256();
            case SHA512:
                return new SHA512();
            default:
                throw new NoSuchAlgorithmException();
        }
    }

    static String md5(String value) {
        return new MD5(value).toString();
    }

    static String sha1(String value) {
        return new SHA1(value).toString();
    }

    static String sha256(String value) {
        return new SHA256(value).toString();
    }

    static String sha512(String value) {
        return new SHA512(value).toString();
    }
}
