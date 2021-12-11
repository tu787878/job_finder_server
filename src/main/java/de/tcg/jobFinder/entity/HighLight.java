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
@Table(name="highlight")
public class HighLight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "highlightId", unique = true, nullable = false)
	private String highlightId;
	
	@Column(name="date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="text", nullable = false)
	private String text;
	
	@Column(name="placeId", nullable = false)
	private String placeId;
	
	public HighLight() {}

	public HighLight(String highlightId, Date date, String text, String placeId) {
		super();
		this.highlightId = highlightId;
		this.date = date;
		this.text = text;
		this.placeId = placeId;
	}

	public String getHighlightId() {
		return highlightId;
	}

	public void setHighlightId(String highlightId) {
		this.highlightId = highlightId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		return "HighLight [id=" + id + ", highlightId=" + highlightId + ", date=" + date + ", text=" + text
				+ ", placeId=" + placeId + "]";
	}

	
	
}
