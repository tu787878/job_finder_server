package de.tcg.jobFinder.model.web;

import java.util.UUID;

public class Place {
	private String placeId;
	private String name;
	private String address;
	private String mainImageId;
	private double lng;
	private double lat;
	private String imagesId;
	private String videosId;
	
	public Place() {}
	
	public Place(String placeId, String name, String address, String mainImageId,
			double lng, double lat,String imagesId, String videosId) {
		this.placeId = placeId;
		this.name = name;
		this.address = address;
		this.mainImageId = mainImageId;
		this.lng = lng;
		this.lat = lat;
		this.imagesId = imagesId;
		this.videosId = videosId;
	}
	
	public Place(String name, String address, String mainImageId, double lng, double lat,String imagesId, String videosId) {
		this.placeId = UUID.randomUUID().toString();
		this.name = name;
		this.address = address;
		this.mainImageId = mainImageId;
		this.lng = lng;
		this.lat = lat;
		this.imagesId = imagesId;
		this.videosId = videosId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainImageId() {
		return mainImageId;
	}

	public void setMainImageId(String mainImageId) {
		this.mainImageId = mainImageId;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getImagesId() {
		return imagesId;
	}

	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	public String getVideosId() {
		return videosId;
	}

	public void setVideosId(String videosId) {
		this.videosId = videosId;
	}

}
