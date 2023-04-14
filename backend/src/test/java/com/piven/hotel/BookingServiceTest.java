package com.piven.hotel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class BookingServiceTest {

    private static BookingService bookingService;
    private static final Booking booking = new Booking("John Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
    private static final Long idBooking = 1L;

    @BeforeAll
    public static void setUp() {
        bookingService = new BookingService() {{
            bookings = new HashMap<>() {{
                put(idBooking, booking);
            }};
            counter = idBooking;
        }};
    }

    @Test
    public void testCreateBooking() throws Exception {
        // then
        assertEquals("Test Create Bookings", 2L, bookingService.createBooking(booking));
    }

    @Test
    public void testGetBookings() {
        // then
        assertEquals("Test Get Bookings", 1, bookingService.getBookings().size());
    }

    @Test
    public void testGetBooking() throws Exception {
        // then
        assertEquals("Test Get Booking", booking, bookingService.getBooking(idBooking));
    }

    @Test
    public void testUpdateBooking() throws Exception {
        // then
        var bookingUpd = new Booking("Jane Smith", 2, LocalDate.now(), LocalDate.now().plusDays(2), "STANDARD");
        bookingService.updateBooking(idBooking, bookingUpd);
        assertEquals("Test Get Booking", bookingUpd, bookingService.getBooking(idBooking));
    }

    @Test
    public void testDeleteBooking() throws Exception {
        // then
        assertEquals("Test Get Booking", true, bookingService.deleteBooking(idBooking));
    }
}