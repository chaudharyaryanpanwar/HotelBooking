package com.HotelManagement.Hotel.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;
    private UserDTO user;
    private  RoomDTO room;

}
//
//package com.HotelManagement.Hotel.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//import java.time.LocalDate;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class BookingDTO {
//
//    private Long id;
//    private LocalDate checkInDate;
//    private LocalDate checkOutDate;
//    private int numOfAdults;
//    private int numOfChildren;
//    private int totalNumOfGuest;
//    private String bookingConfirmationCode;
//    private UserDTO user;
//    private RoomDTO room;
//
//    // 🔹 Default Constructor
//    public BookingDTO() {}
//
//    // 🔹 Parameterized Constructor
//    public BookingDTO(Long id, LocalDate checkInDate, LocalDate checkOutDate, int numOfAdults, int numOfChildren,
//                      String bookingConfirmationCode, UserDTO user, RoomDTO room) {
//        this.id = id;
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//        this.numOfAdults = numOfAdults;
//        this.numOfChildren = numOfChildren;
//        this.bookingConfirmationCode = bookingConfirmationCode;
//        this.user = user;
//        this.room = room;
//        calculateTotalNumberOfGuests(); // Calculate total guests upon object creation
//    }
//
//    // 🔹 Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDate getCheckInDate() {
//        return checkInDate;
//    }
//
//    public void setCheckInDate(LocalDate checkInDate) {
//        this.checkInDate = checkInDate;
//    }
//
//    public LocalDate getCheckOutDate() {
//        return checkOutDate;
//    }
//
//    public void setCheckOutDate(LocalDate checkOutDate) {
//        this.checkOutDate = checkOutDate;
//    }
//
//    public int getNumOfAdults() {
//        return numOfAdults;
//    }
//
//    public void setNumOfAdults(int numOfAdults) {
//        this.numOfAdults = numOfAdults;
//        calculateTotalNumberOfGuests();
//    }
//
//    public int getNumOfChildren() {
//        return numOfChildren;
//    }
//
//    public void setNumOfChildren(int numOfChildren) {
//        this.numOfChildren = numOfChildren;
//        calculateTotalNumberOfGuests();
//    }
//
//    public int getTotalNumOfGuest() {
//        return totalNumOfGuest;
//    }
//
//    public String getBookingConfirmationCode() {
//        return bookingConfirmationCode;
//    }
//
//    public void setBookingConfirmationCode(String bookingConfirmationCode) {
//        this.bookingConfirmationCode = bookingConfirmationCode;
//    }
//
//    public UserDTO getUser() {
//        return user;
//    }
//
//    public void setUser(UserDTO user) {
//        this.user = user;
//    }
//
//    public RoomDTO getRoom() {
//        return room;
//    }
//
//    public void setRoom(RoomDTO room) {
//        this.room = room;
//    }
//
//    // 🔹 Calculate total number of guests
//    public void calculateTotalNumberOfGuests() {
//        this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
//    }
//
//    // 🔹 toString Method
//    @Override
//    public String toString() {
//        return "BookingDTO{" +
//                "id=" + id +
//                ", checkInDate=" + checkInDate +
//                ", checkOutDate=" + checkOutDate +
//                ", numOfAdults=" + numOfAdults +
//                ", numOfChildren=" + numOfChildren +
//                ", totalNumOfGuest=" + totalNumOfGuest +
//                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
//                ", user=" + user +
//                ", room=" + room +
//                '}';
//    }
//}
//
