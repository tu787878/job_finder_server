package de.tcg.jobFinder.model.web;

public class MenuNotification {
	private String type;
	private String data;
	private String bgColor;
	private String textColor;
	
	public MenuNotification() {
		
	}

	public MenuNotification(String type, String data, String bgColor, String textColor) {
		super();
		this.type = type;
		this.data = data;
		this.bgColor = bgColor;
		this.textColor = textColor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

}
