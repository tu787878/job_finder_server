package de.tcg.jobFinder.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public class Place implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "placeId", unique = true, nullable = false)
	private String placeId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "subName", nullable = true)
	private String subName;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "lng", nullable = false)
	private float lng;

	@Column(name = "lat", nullable = false)
	private float lat;

	@Column(name = "mainImageSrc", nullable = false)
	private String mainImageSrc;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(targetEntity = Gallery.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "placeId", referencedColumnName = "placeId")
	private Set<Gallery> galleries;
	
	public Place() {}
	
	public Place(String placeId, String name, String subName, String address, float lng, float lat, String mainImageSrc,
			String description) {
		super();
		this.placeId = placeId;
		this.name = name;
		this.subName = subName;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.mainImageSrc = mainImageSrc;
		this.description = description;
	}

	public Place(String placeId, String name, String subName, String address, float lng, float lat, String mainImageSrc,
			String description, Set<Gallery> galleries) {
		super();
		this.placeId = placeId;
		this.name = name;
		this.subName = subName;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
		this.mainImageSrc = mainImageSrc;
		this.description = description;
		this.galleries = galleries;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getMainImageSrc() {
		return mainImageSrc;
	}

	public void setMainImageSrc(String mainImageSrc) {
		this.mainImageSrc = mainImageSrc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", placeId=" + placeId + ", name=" + name + ", subName=" + subName + ", address="
				+ address + ", lng=" + lng + ", lat=" + lat + ", mainImageSrc=" + mainImageSrc + ", description="
				+ description + ", galleries=" + galleries + "]";
	}
}
