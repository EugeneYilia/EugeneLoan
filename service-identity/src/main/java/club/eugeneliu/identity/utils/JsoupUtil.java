package club.eugeneliu.identity.utils;//package club.eugeneliu.gateway.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupUtil {
    private static final Whitelist whiteList = new Whitelist().basicWithImages();
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        whiteList.addAttributes(":all","style");
    }

    public static String clean(String content){
        if(StringUtils.isNotBlank(content)){
            content = content.trim();
        }
        return Jsoup.clean(content,"",whiteList,outputSettings);
    }
}
