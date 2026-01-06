package com.HotelManagement.Hotel.utils;

import com.HotelManagement.Hotel.dto.BookingDTO;
import com.HotelManagement.Hotel.dto.RoomDTO;
import com.HotelManagement.Hotel.dto.UserDTO;
import com.HotelManagement.Hotel.entity.Booking;
import com.HotelManagement.Hotel.entity.Room;
import com.HotelManagement.Hotel.entity.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;


public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());
        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        return bookingDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTOPlusBookings(Room room) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());

        if (room.getBookings() != null) {
            roomDTO.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
        }

        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        if (mapUser) {
            bookingDTO.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
        }

        if (booking.getRoom() != null) {

            RoomDTO roomDTO = new RoomDTO();

            roomDTO.setId(booking.getRoom().getId());
            roomDTO.setRoomType(booking.getRoom().getRoomType());
            roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDTO.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDTO.setRoomDescription(booking.getRoom().getRoomDescription());

            bookingDTO.setRoom(roomDTO);
        }

        return bookingDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()){
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false)).collect(Collectors.toList()));
        }
        return userDTO;
    }

    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList){
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }

    public static List<RoomDTO> mapRoomListEntityToRoomListDTO(List<Room> roomList){
        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }
    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(List<Booking> bookingList){
        return bookingList.stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList());
    }

}


//
//package com.HotelManagement.Hotel.utils;
//
//import com.HotelManagement.Hotel.dto.BookingDTO;
//import com.HotelManagement.Hotel.dto.RoomDTO;
//import com.HotelManagement.Hotel.dto.UserDTO;
//import com.HotelManagement.Hotel.entity.Booking;
//import com.HotelManagement.Hotel.entity.Room;
//import com.HotelManagement.Hotel.entity.User;
//
//import java.security.SecureRandom;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Utils {
//    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//    private static final SecureRandom secureRandom = new SecureRandom();
//
//    // Generate a random booking confirmation code
//    public static String generateRandomConfirmationCode(int length) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
//            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
//            stringBuilder.append(randomChar);
//        }
//        return stringBuilder.toString();
//    }
//
//    // Convert User entity to UserDTO
//    public static UserDTO mapUserEntityToUserDTO(User user) {
//        if (user == null) return null;
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPhoneNumber(user.getPhoneNumber());
//        userDTO.setRole(user.getRole());
//        return userDTO;
//    }
//
//    // Convert Room entity to RoomDTO
//    public static RoomDTO mapRoomEntityToRoomDTO(Room room) {
//        if (room == null) return null;
//
//        RoomDTO roomDTO = new RoomDTO();
//        roomDTO.setId(room.getId());
//        roomDTO.setRoomType(room.getRoomType());
//        roomDTO.setRoomPrice(room.getRoomPrice());
//        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
//        roomDTO.setRoomDescription(room.getRoomDescription());
//        return roomDTO;
//    }
//
//    // Convert Booking entity to BookingDTO
//    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {
//        if (booking == null) return null;
//
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setId(booking.getId());
//        bookingDTO.setCheckInDate(booking.getCheckInDate());
//        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
//        bookingDTO.setNumOfChildren(booking.getNumOfChildren());
//        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
////        bookingDTO.setTotalNumOfGuest(booking.getTotalNumOfGuest());
//        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
//        return bookingDTO;
//    }
//
//    // Convert Room entity to RoomDTO including associated bookings
//    public static RoomDTO mapRoomEntityToRoomDTOPlusBookings(Room room) {
//        RoomDTO roomDTO = mapRoomEntityToRoomDTO(room);
//        if (room != null && room.getBookings() != null) {
//            roomDTO.setBookings(room.getBookings().stream()
//                    .map(Utils::mapBookingEntityToBookingDTO)
//                    .collect(Collectors.toList()));
//        }
//        return roomDTO;
//    }
//
//    // Convert Booking entity to BookingDTO including booked room and optional user mapping
//    public static BookingDTO mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
//        BookingDTO bookingDTO = mapBookingEntityToBookingDTO(booking);
//        if (booking != null) {
//            if (mapUser) {
//                bookingDTO.setUser(mapUserEntityToUserDTO(booking.getUser()));
//            }
//            bookingDTO.setRoom(mapRoomEntityToRoomDTO(booking.getRoom()));
//        }
//        return bookingDTO;
//    }
//
//    // Convert User entity to UserDTO including user bookings with room details
//    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user) {
//        UserDTO userDTO = mapUserEntityToUserDTO(user);
//        if (user != null && user.getBookings() != null && !user.getBookings().isEmpty()) {
//            userDTO.setBookings(user.getBookings().stream()
//                    .map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false))
//                    .collect(Collectors.toList()));
//        }
//        return userDTO;
//    }
//
//    // Convert List<User> to List<UserDTO>
//    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
//        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
//    }
//
//    // Convert List<Room> to List<RoomDTO>
//    public static List<RoomDTO> mapRoomListEntityToRoomListDTO(List<Room> roomList) {
//        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
//    }
//
//    // Convert List<Booking> to List<BookingDTO>
//    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
//        return bookingList.stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList());
//    }
//}
