package springredis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.Set;

@Controller  
public class RedisTestController {
		 
/*	public void ssss(){
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring-servlet.xml");  
        //这里已经配置好,属于一个redis的服务接口  
        redisService = (RedisService) app.getBean("redisService");  
	}*/
	@Resource
	RedisService redisService;
	
	@RequestMapping("/springredis.do")
	@ResponseBody
    public void testredis(PrintWriter pw) throws InterruptedException {
		Thread.sleep(20000);
		String ping = redisService.ping();//测试是否连接成功,连接成功输出PONG  
        if(ping.equals("PONG")){
        	pw.println("connection ok !");
        	 //首先,我们看下redis服务里是否有数据  
            long dbSizeStart = redisService.dbSize();  
            pw.println("start dbSize is "+dbSizeStart);  
      
            redisService.set("key-0", "key-0-value");//设值(查看了源代码,默认存活时间30分钟)  
            String getkey1value = redisService.get("key-0");//取值   
            pw.println("set key-0,then get value : "+getkey1value);  
            redisService.getJedis().append("key-0", "xxxx");
            pw.println("key-0 append,then get value : "+getkey1value);  

            //是否存在  
            boolean exist = redisService.exists("key-0");  
            pw.println("key-0 exist: "+exist);  
      
            redisService.getJedis().mset("key-1","key-1-value","key-2","key-2-value","key-3","key-3-value");
                  
            //查看keys  
            Set<String> keys = redisService.keys("*");//这里查看所有的keys  
            pw.println("all keys :"+keys);
            
            //dbsize  
            long dbSizeEnd = redisService.dbSize();  
            pw.println("now dbSize is "+dbSizeEnd);  
            
            redisService.del("key-3");  
            String getkey2value = redisService.get("key-3"); 
            if (getkey2value ==null){
            	pw.println("set key-3,then del ok");//如果为null,那么就是删除数据了
                long dbSize = redisService.dbSize();  
                pw.println("now dbSize is "+dbSize);  
            }else
            	pw.println("del failed!");//如果为null,那么就是删除数据了  
      
           //清空reids所有数据  
            redisService.flushDB(); 
            pw.println("flushDB !!!");  
            //

        }else
        	 pw.println(" connection failed !!!");  
       
    }  
}  