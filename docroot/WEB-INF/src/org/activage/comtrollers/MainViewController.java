package org.activage.comtrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.activage.backend.HTTPClient;
import org.activage.entities.Platform;
import org.activage.entities.Service;
import org.activage.entities.SyntacticTranslator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ManagedBean(name = "mainViewController")
@ViewScoped
public class MainViewController {
	
	//private static final String URL = "http://service_registry:80/";
	private static final String DEFAULT_URL = "http://localhost:20085/";
	private String URL;
	
	@PostConstruct
	public void init() {
		URL = System.getenv("BACKEND_URL");
		if (URL == null || URL.isEmpty()){
			System.out.println("Use default");
			URL = DEFAULT_URL;
		}
		System.out.println("===> " + URL);
	}

	
	private JsonObject getIpsm() throws Exception{
		String SERVICE_REGISTRY_URL = URL + "services";
		String s = HTTPClient.sendGet(SERVICE_REGISTRY_URL + "/ipsm");
		return new JsonParser().parse(s).getAsJsonObject();
	}
	
	public JsonArray getAllServices() throws Exception{
		String SERVICE_REGISTRY_URL = URL + "services";
		String s = HTTPClient.sendGet(SERVICE_REGISTRY_URL);
		return new JsonParser().parse(s).getAsJsonArray();
	}
	
	public String getIpsmUrl() throws Exception{
		String url = getIpsm().get("url").getAsString();
		return url;
	}
	
	public void saveIpsm(String url) throws Exception{
		JsonObject json = getIpsm();
		json.addProperty("url", url);
		String SERVICE_REGISTRY_URL = URL + "services";
		HTTPClient.sendPut(SERVICE_REGISTRY_URL + "/ipsm", json.toString());
	}
	
	public void saveServices(List<Service> services) throws Exception{
		JsonArray array = getAllServices();
		for (int i=0; i < array.size(); i++){
			JsonObject service = array.get(i).getAsJsonObject();
			String serviceType = service.get("type").getAsString();
			if (serviceType.equals("platform-historic") || serviceType.equals("independent-storage")){
				List<String> sources = new ArrayList<String>();
				JsonArray sourcesArray = service.get("sources").getAsJsonArray();
				for (int k=0; k < sourcesArray.size(); k++){
					sources.add(sourcesArray.get(k).getAsString());
				}
				Service s = new Service(service.get("id").getAsString(), service.get("type").getAsString(), service.get("url").getAsString(),
						sources, service.get("user").getAsString(), service.get("password").getAsString(), 
						service.get("DS").getAsString(), service.get("platform").getAsString());
				for (Service newService : services){
					if (s.getId().equals(newService.getId())){
						service = serviceTranslatorToJson(service, newService);
						String SERVICE_REGISTRY_URL = URL + "services";
						HTTPClient.sendPut(SERVICE_REGISTRY_URL + "/" + s.getId(), service.toString());
						break;
					}
				}
			}
		}
	}
	
	public List<SyntacticTranslator> getSyntacticTranslators() throws Exception{
		List<SyntacticTranslator> list = new ArrayList<SyntacticTranslator>();
		JsonArray array = getAllServices();
		for (int i=0; i < array.size(); i++){
			JsonObject service = array.get(i).getAsJsonObject();
			String serviceType = service.get("type").getAsString();
			if (serviceType.equals("syntactic-translator")){
				SyntacticTranslator st = new SyntacticTranslator(service.get("id").getAsString(), service.get("url").getAsString(), service.get("platformType").getAsString());
				list.add(st);
			}
		}
		return list;
	}
	
	public List<Service> getServices() throws Exception{
		List<Service> list = new ArrayList<Service>();
		JsonArray array = getAllServices();
		for (int i=0; i < array.size(); i++){
			JsonObject service = array.get(i).getAsJsonObject();
			String serviceType = service.get("type").getAsString();
			if (serviceType.equals("platform-historic") || serviceType.equals("independent-storage")){
				List<String> sources = new ArrayList<String>();
				JsonArray sourcesArray = service.get("sources").getAsJsonArray();
				for (int k=0; k < sourcesArray.size(); k++){
					sources.add(sourcesArray.get(k).getAsString());
				}
				Service s = new Service(service.get("id").getAsString(), service.get("type").getAsString(), service.get("url").getAsString(),
						sources, service.get("user").getAsString(), service.get("password").getAsString(), 
						service.get("DS").getAsString(), service.get("platform").getAsString());
				list.add(s);
			}
		}
		return list;
	}
	
	public List<Platform> getPlatforms() throws Exception{
		List<Platform> list = new ArrayList<Platform>();
		String PLATFORM_REGISTRY_URL = URL + "platforms";
		String s = HTTPClient.sendGet(PLATFORM_REGISTRY_URL);
		JsonArray array = new JsonParser().parse(s).getAsJsonArray();
		for (int i=0; i < array.size(); i++){
			JsonObject platform = array.get(i).getAsJsonObject();
			Platform p = new Platform(platform.get("id").getAsString(), platform.get("platformType").getAsString(), platform.get("baseEndpoint").getAsString(), 
					platform.get("location").getAsString(), platform.get("name").getAsString(), 
					platform.get("downstreamInputAlignmentName").getAsString(), platform.get("downstreamInputAlignmentVersion").getAsString(), 
					platform.get("downstreamOutputAlignmentName").getAsString(), platform.get("downstreamOutputAlignmentVersion").getAsString(),
					platform.get("upstreamInputAlignmentName").getAsString(), platform.get("upstreamInputAlignmentVersion").getAsString(),
					platform.get("upstreamOutputAlignmentName").getAsString(), platform.get("upstreamOutputAlignmentVersion").getAsString());
			list.add(p);
		}
		return list;
	}
	
