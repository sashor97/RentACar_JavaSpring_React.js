import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';

function UserKomentari({match}) {

    useEffect( () => {
            fetchItems();

        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            `http://localhost:8080/komentar/user/${match.params.id}`
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    return (
        <div>
            <h2>Komentari koi gi ima napisano izbraniot user</h2>

            {items.map(item => (

                <div style={{'border': '1px solid black'}}>

                    <h3>Komentar: {item.opis}</h3>

                    <h3>Rejting: {item.rejting}</h3>

                </div>
            ))}
            <br/>
            <Link to='/users'>Nazad kon users </Link>
            <br/>
            <Link to='/shop'>Nazad kon vozila </Link>

        </div>
    )
}

export default UserKomentari;