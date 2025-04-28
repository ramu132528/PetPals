package entity;

import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {

    private String eventName;
    private List<IAdoptable> participants;

    public AdoptionEvent(String eventName) {
        this.eventName = eventName;
        this.participants = new ArrayList<>();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<IAdoptable> getParticipants() {
        return participants;
    }

    public void hostEvent() {
        System.out.println("Hosting Adoption Event: " + eventName);
    }

    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
        System.out.println("Participant registered successfully.");
    }
}
