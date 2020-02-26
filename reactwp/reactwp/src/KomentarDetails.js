import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';


function KomentarDetails({match}) {

    useEffect( () => {
            fetchItems();

        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            `http://localhost:8080/komentar/vozilo/${match.params.id}`
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    const deleteF = (id) =>
    {

        fetch(`http://localhost:8080/komentar/delete/${id}`)
            .then(res => {
                console.log(res.status);

            });



    };




    return (
        <div>
            <h2>Komentari i rejting za voziloto</h2>

            {items.map(item => (

                <div style={{'border': '1px solid black'}}>

                    <h3>Komentar: {item.opis}</h3>

                    <h3>Rejting: {item.rejting}</h3>

                    <button onClick={() => deleteF(item.id)}>Izbrisi</button> <br/>
                </div>

            ))}
            <br/>
            <Link to='/komentar/add'>Dodaj komentar </Link>
            <br/>
            <Link to='/shop'>Nazad kon vozila </Link>

        </div>
    );
}

export default KomentarDetails;

