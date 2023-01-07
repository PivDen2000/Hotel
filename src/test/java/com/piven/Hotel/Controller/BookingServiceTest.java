package com.piven.Hotel.Controller;

import com.piven.Hotel.Model.Booking;
import com.piven.Hotel.Service.Implementation.BookingService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class BookingServiceTest {
    @Test
    public void testCreateBooking() throws Exception {
        // given
        var bookingService = new BookingService();
        var booking = new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");

        // then
        assertEquals("Test Create Bookings", 1L, bookingService.createBooking(booking));
    }

    @Test
    public void testGetBookings() throws Exception {
        // given
        var bookingService = new BookingService();
        bookingService.createBooking(new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD"));
        bookingService.createBooking(new Booking("Jane Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD"));

        // then
        assertEquals("Test Get Bookings", 2, bookingService.getBookings().size());
    }

    @Test
    public void testGetBooking() throws Exception {
        // given
        var bookingService = new BookingService();
        var booking = new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
        var idBooking = bookingService.createBooking(booking);

        // then
        assertEquals("Test Get Booking", booking, bookingService.getBooking(idBooking));
    }

    @Test
    public void testUpdateBooking() throws Exception {
        // given
        var bookingService = new BookingService();
        var booking = new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
        var idBooking = bookingService.createBooking(booking);

        // then
        var bookingUpd = new Booking("Jane Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
        bookingService.updateBooking(idBooking, bookingUpd);
        assertEquals("Test Get Booking", bookingUpd, bookingService.getBooking(idBooking));
    }

    @Test
    public void testDeleteBooking() throws Exception {
        // given
        var bookingService = new BookingService();
        var booking = new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
        var idBooking = bookingService.createBooking(booking);

        // then
        bookingService.deleteBooking(idBooking);
        assertEquals("Test Get Booking", null, bookingService.getBooking(idBooking));
    }
}