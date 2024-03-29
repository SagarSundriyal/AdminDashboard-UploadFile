package com.uploadfile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {
   
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired(required=false)
    private RestTemplate restTemplate;
 
    @PostMapping("/uploadFile")
    public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file,
                                                @RequestParam("id") Long id,
                                                @RequestParam("eventName") String eventName,
                                                @RequestParam("dateEvent") String dateEvent,
                                                @RequestParam("timeEvent") String timeEvent,
                                                @RequestParam("locationVenue") String locationVenue,
                                                @RequestParam("eventDescription") String eventDescription,
                                                @RequestParam("maximumAttendee") String maximumAttendee,
                                                @RequestParam("enabled") boolean enabled) {

        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        
        Response event = new Response();
        event.setEventName(eventName);
        event.setDateEvent(dateEvent);
        event.setTimeEvent(timeEvent);
        event.setLocationVenue(locationVenue);
        event.setEventDescription(eventDescription);
        event.setMaximumAttendee(maximumAttendee);
        event.setEnabled(enabled);

        // Save the event to generate its ID
        Response savedEvent = eventService.saveEvent(event);

        Response response = new Response(savedEvent.getId(), eventName, dateEvent, timeEvent, locationVenue,
                eventDescription, maximumAttendee,
                fileDownloadUri, file.getSize(), enabled);

        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/{id}/enable")
    public ResponseEntity<String> enableEvent(@PathVariable Long id) {
        Response event = eventService.getEventById(id);
        if (event != null) {
            event.setEnabled(true); // Set status to enabled
            eventService.saveEvent(event); // Update the event in the database
            return ResponseEntity.ok("Event enabled successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<String> disableEvent(@PathVariable Long id) {
    	Response event = eventService.getEventById(id);
        if (event != null) {
            event.setEnabled(false); // Set status to disabled
            eventService.saveEvent(event); // Update the event in the database
            return ResponseEntity.ok("Event disabled successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Response> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEventById(@PathVariable Long id) {
    	Response event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    String bookedEventsUrl = "http://localhost:8925/app/v1/bookedEvents";

    @GetMapping("/displayBookedEvents")
    public List<BookingRequest> displayBookedEvents() {
    	/*if(restTemplate == null) {
    		return Collections.emptyList();
    	}*/
    	System.out.println(" ---- ");
    	
        ResponseEntity<List<BookingRequest>> responseEntity = restTemplate.exchange(
                bookedEventsUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BookingRequest>>() {}
        );
        System.out.println(" --responseEntity-- "+responseEntity);
        List<BookingRequest> events = responseEntity.getBody();
        System.out.println(" --events-- "+events);
        return events;//ResponseEntity.ok(events);
    }
}
/*
    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity<Response>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,                            
    		                            @RequestParam("eventName") String eventName,
                                              @RequestParam("dateEvent") String dateEvent,
                                              @RequestParam("timeEvent") String timeEvent,
                                              @RequestParam("locationVenue") String locationVenue,
                                              @RequestParam("eventDescription") String eventDescription,
                                              @RequestParam("maximumAttendee") String maximumAttendee,
                                              @RequestParam("uploadImage") String uploadImage,
                                              @RequestParam("fileType") String fileType){
       
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file, eventName, dateEvent, timeEvent, locationVenue,
            		eventDescription, maximumAttendee, uploadImage, fileType))
            .collect(Collectors.toList());
    }
}*/
    
/*@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();

        return new Response(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
}*/