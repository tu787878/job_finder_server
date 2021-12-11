package de.tcg.jobFinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menuNotification")
public class MenuNotification implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="menuNotificationId", nullable = false)
	private String menuNotificationId;
	
	@Column(name="menuId", nullable = false)
	private String menuId;
	
	@Column(name="type", nullable = false)
	private String type;
	
	@Column(name="data", nullable = false)
	private String data;

	@Column(name="bgColor")
	private String bgColor;
	
	@Column(name="textColor")
	private String textColor;
	

	public MenuNotification(String menuNotificationId, String menuId, String type, String data, String bgColor,
			String textColor) {
		super();
		this.menuNotificationId = menuNotificationId;
		this.menuId = menuId;
		this.type = type;
		this.data = data;
		this.bgColor = bgColor;
		this.textColor = textColor;
	}
	
	public MenuNotification() {
		
	}

	public String getMenuNotificationId() {
		return menuNotificationId;
	}

	public void setMenuNotificationId(String menuNotificationId) {
		this.menuNotificationId = menuNotificationId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "MenuNotification [id=" + id + ", menuNotificationId=" + menuNotificationId + ", menuId=" + menuId
				+ ", type=" + type + ", data=" + data + ", bgColor=" + bgColor + ", textColor=" + textColor + "]";
	}
	

	
}
