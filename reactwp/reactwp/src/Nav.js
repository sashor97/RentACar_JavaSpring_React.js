import React from 'react';
import './App.css';
import { Link } from "react-router-dom";

function Nav() {

    const navStyle = {
        color: 'white',
        'text-decoration': 'none'
    }

    return (
        <nav>
            <Link style={navStyle} to='/shop'> <h3>Rent-a-car</h3></Link>
            <ul className="nav-links">

                <Link style={navStyle} to='/shop'>
                    <li>Vozila</li>
                </Link>

                <Link style={navStyle} to='/proizvoditeli'>
                    <li>Proizvoditeli</li>
                </Link>

                <Link style={navStyle} to='/kategorii'>
                    <li>Kategorii</li>
                </Link>

                <Link style={navStyle} to='/sopstvenici'>
                    <li>Sopstvenici</li>
                </Link>

                <Link style={navStyle} to='/users'>
                    <li>Users</li>
                </Link>

                <Link style={navStyle} to='/komentar/add'>
                    <li>Komentari</li>
                </Link>

                <Link style={navStyle} to='/rezervacijaa/add'>
                    <li>Rezervacii</li>
                </Link>

                <Link style={navStyle} to='/signin'>
                    <li>Sign In</li>
                </Link>


            </ul>
        </nav>
    );
}

export default Nav;