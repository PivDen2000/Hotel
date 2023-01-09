package com.piven.hotel;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookingService {

    public Map<Long, Booking> bookings = new HashMap<>();
    public Long counter = 0L;

    public Long createBooking(@RequestBody Booking booking) throws Exception {
        if (bookings.get(++counter) != null) throw new Exception("Booking already exist.");
        booking.setId(counter);
        bookings.put(counter, booking);
        return counter;
    }

    public Collection<Booking> getBookings() {
        return bookings.values();
    }

    public Booking getBooking(@PathVariable long idBooking) throws Exception {
        var booking = bookings.get(idBooking);
        if (booking == null) throw new Exception("Booking not found.");
        return booking;
    }

    public Boolean updateBooking(@PathVariable long idBooking, @RequestBody Booking booking) throws Exception {
        getBooking(idBooking);
        booking.setId(idBooking);
        bookings.put(idBooking, booking);
        return true;
    }

    public Boolean deleteBooking(@PathVariable long idBooking) throws Exception {
        getBooking(idBooking);
        bookings.remove(idBooking);
        return true;
    }
}
