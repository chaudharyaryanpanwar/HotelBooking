package com.HotelManagement.Hotel.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;

    private BigDecimal roomPrice;
    private String roomPhotoUrl;
    private String roomDescription;
//    @Column(nullable = false)
//    private LocalDate checkInDate = LocalDate.now();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<Booking> Bookings =new ArrayList<>();

    @Override
    public String toString() {
        return "Room{" +
                "Bookings=" + Bookings +
                ", id=" + id +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomPhotoUrl='" + roomPhotoUrl + '\'' +
                ", description='" + roomDescription + '\'' +
                '}';
    }
}
