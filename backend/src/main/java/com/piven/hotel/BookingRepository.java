package com.piven.hotel;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}