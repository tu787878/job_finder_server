package de.tcg.jobFinder.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="gallery")
public class Gallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "galleryId", unique = true, nullable = false)
	private String galleryId;
	
	@Column(name="placeId", nullable = false)
	private String placeId;
	
	@Column(name="type", nullable = false)
	private String type;
	
	@Column(name="src", nullable = false)
	private String src;
	
	@Column(name="timeCreated", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date timeCreated;
	
	@Column(name="description", nullable = false)
	private String description;
	
	public Gallery() {}

	@Override
	public String toString() {
		return "Gallery [id=" + id + ", galleryId=" + galleryId + ", placeId=" + placeId + ", type=" + type + ", src="
				+ src + ", timeCreated=" + timeCreated + ", description=" + description + "]";
	}

	public String getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(String galleryId) {
		this.galleryId = galleryId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public Gallery(String galleryId, String placeId, String type, String src, Date timeCreated, String description) {
		super();
		this.galleryId = galleryId;
		this.placeId = placeId;
		this.type = type;
		this.src = src;
		this.timeCreated = timeCreated;
		this.description = description;
	}
}
