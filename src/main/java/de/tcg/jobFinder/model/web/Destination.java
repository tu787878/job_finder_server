package de.tcg.jobFinder.model.web;

public class Destination extends Place{

	private String subName;
	
	public Destination(String placeId, String name, String address, String mainImageId,
			double lng, double lat,String imagesId, String videosId, String subName) {
		super(placeId, name, address, mainImageId, lng, lat, imagesId, videosId);
		this.subName = subName;
	}
	
	public Destination(String name, String address, String mainImageId,
			double lng, double lat,String imagesId, String videosId, String subName) {
		super(name, address, mainImageId, lng, lat, imagesId, videosId);
		this.subName = subName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
}
