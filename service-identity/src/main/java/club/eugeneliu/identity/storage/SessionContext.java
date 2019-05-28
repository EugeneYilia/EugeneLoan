package club.eugeneliu.identity.storage;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionContext {
    private SessionContext(){}

    private static final HashMap<String, HttpSession> sessionContext = new HashMap<>();

    public static HttpSession searchHttpSession(String sessionId){
        return sessionContext.get(sessionId);
    }

    public void addHttpSession(String sessionId,HttpSession httpSession){
        sessionContext.put(sessionId,httpSession);
    }
}
