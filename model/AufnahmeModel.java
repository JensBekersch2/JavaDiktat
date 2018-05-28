package model;

public class AufnahmeModel {

	private String name;
	private String duration;
	
	public AufnahmeModel() {}
	
	public AufnahmeModel(String name, String duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
