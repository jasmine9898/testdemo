package mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="person")
public class Person {
	private String id;
	private String name;
	private String mobile;

	public Person(String id,String name, String mobile) {
		this.id=id;
        this.name = name;
        this.mobile = mobile;
    }
	public String getName(){
		return name;
	}

	public String getMobile(){
		return mobile;
	}

	public String getId() {
        return id;
    }

}