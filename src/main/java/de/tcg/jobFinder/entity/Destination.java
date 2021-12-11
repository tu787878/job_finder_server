package de.tcg.jobFinder.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "destination")
public class Destination extends Place{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity = HighLight.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "placeId", referencedColumnName = "placeId")
	private Set<HighLight> highlights;
	
	public Destination() {
		super();
	}

	public Destination(String placeId, String name, String subName, String address, float lng, float lat,
			String mainImageSrc, String description, Set<Gallery> galleries, Set<HighLight> highlights) {
		super(placeId, name, subName, address, lng, lat, mainImageSrc, description, galleries);
		this.highlights = highlights;
	}

	public Destination(String placeId, String name, String subName, String address, float lng, float lat,
			String mainImageSrc, String description) {
		super(placeId, name, subName, address, lng, lat, mainImageSrc, description);
		this.highlights = new HashSet<HighLight>();
	}

	public Set<HighLight> getHighlights() {
		return highlights;
	}

	public void setHighlights(Set<HighLight> highlights) {
		this.highlights = highlights;
	}

	@Override
	public String toString() {
		return "Destination [highlights=" + highlights + ", getPlaceId()=" + getPlaceId() + ", getName()=" + getName()
				+ ", getSubName()=" + getSubName() + ", getAddress()=" + getAddress() + ", getLng()=" + getLng()
				+ ", getLat()=" + getLat() + ", getMainImageSrc()=" + getMainImageSrc() + ", getDescription()="
				+ getDescription() + ", getGalleries()=" + getGalleries() + ", getId()=" + getId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}
