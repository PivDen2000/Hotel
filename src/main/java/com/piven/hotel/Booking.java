package com.piven.hotel;


import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class Booking {

    @Id
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private Integer numberOfGuests;
    @NotBlank
    private LocalDate checkInDate;
    @NotBlank
    private LocalDate checkOutDate;
    @NotBlank
    private String roomType;

    public enum roomTypes {STANDARD, SUITE}

    public Booking(String name, int numberOfGuests, LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        this.name = name;
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}