package cn.tools.jackjson.test;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import cn.tools.jackjson.JackJson;

/**
 * 
 * 
 * @author cx
 * @date 2011-5-12 下午02:31:26
 */
public class MainTest {
	public static void main(String[] args) throws Exception {
		test1();
	}

	private static void test1() throws JsonGenerationException, JsonMappingException, IOException {
		/*User user = new User();
		user.setAge(23);
		user.setName("cx");
		user.setPassword("123456");
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter",
				SimpleBeanPropertyFilter.serializeAllExcept("password"));
		// SimpleBeanPropertyFilter.filterOutAllExcept("password"));
		// and then serialize using that filter provider:
		String json = mapper.filteredWriter(filters).writeValueAsString(user);
		System.out.println(json);*/
//		String json = "{\"age\":23,\"name\":\"cx\"}";
//		
//		User user = JackJson.fromJsonToObject(json, User.class);
//		System.out.println(user.getName() + "| " + user.getAge());
		
		User user = new User();
		user.setDate(new Date());
		String json = JackJson.fromObjectHasDateToJson(user);
		
		System.out.println(json);
		
		User u = JackJson.fromJsonToObject(json, User.class);
		System.out.println(u.getDate() + ":" + u.getName());
	}
}
