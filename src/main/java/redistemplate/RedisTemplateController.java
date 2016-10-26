package redistemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redistemplate.dao.UserDAO;
import redistemplate.obj.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/7/26.
 */
@Controller
public class RedisTemplateController {
    @Autowired
    private UserDAO userDAO;
    @RequestMapping("/redistemplate.do")
    @ResponseBody
    public String  redistemplate(){
        //ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/spring-servlet.xml");
        //  UserDAO userDAO = (UserDAO)ac.getBean("userDAO");
        User user1 = new User();
        user1.setId(1);
        user1.setName("set name redis");
        userDAO.saveUser(user1);
        User user2 = userDAO.getUser(1);
        System.out.println(user2.getName());
        return user2.getName();
    }
    public String  xx(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        UserDAO userDAO = (UserDAO)ac.getBean("userDAO");
        User user1 = new User();
        user1.setId(1);
        user1.setName("set name redis");
        userDAO.saveUser(user1);
        User user2 = userDAO.getUser(1);
        System.out.println(user2.getName());
        return user2.getName();
    }
}
