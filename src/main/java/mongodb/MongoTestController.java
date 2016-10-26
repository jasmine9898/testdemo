package mongodb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MongoTestController {
	@Resource
	MongoTemplate mongoTemplate;
	@RequestMapping(value="/mongo.do",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String mongo()throws Exception {
		Thread.sleep(2000);
		Person person=new Person("200","name200","15800000200");
		mongoTemplate.insert(person);
	//	System.out.println(findbyname("name100").getMobile());

		if(mongoTemplate.findById("200", Person.class).getId()!=null){
			person=mongoTemplate.findById("200", Person.class);
			mongoTemplate.remove(person);
			return person.getName()+" "+person.getMobile();
		}else return "nothing";

	}
	private Person findbyname(String name){
		return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Person.class);
	}

}
