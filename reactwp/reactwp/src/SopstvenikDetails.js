import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';

function SopstvenikDetails({match}) {

    useEffect( () => {
            fetchItems();

        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            `http://localhost:8080/vozilo/sopstvenik/${match.params.id}`
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    return (
        <div>
            <h2>Vozila od izbraniot sopstvenik</h2>

            {items.map(item => (

                <div key={item.id} style={{'border': '1px solid black', 'display':"inline-block", 'width':'250px', 'height':'400px'}}>
                    <br/>
                    <img src={item.imgUrl} style={{width:200, height:100}}/>

                    <h3>Model: {item.model}</h3>
                    <h4>Lokacija: {item.lokacija}</h4>
                    <h4>Cena: {item.cenaPoDen} den.</h4>
                    <Link to={`/rezervacija/${item.id}`}><button className="kopce"> Rezervacii </button></Link>
                    <br/><br/>
                    <Link to={`/komentari/vozilo/${item.id}`}>Vidi komentari</Link>
                </div>
            ))}
            <br/>
            <Link to='/sopstvenici'>Nazad kon sopstvenici </Link>

        </div>
    )
}

export default SopstvenikDetails;