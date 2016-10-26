package rpc.dubbodemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ProviderMain {

        public static void main(String[] args) throws Exception {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    new String[] { "dubbo-provider.xml" });
            context.start();
            System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
            com.alibaba.dubbo.container.Main.main(args);
        }
    }

