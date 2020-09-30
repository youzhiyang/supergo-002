/**
 * Created by Administrator on 2020/7/15 0015.
 */
public class StringTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String str = "http://windows10.microdone.cn:9004/html/build/149187842868044";
        int index = str.lastIndexOf(":");
        System.out.println("index:   " + index);
        String str1 = str.substring(0,index);
        String str2 = str.substring(index);
        System.out.println(str1);
        System.out.println(str2);
        String[] strArry = str2.split("/");
        String str3 = str1 + strArry[0];
        System.out.println(str3);
    }
}
