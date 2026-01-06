import React from 'react';
import './App.css'; 
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './component/common/Navbar';
import FooterComponent from './component/common/Footer';
import HomePage from './component/home/HomePage';
import AllRoomsPage from './component/booking_rooms/AllRoomsPage';
import FindBookingPage from './component/booking_rooms/FindBookingPage';
import RoomDetailsPage from './component/booking_rooms/RoomDetailsPage';
import LoginPage from './component/auth/LoginPage';
import RegisterPage from './component/auth/RegisterPage';
import EditProfilePage from './component/profile/EditProfilePage';
import ProfilePage from './component/profile/ProfilePage';

import {ProtectedRoute,AdminRoute} from './service/Guard';
import ManageBookingsPage from './component/admin/ManageBookingsPage';
import AddRoomPage from './component/admin/AddRoomPage';
import EditRoomPage from './component/admin/EditRoomPage';
import EditBookingPage from './component/admin/EditBookingPage';
import ManageRoomPage from './component/admin/ManageRoomPage';
import AdminPage from './component/admin/AdminPage';

function App() {
  return (
    <BrowserRouter>
      <div className='App'>
        <Navbar />
        <div className='content'>
          <Routes>
            {/* <Route path="/" element={<Navigate to="/home" />} /> */}
            {/* Public Route */}
            <Route path="/home" element={<HomePage />} />
            <Route exact path='/rooms' element={<AllRoomsPage></AllRoomsPage>}></Route>
            <Route path='/find-booking' element={<FindBookingPage/>}></Route>

            <Route path="/login" element={<LoginPage></LoginPage>}></Route>
            <Route path="/register" element={<RegisterPage></RegisterPage>}></Route>

            {/* authenticated user routes */}
            <Route path="/room-details-book/:roomId"
              element={<ProtectedRoute element={<RoomDetailsPage />} />}
            />
            <Route path="/profile"
              element={<ProtectedRoute element={<ProfilePage />} />}
            />
            <Route path="/edit-profile"
              element={<ProtectedRoute element={<EditProfilePage />} />}
            />
         {/* ADMIN AUTH ROUTER */}
         <Route path="/admin"
              element={<AdminRoute element={<AdminPage />} />}
            />

          <Route path="/admin/manage-rooms"
              element={<AdminRoute element={<ManageRoomPage />} />}
            />
              <Route path="/admin/manage-bookings"
              element={<AdminRoute element={<ManageBookingsPage />} />}
            />

<Route path="/admin/add-room"
              element={<AdminRoute element={<AddRoomPage />} />}
            />

    <Route path="/admin/edit-room/:roomId"
              element={<ProtectedRoute element={<EditRoomPage />} />}
            />
        <Route path="/admin/edit-booking/:bookingCode"
              element={<ProtectedRoute element={<EditBookingPage />} />}
            />

          </Routes>
        </div>
        <FooterComponent />
      </div>
    </BrowserRouter>
  );
}

export default App;
