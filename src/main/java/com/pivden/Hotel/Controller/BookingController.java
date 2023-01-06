package com.pivden.Hotel.Controller;

import com.pivden.Hotel.Model.Booking;
import com.pivden.Hotel.Service.Implementation.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/booking")
@RestController
public class BookingController {
    private final BookingService bookingService = new BookingService();

    @PutMapping("/")
    public long createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/")
    public Collection<Booking> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/{idBooking}")
    public Booking getBooking(@PathVariable long idBooking) {
        return bookingService.getBooking(idBooking);
    }

    @PutMapping("/{idBooking}")
    public void updateBooking(@PathVariable long idBooking, @RequestBody Booking booking) {
        bookingService.updateBooking(idBooking, booking);
    }

    @DeleteMapping("/{idBooking}")
    public void deleteBooking(@PathVariable long idBooking) {
        bookingService.deleteBooking(idBooking);
    }

}
