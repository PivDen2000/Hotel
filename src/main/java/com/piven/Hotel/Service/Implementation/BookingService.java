package com.piven.Hotel.Service.Implementation;

import com.piven.Hotel.Model.Booking;
import com.piven.Hotel.Service.IBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookingService implements IBookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();
    private Long counter = 0L;

    public BookingService() {
    }

    @PutMapping("/")
    public Long createBooking(@RequestBody Booking booking) throws Exception {
        return createBookingById(++counter, booking);
    }

    private Long createBookingById(long idBooking, @RequestBody Booking booking) throws Exception {
        if (bookings.get(idBooking) != null) throw new Exception("Booking already exist.");
        booking.setId(idBooking);
        bookings.put(idBooking, booking);
        return idBooking;
    }

    @GetMapping("/")
    public Collection<Booking> getBookings() {
        return bookings.values();
    }

    @GetMapping("/{idBooking}")
    public Booking getBooking(@PathVariable long idBooking) throws Exception {
        var booking = bookings.get(idBooking);
        if (booking == null) throw new Exception("Booking not found.");
        return booking;
    }

    @PutMapping("/{idBooking}")
    public Boolean updateBooking(@PathVariable long idBooking, @RequestBody Booking booking) throws Exception {
        getBooking(idBooking);
        booking.setId(idBooking);
        bookings.put(idBooking, booking);
        return true;
    }

    @DeleteMapping("/{idBooking}")
    public Boolean deleteBooking(@PathVariable long idBooking) throws Exception {
        getBooking(idBooking);
        bookings.remove(idBooking);
        return true;
    }
}
