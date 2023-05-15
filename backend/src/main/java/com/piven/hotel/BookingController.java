package com.piven.hotel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/booking")
@RestController
@Tag(name = "Booking Controller")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepo;

    private void validateBooking(Booking booking) throws Exception {
        if (booking == null)
            throw new Exception("Booking null object.");
        if (booking.getName() == null)
            throw new Exception("Empty name.");
        if (booking.getNumberOfGuests() == null)
            throw new Exception("Empty number of guests.");
        if (booking.getNumberOfGuests() <= 0)
            throw new Exception("Incorrect number of guests.");
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

    @Operation(summary = "Create a booking", description = "Returns id of a booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "The booking was not created",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
        try {
            validateBooking(booking);
            return new ResponseEntity<>(bookingRepo.save(booking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get list of all bookings", description = "Returns list of all bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully got",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Object> getBookings() {
        try {
            return new ResponseEntity<>(bookingRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get booking by id", description = "Returns booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully got",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "The booking was not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    @GetMapping("/{idBooking}")
    public ResponseEntity<Object> getBooking(@PathVariable String idBooking) {
        try {
            return new ResponseEntity<>(bookingRepo.findById(Long.valueOf(idBooking)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update booking by id", description = "Returns boolean")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "The booking was not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    @PutMapping("/{idBooking}")
    public ResponseEntity<Object> updateBooking(@PathVariable String idBooking, @RequestBody Booking newBooking) {
        try {
            var oldBooking = bookingRepo.findById(Long.valueOf(idBooking)).get();

            if(newBooking.getName() == null)
                newBooking.setName(oldBooking.getName());
            if(newBooking.getNumberOfGuests() == null)
                newBooking.setNumberOfGuests(oldBooking.getNumberOfGuests());
            if(newBooking.getCheckInDate() == null)
                newBooking.setCheckInDate(oldBooking.getCheckInDate());
            if(newBooking.getCheckOutDate() == null)
                newBooking.setCheckOutDate(oldBooking.getCheckOutDate());
            if(newBooking.getRoomType() == null)
                newBooking.setRoomType(oldBooking.getRoomType());

            validateBooking(newBooking);

            deleteBooking(idBooking);

            return new ResponseEntity<>(bookingRepo.save(newBooking), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete booking by id", description = "Returns boolean")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "The booking was not found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Object> deleteBooking(@PathVariable String idBooking) {
        try {
            bookingRepo.delete(bookingRepo.findById(Long.valueOf(idBooking)).get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
