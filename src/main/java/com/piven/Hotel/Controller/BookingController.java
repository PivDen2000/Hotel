package com.piven.Hotel.Controller;

import com.piven.Hotel.Model.Booking;
import com.piven.Hotel.Service.Implementation.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/booking")
@RestController
public class BookingController {
    private final BookingService bookingService = new BookingService();

    public void validateBooking(Booking booking) throws Exception {
        if (booking.getName() == null)
            throw new Exception("Empty name.");
        if (booking.getNumberOfGuests() <= 0)
            throw new Exception("Empty number of guests.");
        if (booking.getCheckInDate() == null)
            throw new Exception("Empty check-in date.");
        if (booking.getCheckOutDate() == null)
            throw new Exception("Empty check-out date.");
        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate()))
            throw new Exception("Check-in date is after check-out date.");
        if (booking.getRoomType() == null)
            throw new Exception("Empty room type.");
        if (Arrays.stream(Booking.roomTypes.values()).noneMatch(el -> booking.getRoomType().equals(el.toString())))
            throw new Exception("Incorrect room type.");
    }

    @PutMapping("/")
    public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
        try {
            validateBooking(booking);
            return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getBookings() {
        try {
            return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idBooking}")
    public ResponseEntity<Object> getBooking(@PathVariable long idBooking) {
        try {
            return new ResponseEntity<>(bookingService.getBooking(idBooking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idBooking}")
    public ResponseEntity<Object> updateBooking(@PathVariable long idBooking, @RequestBody Booking booking) {
        try {
            validateBooking(booking);
            return new ResponseEntity<>(bookingService.updateBooking(idBooking, booking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Object> deleteBooking(@PathVariable long idBooking) {
        try {
            return new ResponseEntity<>(bookingService.deleteBooking(idBooking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
