package org.activage.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.activage.comtrollers.MainViewController;
import org.activage.entities.Platform;
import org.activage.entities.Service;
import org.activage.entities.SyntacticTranslator;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "mainView")
@ViewScoped
public class MainView {
	
	private String ipsmUrl;
	private String newSyntacticTranslatorID;
	private String newSyntacticTranslatorUrl;
	private String newSyntacticTranslatorPlatformType;
	
	private String newServiceID;
	private String newServiceType;
	private String newServiceUrl;
	private List<String> newServiceSources;
	private String newServiceUsername;
	private String newServicePassword;
	private String newServiceDS;
	private String newServicePlatform;

	private String newPlatformID;
	private String newPlatformType;
	private String newPlatformEndpoint;
	private String newPlatformLocation;
	private String newPlatformName;
	private String newPlatformDownstreamInputAlignmentName;
	private String newPlatformDownstreamInputAlignmentVersion;
	private String newPlatformDownstreamOutputAlignmentName;
	private String newPlatformDownstreamOutputAlignmentVersion;
	private String newPlatformUpstreamInputAlignmentName;
	private String newPlatformUpstreamInputAlignmentVersion;
	private String newPlatformUpstreamOutputAlignmentNamen;
	private String newPlatformUpstreamOutputAlignmentVersion;

	
	
	private List<SyntacticTranslator> syntacticTranslators = new ArrayList<SyntacticTranslator>();
	private List<Service> services = new ArrayList<Service>();
	private List<Platform> platforms = new ArrayList<Platform>();
	private List<String> platformIds = new ArrayList<String>();
		
	@ManagedProperty(value = "#{mainViewController}")
	protected MainViewController mainViewController;
	
