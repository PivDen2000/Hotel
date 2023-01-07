package com.piven.Hotel.Service;

import com.piven.Hotel.Model.Booking;

import java.util.Collection;

public interface IBookingService {
    Long createBooking(Booking booking) throws Exception;
    Collection<Booking> getBookings();
    Booking getBooking(long idBooking) throws Exception;
    Boolean updateBooking(long idBooking, Booking booking) throws Exception;
    Boolean deleteBooking(long idBooking) throws Exception;
}
