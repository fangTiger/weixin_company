package cn.tools.jackjson.test;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.tools.jackjson.CustomDateTimeDeserializer;
import cn.tools.jackjson.CustomDateTimeSerializer;

/**
 * 
 * 
 * @author cx
 * @date 2011-5-12 下午02:30:10
 */
 
public class User {

	private String name;
	private String password;
	private Integer age;

	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getDate() {
		return date;
	}

	@JsonDeserialize(using=CustomDateTimeDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

}
