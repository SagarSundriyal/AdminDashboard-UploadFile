package com.uploadfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Response> getAllEvents() {
        return eventRepository.findAll();
    }

    public Response getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    
    public Response enableEvent(Long id) {
    	Response event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEnabled(true);
            return eventRepository.save(event);
        }
        return null;
    }

    public Response disableEvent(Long id) {
    	Response event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEnabled(false);
            return eventRepository.save(event);
        }
        return null;
    }

	public Response saveEvent(Response event) {
		// TODO Auto-generated method stub
		return eventRepository.save(event);
	}
}

   /* public void enableEvent(Long id) {
    	Response event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEnabled(true);
            eventRepository.save(event);
        }
    }

    public void disableEvent(Long id) {
    	Response event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEnabled(false);
            eventRepository.save(event);
        }
    }
}
*/