import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';

function UserRezervacii({match}) {

    useEffect( () => {
            fetchItems();

        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            `http://localhost:8080/rezervacija/user/${match.params.id}`
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    return (
        <div>
            <h2>Rezervacii koi gi ima napraveno izbraniot user</h2>

            {items.map(item => (

                <div style={{'border': '1px solid red'}}>
                    {item.uspeshnost &&
                    <h4>Poceten datum: {item.datumOd}</h4>
                    }

                    {item.uspeshnost &&
                    <h4>Kraen datum: {item.datumDo}</h4>
                    }

                    {item.uspeshnost &&
                    <h4>Denovi: {item.denoviIznajmuvanje}</h4>
                    }

                    {item.uspeshnost &&
                    <h4>Cena: {item.total}</h4>
                    }


                </div>
            ))}
            <br/>
            <Link to='/users'>Nazad kon users </Link>
            <br/>
            <Link to='/shop'>Nazad kon vozila </Link>

        </div>
    )
}

export default UserRezervacii;