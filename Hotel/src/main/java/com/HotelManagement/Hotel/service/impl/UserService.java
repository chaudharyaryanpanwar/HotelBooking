package com.HotelManagement.Hotel.service.impl;


import com.HotelManagement.Hotel.dto.LoginRequest;
import com.HotelManagement.Hotel.dto.Response;
import com.HotelManagement.Hotel.dto.UserDTO;
import com.HotelManagement.Hotel.entity.User;
import com.HotelManagement.Hotel.exception.OurException;
import com.HotelManagement.Hotel.repo.UserRepository;
import com.HotelManagement.Hotel.service.Interface.IUserService;
import com.HotelManagement.Hotel.utils.JWTUtils;
import com.HotelManagement.Hotel.utils.Utils;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements
        IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

//😎😎😎😎😎😎😎😎 Logic of user role encoded of password and all...
    @Override
    public Response register(User user){
        Response response=new Response();

        try{
            if(user.getRole()==null || user.getRole().isBlank()){
                user.setRole("USER");
            }
            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurException(user.getEmail() + " " + "Already Exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser=userRepository.save(user);
            UserDTO userDTO= Utils.mapUserEntityToUserDTO(savedUser);

            response.setStatusCode(200);
            response.setUser(userDTO);
            response.setMessage("successfull");

        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Saving a User" + e.getMessage());

        }
        return response;
    }






// logic for login 😎  user token logic also

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response=new Response();

        try{
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
         var user =userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new OurException("User Not found"));
         var token =jwtUtils.generateToken(user);
         response.setToken(token);
         response.setExpirationTime("7 days");
         response.setRole(user.getRole());
         response.setMessage("Success");



            response.setStatusCode(200);


        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Login in" + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response=new Response();

        try{
            List<User> userList=userRepository.findAll();
            List<UserDTO> userDTOList=Utils.mapUserListEntityToUserListDTO(userList);
          response.setUserList(userDTOList);

            response.setMessage("Success");



            response.setStatusCode(200);


        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all user " + e.getMessage());

        }
        return response;
    }



    @Override
    public Response getUserBookingHistory(String userId) {
        Response response=new Response();

        try{
            User user =userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("User not found"));
            UserDTO userDTO=Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);

            response.setMessage("Success");
            response.setStatusCode(200);
            response.setUser(userDTO);


        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting user booking in" + e.getMessage());

        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        Response response=new Response();

        try{
           userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("User not found"));
          userRepository.deleteById(Long.valueOf(userId));


            response.setMessage("Success");
            response.setStatusCode(200);


        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error deleting a user" + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {
        Response response=new Response();

        try{
            User user=userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("User not found"));
           UserDTO userDTO=Utils.mapUserEntityToUserDTO(user);


            response.setMessage("Success");
            response.setStatusCode(200);
            response.setUser(userDTO);


        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting  a user by id " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response=new Response();

        try{
            User user=userRepository.findByEmail(email).orElseThrow(()->new OurException("User not found"));
            UserDTO userDTO=Utils.mapUserEntityToUserDTO(user);


            response.setMessage("Success");
            response.setStatusCode(200);
            response.setUser(userDTO);


        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting  a user info " + e.getMessage());

        }
        return response;
    }
}
