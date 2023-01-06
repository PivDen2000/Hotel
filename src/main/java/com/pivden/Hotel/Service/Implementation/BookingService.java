package com.pivden.Hotel.Service.Implementation;

import com.pivden.Hotel.Model.Booking;
import com.pivden.Hotel.Service.IBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BookingService implements IBookingService {

    private final Map<Long, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public BookingService() {}

    @PutMapping("/")
    public long createBooking(@RequestBody Booking booking) {
        long id = counter.incrementAndGet();
        booking.setId(id);
        bookings.put(id, booking);
        return id;
    }

    @GetMapping("/")
    public Collection<Booking> getBookings() {
        return bookings.values();
    }

    @GetMapping("/{idBooking}")
    public Booking getBooking(@PathVariable long idBooking) {
        return bookings.get(idBooking);
    }

    @PutMapping("/{idBooking}")
    public void updateBooking(@PathVariable long idBooking, @RequestBody Booking booking) {
        booking.setId(idBooking);
        bookings.put(idBooking, booking);
    }

    @DeleteMapping("/{idBooking}")
    public void deleteBooking(@PathVariable long idBooking) {
        bookings.remove(idBooking);
    }
}
