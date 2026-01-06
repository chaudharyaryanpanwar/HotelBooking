package com.HotelManagement.Hotel.entity;

//import lombok.Getter;
//import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;



import java.time.LocalDate;

@Data
@Entity
@Table(name="bookings")
public class
Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Check in date is required")
    private LocalDate checkInDate;

    @FutureOrPresent(message="Check out date must be today or in the future")
    private LocalDate checkOutDate;

    @Min(value=1,message="Number of adults should not be less than one")
    private int numOfAdults;


    @Min(value=0,message="Number of adults should not be less than zero")
    private int numOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    public void calculateTotalNumberOfGuests(){
        this.totalNumOfGuest=this.numOfAdults + this.numOfChildren;

    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNumberOfGuests();
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNumberOfGuests();
    }

    @Override
    public String toString() {
        return "Booking{" +

                ", id=" + id +
                ", checkInDate=" + checkInDate +
                ", getCheckOutDate=" + checkOutDate +
                ", numOfAdults=" + numOfAdults +
                ", numOfChildren=" + numOfChildren +
                ", totalNumOfGuest=" + totalNumOfGuest +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
                '}';
    }
}


