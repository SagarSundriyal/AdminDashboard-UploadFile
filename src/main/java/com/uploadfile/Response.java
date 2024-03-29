package com.uploadfile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Response")
public class Response {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	 public String eventName;
	 private String dateEvent;
	 private String timeEvent;
	 private String locationVenue;
	 private String eventDescription;
	 private String maximumAttendee;
	 //private String uploadImage;
    //private String fileName;
    private String fileDownloadUri;
    //private String fileType;
    private long size;
    private boolean enabled;
    

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getTimeEvent() {
		return timeEvent;
	}

	public void setTimeEvent(String timeEvent) {
		this.timeEvent = timeEvent;
	}

	public String getLocationVenue() {
		return locationVenue;
	}

	public void setLocationVenue(String locationVenue) {
		this.locationVenue = locationVenue;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getMaximumAttendee() {
		return maximumAttendee;
	}

	public void setMaximumAttendee(String maximumAttendee) {
		this.maximumAttendee = maximumAttendee;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Response [id=" + id + ", eventName=" + eventName + ", dateEvent=" + dateEvent + ", timeEvent="
				+ timeEvent + ", locationVenue=" + locationVenue + ", eventDescription=" + eventDescription
				+ ", maximumAttendee=" + maximumAttendee + ", fileDownloadUri=" + fileDownloadUri + ", size=" + size
				+ ", enabled=" + enabled + "]";
	}

	public Response(Long id, String eventName, String dateEvent, String timeEvent, String locationVenue,
			String eventDescription, String maximumAttendee, String fileDownloadUri, long size, boolean enabled) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.dateEvent = dateEvent;
		this.timeEvent = timeEvent;
		this.locationVenue = locationVenue;
		this.eventDescription = eventDescription;
		this.maximumAttendee = maximumAttendee;
		this.fileDownloadUri = fileDownloadUri;
		this.size = size;
		this.enabled = enabled;
	}

	public Response() {
	}
}
