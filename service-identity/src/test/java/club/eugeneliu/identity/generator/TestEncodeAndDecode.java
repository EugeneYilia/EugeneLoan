package club.eugeneliu.identity.generator;

import club.eugeneliu.identity.utils.CertificationUtil;

import java.io.IOException;

public class TestEncodeAndDecode {
    public static void main(String[] args) throws IOException {
        String message = "EugeneLiu12345";
        String encodeMessage = CertificationUtil.encode(message);
        System.out.println(encodeMessage);
        String decodedMessage = CertificationUtil.decode(encodeMessage);
        System.out.println(decodedMessage);
    }
}
