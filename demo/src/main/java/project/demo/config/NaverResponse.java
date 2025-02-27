package project.demo.config;

import java.util.Map;

public class NaverResponse implements OAuth2Response{

	private final Map<String,Object> attribute;
	
	public NaverResponse(Map<String, Object> attribute) {
		this.attribute=(Map<String,Object>)attribute.get("response");
	}
	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "naver";
	}

	@Override
	public String getproviderId() {
		// TODO Auto-generated method stub
		return attribute.get("id").toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attribute.get("name").toString();
	}
	@Override
	public String getBirthyear() {
		// TODO Auto-generated method stub
		return attribute.get("birthyear").toString();
	}
	

}
