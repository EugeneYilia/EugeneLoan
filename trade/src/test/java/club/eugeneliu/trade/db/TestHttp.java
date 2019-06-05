package club.eugeneliu.trade.db;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHttp {
    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
