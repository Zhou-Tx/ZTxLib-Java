package cn.taizhou0523.encrypt;

public interface Base64 {

    static String encode(byte[] content) {
        return java.util.Base64.getEncoder().encodeToString(content);
    }

    static byte[] decode(String string) {
        return java.util.Base64.getDecoder().decode(string);
    }
}