	@PostConstruct
	public void init() {
		try {
			ipsmUrl = mainViewController.getIpsmUrl();
			syntacticTranslators = mainViewController.getSyntacticTranslators();
			services = mainViewController.getServices();
			platforms = mainViewController.getPlatforms();
			for (Platform p : platforms){
				platformIds.add(p.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openEditPlatformDialog(Platform p){
		System.out.println("****************************************************");
		System.out.println("---> " + p);
		newPlatformID = p.getId();
		newPlatformType = p.getPlatformType();
		newPlatformEndpoint = p.getBaseEndpoint();
		newPlatformLocation = p.getLocation();
		newPlatformName = p.getName();
		newPlatformDownstreamInputAlignmentName = p.getDownstreamInputAlignmentName();
		newPlatformDownstreamInputAlignmentVersion = p.getDownstreamInputAlignmentVersion();
		newPlatformDownstreamOutputAlignmentName = p.getDownstreamOutputAlignmentName();
		newPlatformDownstreamOutputAlignmentVersion = p.getDownstreamOutputAlignmentVersion();
		newPlatformUpstreamInputAlignmentName = p.getUpstreamInputAlignmentName();
		newPlatformUpstreamInputAlignmentVersion = p.getUpstreamInputAlignmentVersion();
		newPlatformUpstreamOutputAlignmentNamen = p.getUpstreamOutputAlignmentName();
		newPlatformUpstreamOutputAlignmentVersion = p.getUpstreamOutputAlignmentVersion();
		RequestContext.getCurrentInstance().execute("editPlatformDialog.show();");
	}
	
	public void saveIpsmUrl(){
		try {
			mainViewController.saveIpsm(ipsmUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveSyntacticTranslators(){
		try {
			mainViewController.saveSyntacticTranslators(syntacticTranslators);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeSyntacticTranslator(SyntacticTranslator st){
		try {
			mainViewController.deleteSyntacticTranslators(st);
			syntacticTranslators.remove(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewSyntacticTranslator(){
		newSyntacticTranslatorID = null;
		newSyntacticTranslatorUrl = null;
		newSyntacticTranslatorPlatformType = null;
		RequestContext.getCurrentInstance().execute("addSyntacticTranslatorDialog.show();");
	}
	
	public void saveNewSyntacticTranslator(){
		if (newSyntacticTranslatorID != null && !newSyntacticTranslatorID.isEmpty() && newSyntacticTranslatorUrl != null 
				&& !newSyntacticTranslatorUrl.isEmpty() && newSyntacticTranslatorPlatformType != null && !newSyntacticTranslatorPlatformType.isEmpty()){
			
			try {
				SyntacticTranslator st = new SyntacticTranslator(newSyntacticTranslatorID, newSyntacticTranslatorUrl, newSyntacticTranslatorPlatformType);
				mainViewController.saveNewSyntacticTranslator(st);
				syntacticTranslators.add(st);
				RequestContext.getCurrentInstance().execute("addSyntacticTranslatorDialog.hide();");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveNewPlatform(){
		if (newPlatformID != null && !newPlatformID.isEmpty() && newPlatformType != null 
				&& !newPlatformType.isEmpty() && newPlatformName != null && !newPlatformName.isEmpty()){
			
			try {
				Platform p = new Platform(newPlatformID, newPlatformType, newPlatformEndpoint, newPlatformLocation, newPlatformName, 
						newPlatformDownstreamInputAlignmentName, newPlatformDownstreamInputAlignmentVersion, newPlatformDownstreamOutputAlignmentName,
						newPlatformDownstreamOutputAlignmentVersion, newPlatformUpstreamInputAlignmentName, newPlatformUpstreamInputAlignmentVersion,
						newPlatformUpstreamOutputAlignmentNamen, newPlatformUpstreamOutputAlignmentVersion);
				mainViewController.saveNewPlatform(p);
				platforms.add(p);
				platformIds.add(p.getId());
				RequestContext.getCurrentInstance().execute("addPlatformDialog.hide();");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void savePlatform(){
		if (newPlatformID != null && !newPlatformID.isEmpty() && newPlatformType != null 
				&& !newPlatformType.isEmpty() && newPlatformName != null && !newPlatformName.isEmpty()){
			
			try {
				for (Platform p : platforms){
					if (p.getId().equals(newPlatformID)){
						p.setPlatformType(newPlatformType);
						p.setBaseEndpoint(newPlatformEndpoint);
						p.setLocation(newPlatformLocation);
						p.setName(newPlatformName);
						p.setDownstreamInputAlignmentName(newPlatformDownstreamInputAlignmentName);
						p.setDownstreamInputAlignmentVersion(newPlatformDownstreamInputAlignmentVersion);
						p.setDownstreamOutputAlignmentName(newPlatformDownstreamOutputAlignmentName);
						p.setDownstreamOutputAlignmentVersion(newPlatformDownstreamOutputAlignmentVersion);
						p.setUpstreamInputAlignmentName(newPlatformUpstreamInputAlignmentName);
						p.setUpstreamInputAlignmentVersion(newPlatformUpstreamInputAlignmentVersion);
						p.setUpstreamOutputAlignmentName(newPlatformUpstreamOutputAlignmentNamen);
						p.setUpstreamOutputAlignmentVersion(newPlatformUpstreamOutputAlignmentVersion);
						mainViewController.savePlatform(p);
						RequestContext.getCurrentInstance().execute("addPlatformDialog.hide();");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveNewService(){
		if (newServiceID != null && !newServiceID.isEmpty() && newServiceType != null 
				&& !newServiceType.isEmpty() && newServiceUrl != null && !newServiceUrl.isEmpty() && newServiceDS != null && !newServiceDS.isEmpty()
				&& newServicePlatform != null && !newServicePlatform.isEmpty()){
			try {
				Service s = new Service(newServiceID, newServiceType, newServiceUrl, newServiceSources, newServiceUsername, newServicePassword, newServiceDS, newServicePlatform);
				services.add(s);
				mainViewController.saveNewService(s);
				RequestContext.getCurrentInstance().execute("addServiceDialog.hide();");
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void removeService(Service s){
		try {
			mainViewController.deleteService(s);
			services.remove(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePlatform(Platform p){
		System.out.println("---> " + p);
		try {
			mainViewController.deletePlatform(p);
			platforms.remove(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveServices(){
		System.out.println("Save services");
		try {
			mainViewController.saveServices(services);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewService(){
		newServiceID = "";
		newServiceType = "";
		newServiceUrl = "";
		newServiceSources = new ArrayList<String>();
		newServiceUsername = "";
		newServicePassword = "";
		newServiceDS = "";
		newServicePlatform = "";
		RequestContext.getCurrentInstance().execute("addServiceDialog.show();");
	}
	
	public void addPlatform(){
		System.out.println("Add new platform");
		newPlatformID = "";
		newPlatformType = "";
		newPlatformEndpoint = "";
		newPlatformLocation = "";
		newPlatformName = "";
		newPlatformDownstreamInputAlignmentName = "";
		newPlatformDownstreamInputAlignmentVersion = "";
		newPlatformDownstreamOutputAlignmentName = "";
		newPlatformDownstreamOutputAlignmentVersion = "";
		newPlatformUpstreamInputAlignmentName = "";
		newPlatformUpstreamInputAlignmentVersion = "";
		newPlatformUpstreamOutputAlignmentNamen = "";
		newPlatformUpstreamOutputAlignmentVersion = "";
		RequestContext.getCurrentInstance().execute("addPlatformDialog.show();");

	}

	public String getIpsmUrl() {
		return ipsmUrl;
	}

	public void setIpsmUrl(String ipsmUrl) {
		this.ipsmUrl = ipsmUrl;
	}

	public MainViewController getMainViewController() {
		return mainViewController;
	}

	public void setMainViewController(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
	}

	public List<SyntacticTranslator> getSyntacticTranslators() {
		return syntacticTranslators;
	}

	public void setSyntacticTranslators(
			List<SyntacticTranslator> syntacticTranslators) {
		this.syntacticTranslators = syntacticTranslators;
	}

	public String getNewSyntacticTranslatorID() {
		return newSyntacticTranslatorID;
	}

	public void setNewSyntacticTranslatorID(String newSyntacticTranslatorID) {
		this.newSyntacticTranslatorID = newSyntacticTranslatorID;
	}

	public String getNewSyntacticTranslatorUrl() {
		return newSyntacticTranslatorUrl;
	}

	public void setNewSyntacticTranslatorUrl(String newSyntacticTranslatorUrl) {
		this.newSyntacticTranslatorUrl = newSyntacticTranslatorUrl;
	}

	public String getNewSyntacticTranslatorPlatformType() {
		return newSyntacticTranslatorPlatformType;
	}

	public void setNewSyntacticTranslatorPlatformType(
			String newSyntacticTranslatorPlatformType) {
		this.newSyntacticTranslatorPlatformType = newSyntacticTranslatorPlatformType;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getNewServiceID() {
		return newServiceID;
	}

	public void setNewServiceID(String newServiceID) {
		this.newServiceID = newServiceID;
	}

	public String getNewServiceType() {
		return newServiceType;
	}

	public void setNewServiceType(String newServiceType) {
		this.newServiceType = newServiceType;
	}

	public String getNewServiceUrl() {
		return newServiceUrl;
	}

	public void setNewServiceUrl(String newServiceUrl) {
		this.newServiceUrl = newServiceUrl;
	}

	public List<String> getNewServiceSources() {
		return newServiceSources;
	}

	public void setNewServiceSources(List<String> newServiceSources) {
		this.newServiceSources = newServiceSources;
	}

	public String getNewServiceUsername() {
		return newServiceUsername;
	}

	public void setNewServiceUsername(String newServiceUsername) {
		this.newServiceUsername = newServiceUsername;
	}

	public String getNewServicePassword() {
		return newServicePassword;
	}

	public void setNewServicePassword(String newServicePassword) {
		this.newServicePassword = newServicePassword;
	}

	public String getNewServiceDS() {
		return newServiceDS;
	}

	public void setNewServiceDS(String newServiceDS) {
		this.newServiceDS = newServiceDS;
	}

	public String getNewServicePlatform() {
		return newServicePlatform;
	}

	public void setNewServicePlatform(String newServicePlatform) {
		this.newServicePlatform = newServicePlatform;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public String getNewPlatformID() {
		return newPlatformID;
	}

	public void setNewPlatformID(String newPlatformID) {
		this.newPlatformID = newPlatformID;
	}

	public String getNewPlatformType() {
		return newPlatformType;
	}

	public void setNewPlatformType(String newPlatformType) {
		this.newPlatformType = newPlatformType;
	}

	public String getNewPlatformEndpoint() {
		return newPlatformEndpoint;
	}

	public void setNewPlatformEndpoint(String newPlatformEndpoint) {
		this.newPlatformEndpoint = newPlatformEndpoint;
	}

	public String getNewPlatformLocation() {
		return newPlatformLocation;
	}

	public void setNewPlatformLocation(String newPlatformLocation) {
		this.newPlatformLocation = newPlatformLocation;
	}

	public String getNewPlatformName() {
		return newPlatformName;
	}

	public void setNewPlatformName(String newPlatformName) {
		this.newPlatformName = newPlatformName;
	}

	public String getNewPlatformDownstreamInputAlignmentName() {
		return newPlatformDownstreamInputAlignmentName;
	}

	public void setNewPlatformDownstreamInputAlignmentName(
			String newPlatformDownstreamInputAlignmentName) {
		this.newPlatformDownstreamInputAlignmentName = newPlatformDownstreamInputAlignmentName;
	}

	public String getNewPlatformDownstreamInputAlignmentVersion() {
		return newPlatformDownstreamInputAlignmentVersion;
	}

	public void setNewPlatformDownstreamInputAlignmentVersion(
			String newPlatformDownstreamInputAlignmentVersion) {
		this.newPlatformDownstreamInputAlignmentVersion = newPlatformDownstreamInputAlignmentVersion;
	}

	public String getNewPlatformDownstreamOutputAlignmentName() {
		return newPlatformDownstreamOutputAlignmentName;
	}

	public void setNewPlatformDownstreamOutputAlignmentName(
			String newPlatformDownstreamOutputAlignmentName) {
		this.newPlatformDownstreamOutputAlignmentName = newPlatformDownstreamOutputAlignmentName;
	}

	public String getNewPlatformDownstreamOutputAlignmentVersion() {
		return newPlatformDownstreamOutputAlignmentVersion;
	}

	public void setNewPlatformDownstreamOutputAlignmentVersion(
			String newPlatformDownstreamOutputAlignmentVersion) {
		this.newPlatformDownstreamOutputAlignmentVersion = newPlatformDownstreamOutputAlignmentVersion;
	}

	public String getNewPlatformUpstreamInputAlignmentName() {
		return newPlatformUpstreamInputAlignmentName;
	}

	public void setNewPlatformUpstreamInputAlignmentName(
			String newPlatformUpstreamInputAlignmentName) {
		this.newPlatformUpstreamInputAlignmentName = newPlatformUpstreamInputAlignmentName;
	}

	public String getNewPlatformUpstreamInputAlignmentVersion() {
		return newPlatformUpstreamInputAlignmentVersion;
	}

	public void setNewPlatformUpstreamInputAlignmentVersion(
			String newPlatformUpstreamInputAlignmentVersion) {
		this.newPlatformUpstreamInputAlignmentVersion = newPlatformUpstreamInputAlignmentVersion;
	}

	public String getNewPlatformUpstreamOutputAlignmentNamen() {
		return newPlatformUpstreamOutputAlignmentNamen;
	}

	public void setNewPlatformUpstreamOutputAlignmentNamen(
			String newPlatformUpstreamOutputAlignmentNamen) {
		this.newPlatformUpstreamOutputAlignmentNamen = newPlatformUpstreamOutputAlignmentNamen;
	}

	public String getNewPlatformUpstreamOutputAlignmentVersion() {
		return newPlatformUpstreamOutputAlignmentVersion;
	}

	public void setNewPlatformUpstreamOutputAlignmentVersion(
			String newPlatformUpstreamOutputAlignmentVersion) {
		this.newPlatformUpstreamOutputAlignmentVersion = newPlatformUpstreamOutputAlignmentVersion;
	}

	public List<String> getPlatformIds() {
		platformIds = new ArrayList<String>(); 
		for (Platform p : platforms){
			platformIds.add(p.getId());
		}
		return platformIds;
	}

	public void setPlatformIds(List<String> platformIds) {
		this.platformIds = platformIds;
	}	
}
