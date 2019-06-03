package club.eugeneliu.trade.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class CertificationUtil {
    private CertificationUtil(){}

    public static String encode(String originalString){
        String encodeMessage = new BASE64Encoder().encodeBuffer(originalString.getBytes());
        encodeMessage = encodeMessage.substring(0,encodeMessage.length()-1);
        return encodeMessage;
    }

    public static String decode(String encodedString) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(encodedString));
    }
}
