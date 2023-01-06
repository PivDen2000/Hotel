package com.pivden.Hotel.Service;

import com.pivden.Hotel.Model.Booking;

import java.util.Collection;

public interface IBookingService {
    long createBooking(Booking booking);
    Collection<Booking> getBookings();
    Booking getBooking(long idBooking);
    void updateBooking(long idBooking, Booking booking);
    void deleteBooking(long idBooking);
}
