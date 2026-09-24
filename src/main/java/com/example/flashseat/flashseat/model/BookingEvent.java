package com.example.flashseat.flashseat.model;

public class BookingEvent {

    private Long bookingId;
    private Long userId;
    private Long eventId;
    private Long seatId;

    public BookingEvent() {
    }

    public BookingEvent(Long bookingId, Long userId, Long eventId, Long seatId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.seatId = seatId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
}