	public void saveSyntacticTranslators(List<SyntacticTranslator> syntacticTranslators) throws Exception{
		JsonArray array = getAllServices();
		for (int i=0; i < array.size(); i++){
			JsonObject service = array.get(i).getAsJsonObject();
			String serviceType = service.get("type").getAsString();
			if (serviceType.equals("syntactic-translator")){
				SyntacticTranslator st = new SyntacticTranslator(service.get("id").getAsString(), service.get("url").getAsString(), service.get("platformType").getAsString());
				for (SyntacticTranslator newST : syntacticTranslators){
					if (st.getId().equals(newST.getId())){
						service = syntacticTranslatorToJson(service, newST);
						String SERVICE_REGISTRY_URL = URL + "services";
						HTTPClient.sendPut(SERVICE_REGISTRY_URL + "/" + st.getId(), service.toString());
						break;
					}
				}
				
			}
		}	
	}
	
	public void saveNewSyntacticTranslator(SyntacticTranslator st) throws IOException{
		JsonObject json = new JsonObject();
		json = syntacticTranslatorToJson(json, st);
		String SERVICE_REGISTRY_URL = URL + "services";
		HTTPClient.sendPost(SERVICE_REGISTRY_URL, json.toString());	
	}
	
	public void saveNewPlatform(Platform p) throws IOException{
		JsonObject json = new JsonObject();
		json = platformToJson(json, p);
		String PLATFORM_REGISTRY_URL = URL + "platforms";
		HTTPClient.sendPost(PLATFORM_REGISTRY_URL, json.toString());	
	}
	
	public void savePlatform(Platform p) throws IOException{
		JsonObject json = new JsonObject();
		json = platformToJson(json, p);
		String PLATFORM_REGISTRY_URL = URL + "platforms";
		HTTPClient.sendPost(PLATFORM_REGISTRY_URL, json.toString());
	}
	
	public void saveNewService(Service s) throws IOException{
		JsonObject json = new JsonObject();
		json = serviceTranslatorToJson(json, s);
		String SERVICE_REGISTRY_URL = URL + "services";
		HTTPClient.sendPost(SERVICE_REGISTRY_URL, json.toString());	
	}
	
	private JsonObject syntacticTranslatorToJson(JsonObject json, SyntacticTranslator st){
		json.addProperty("id", st.getId());
		json.addProperty("url", st.getUrl());
		json.addProperty("platformType", st.getPlatformType());
		json.addProperty("type", "syntactic-translator");
		return json;
	}
	
	private JsonObject serviceTranslatorToJson(JsonObject json, Service s){
		json.addProperty("id", s.getId());
		json.addProperty("type", s.getType());
		json.addProperty("url", s.getUrl());
		JsonArray sourcesArray = new JsonArray();
		for (String source : s.getSources()){
			sourcesArray.add(source);
		}
		json.add("sources", sourcesArray);
		json.addProperty("user", s.getUser());
		json.addProperty("password", s.getPassword());
		json.addProperty("DS", s.getDs());
		json.addProperty("platform", s.getPlatform());
		return json;	
	}
	
	private JsonObject platformToJson(JsonObject json, Platform p){
		json.addProperty("id", p.getId());
		json.addProperty("platformType", p.getPlatformType());
		json.addProperty("baseEndpoint", p.getBaseEndpoint());
		json.addProperty("location", p.getLocation());
		json.addProperty("name", p.getName());
		json.addProperty("downstreamInputAlignmentName", p.getDownstreamInputAlignmentName());
		json.addProperty("downstreamInputAlignmentVersion", p.getDownstreamInputAlignmentVersion());
		json.addProperty("downstreamOutputAlignmentName", p.getDownstreamOutputAlignmentName());
		json.addProperty("downstreamOutputAlignmentVersion", p.getDownstreamOutputAlignmentVersion());
		
		json.addProperty("upstreamInputAlignmentName", p.getUpstreamInputAlignmentName());
		json.addProperty("upstreamInputAlignmentVersion", p.getUpstreamInputAlignmentVersion());
		json.addProperty("upstreamOutputAlignmentName", p.getUpstreamOutputAlignmentName());
		json.addProperty("upstreamOutputAlignmentVersion", p.getUpstreamOutputAlignmentVersion());

		return json;
	}
	
	public void deleteSyntacticTranslators(SyntacticTranslator st) throws IOException{
		String SERVICE_REGISTRY_URL = URL + "services";
		HTTPClient.sendDelete(SERVICE_REGISTRY_URL + "/" + st.getId());
	}
	
	public void deleteService(Service s) throws IOException{
		String SERVICE_REGISTRY_URL = URL + "services";
		HTTPClient.sendDelete(SERVICE_REGISTRY_URL + "/" + s.getId());
	}
	
	public void deletePlatform(Platform p) throws IOException{
		String PLATFORM_REGISTRY_URL = URL + "platforms";
		HTTPClient.sendDelete(PLATFORM_REGISTRY_URL + "/" + p.getId());
	}

}
