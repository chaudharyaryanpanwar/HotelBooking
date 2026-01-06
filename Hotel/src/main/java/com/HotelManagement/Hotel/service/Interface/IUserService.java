package com.HotelManagement.Hotel.service.Interface;

import com.HotelManagement.Hotel.dto.Response;
import com.HotelManagement.Hotel.entity.User;
import com.HotelManagement.Hotel.dto.LoginRequest;


public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);;

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);


    Response getMyInfo(String email);





}
