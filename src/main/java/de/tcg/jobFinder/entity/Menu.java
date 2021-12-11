package de.tcg.jobFinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Menu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="menuId", nullable = false)
	private String menuId;
	
	@Column(name="path", nullable = false)
	private String path;
	
	@Column(name="iconLink", nullable = false)
	private String iconLink;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="position")
	private int position;
	
	@Column(name="visible", nullable = false)
	private boolean visible;
	
	public Menu() {}

	public Menu(String menuId, String path, String iconLink, String name, int position, boolean visible) {
		this.menuId = menuId;
		this.path = path;
		this.iconLink = iconLink;
		this.name = name;
		this.position = position;
		this.visible = visible;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuId=" + menuId + ", path=" + path + ", iconLink=" + iconLink + ", name=" + name
				+ ", position=" + position + ", visible=" + visible + "]";
	}

}
