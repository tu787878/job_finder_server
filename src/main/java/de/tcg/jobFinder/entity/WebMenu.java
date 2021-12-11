package de.tcg.jobFinder.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="webMenu")
public class WebMenu extends Menu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menuId", referencedColumnName = "menuId",insertable=false, updatable=false)
	private MenuNotification notifications;
	
	public WebMenu() {
		super();
	}

	public WebMenu(String menuId, String path, String iconLink, String name, int position, boolean visible,
			MenuNotification notifications) {
		super(menuId, path, iconLink, name, position, visible);
		this.notifications = notifications;
	}

	public WebMenu(String menuId, String path, String iconLink, String name, int position, boolean visible) {
		super(menuId, path, iconLink, name, position, visible);
	}

	public MenuNotification getNotifications() {
		return notifications;
	}

	public void setNotifications(MenuNotification notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "WebMenu [notifications=" + notifications + ", getMenuId()=" + getMenuId() + ", getPath()=" + getPath()
				+ ", getIconLink()=" + getIconLink() + ", getName()=" + getName() + ", getPosition()=" + getPosition()
				+ ", isVisible()=" + isVisible() + ", getId()=" + getId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
