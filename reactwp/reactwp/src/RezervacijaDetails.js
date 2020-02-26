import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';

function RezervacijaDetails({match}) {

    useEffect( () => {
            fetchItems();

        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            `http://localhost:8080/rezervacija/vozilo/${match.params.id}`
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    const deleteF = (id) =>
    {

        fetch(`http://localhost:8080/rezervacija/delete/${id}`)
            .then(res => {
                console.log(res.status);

            });



    };

    return (
        <div>
            <h2>Rezervacii za voziloto</h2>

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

                    <button onClick={() => deleteF(item.id)}>Izbrisi</button> <br/>
                </div>

            ))}
            <br/>
            <Link to='/rezervacijaa/add'>Dodaj rezervacija </Link>

            <br/>
            <Link to='/shop'>Nazad kon vozila </Link>

        </div>
    );
}

export default RezervacijaDetails;