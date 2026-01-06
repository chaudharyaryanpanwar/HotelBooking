import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";

function Navbar() {
    const isAuthenticated = ApiService.isAuthenticated();
    const isAdmin = ApiService.isAdmin();
    const isUser = ApiService.isUser();
    const navigate = useNavigate();

    const handleLogout = () => {
        const isLogout = window.confirm("Are you sure you really want to logout?");
        if (isLogout) {
            ApiService.logout();
            navigate('/home');
        }
    };

    return (
        <nav>
            <span className="logo">
                <NavLink to="/home">Choudhary Hotel</NavLink>
            </span>
            <ul className="navbar-ul">
                <li><NavLink to="/home" className={({ isActive }) => (isActive ? "active" : "")}>Home</NavLink></li>
                <li><NavLink to="/rooms" className={({ isActive }) => (isActive ? "active" : "")}>Rooms</NavLink></li>
                <li><NavLink to="/find-booking" className={({ isActive }) => (isActive ? "active" : "")}>Find My Bookings</NavLink></li>

                {isUser && <li><NavLink to="/profile" className={({ isActive }) => (isActive ? "active" : "")}>Profile</NavLink></li>}
                {isAdmin && <li><NavLink to="/admin" className={({ isActive }) => (isActive ? "active" : "")}>Admin</NavLink></li>}
                {!isAuthenticated && <li><NavLink to="/login" className={({ isActive }) => (isActive ? "active" : "")}>Login</NavLink></li>}
                {!isAuthenticated && <li><NavLink to="/register" className={({ isActive }) => (isActive ? "active" : "")}>Register</NavLink></li>}
                {isAuthenticated && <li onClick={handleLogout} style={{ cursor: "pointer", color: "white" }}>Logout</li>}
            </ul>
        </nav>
    );
}

export default Navbar;
