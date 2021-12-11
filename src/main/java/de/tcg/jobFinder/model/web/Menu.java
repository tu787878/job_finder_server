package de.tcg.jobFinder.model.web;

import java.util.HashMap;

import com.google.cloud.firestore.QueryDocumentSnapshot;

public class Menu {
	private String id;
	private String linkView;
	private String name;
	private String iconLink;
	private MenuNotification notification;
	private long position;
	private boolean active;
	
	public static Menu firebaseToMenu(QueryDocumentSnapshot firebase) {
		MenuNotification menuNotification = new MenuNotification();
		HashMap noti = (HashMap) firebase.get("notification");
		if(noti != null) {
			menuNotification.setType(noti.get("type").toString());
			menuNotification.setData(noti.get("data").toString());
			menuNotification.setBgColor(noti.get("bgColor").toString());
			menuNotification.setTextColor(noti.get("textColor").toString());
		}else {
			menuNotification = null;
		}
		return new Menu(firebase.get("id").toString(),firebase.get("linkView").toString(),firebase.get("name").toString(),firebase.get("iconLink").toString(), menuNotification, (long) firebase.get("position"));
	}

	public Menu(String id, String linkView, String name, String iconLink, MenuNotification notification, long position) {
		this.id = id;
		this.linkView = linkView;
		this.name = name;
		this.iconLink = iconLink;
		this.notification = notification;
		this.position = position;
		this.active = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLinkView() {
		return linkView;
	}

	public void setLinkView(String linkView) {
		this.linkView = linkView;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public MenuNotification getNotification() {
		return notification;
	}

	public void setNotification(MenuNotification notification) {
		this.notification = notification;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
