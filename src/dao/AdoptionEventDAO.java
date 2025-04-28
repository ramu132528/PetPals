package dao;

import entity.AdoptionEvent;

public interface AdoptionEventDAO {
    void hostEvent(AdoptionEvent event);
    void listParticipants(int eventId);
}


