package com.capstone.apm.commons.event;

public class EventConfiguration {

    private int eventRequestNumber;

    public EventConfiguration(int eventRequestNumber){
        this.eventRequestNumber = eventRequestNumber;
    }

    public int getEventRequestNumber() {
        return eventRequestNumber;
    }
}
