import java.time.ZonedDateTime;

public class Test1 {

    public static void main(String[] args) {
        ZonedDateTime zbj=ZonedDateTime.now();//默认时区
        System.out.println(zbj);

        //2021-04-03T20:06:29.724+08:00[Asia/Shanghai]
    }
}
