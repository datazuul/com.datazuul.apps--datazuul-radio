package com.datazuul.apps.jradio;

public class RadioStation {
	private String label;
	private String url;
	
	public RadioStation(String label, String url) {
		super();
		this.label = label;
		this.url = url;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
