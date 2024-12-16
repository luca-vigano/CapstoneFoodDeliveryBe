package lucavigano.deliveryapp.service;



import lucavigano.deliveryapp.entities.Event;
import lucavigano.deliveryapp.entities.Restaurant;
import lucavigano.deliveryapp.repository.EventRepository;
import lucavigano.deliveryapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Long restaurantId, Event event) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));
        event.setRestaurant(restaurant);
        return eventRepository.save(event);
    }

    public List<Event> getEventsByRestaurantId(Long restaurantId) {
        return eventRepository.findByRestaurantId(restaurantId);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));

        event.setName(updatedEvent.getName());
        event.setLocation(updatedEvent.getLocation());
        event.setImage(updatedEvent.getImage());
        event.setStartedAt(updatedEvent.getStartedAt());
        event.setEndsAt(updatedEvent.getEndsAt());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}

