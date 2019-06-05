package club.eugeneliu.trade.db;

public class TestBoolean {
    boolean a;
    static boolean b;
    public static void main(String[] args) {

        System.out.println(new TestBoolean().a);//boolean默认为false
        System.out.println(TestBoolean.b);
//        boolean c;
//        System.out.println(c);//Error 必须先将其初始化
    }

    static void test(){
//        boolean d;
//        System.out.println(d);//Error 如果想要使用静态方法中创建的boolean类型,必须要先将其初始化
    }

    void test2(){
//        boolean e;
//        System.out.println(e);
    }
}
