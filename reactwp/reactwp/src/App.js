import React from 'react';
import './App.css';
import Nav from './Nav';
import Shop from './Shop';
import Proizvoditel from './Proizvoditel';
import Kategorija from './Kategorija';
import SignIn from './SignIn';
import RezervacijaDetails from './RezervacijaDetails';
import ProizvoditelDetails from './ProizvoditelDetails';
import KategorijaDetails from './KategorijaDetails';
import KomentarDetails from './KomentarDetails';

import Sopstvenik from './Sopstvenik';
import SopstvenikDetails from './SopstvenikDetails';


import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AddKategorija from "./AddKategorija";
import AddKomentar from "./AddKomentar";
import AddRezervacija from "./AddRezervacija";
import UserRezervacii from "./UserRezervacii";

import User from "./User";
import UserKomentari from "./UserKomentari";


function App() {
    return (
        <Router>
            <div className="App">
                <Nav />
                <Switch>
                    <Route path="/shop" component={Shop} />
                    <Route path="/proizvoditeli" component={Proizvoditel} />
                    <Route path="/kategorii" component={Kategorija} />
                    <Route path="/signIn" component={SignIn} />
                    <Route path="/rezervacija/:id"  component={RezervacijaDetails}/>
                    <Route path="/proizvoditel/:id"  component={ProizvoditelDetails}/>
                    <Route path="/kategorija/:id"  component={KategorijaDetails}/>
                    <Route path="/komentari/vozilo/:id"  component={KomentarDetails}/>
                    <Route path="/rezervacii/user/:id"  component={UserRezervacii}/>
                    <Route path="/komentari/user/:id"  component={UserKomentari}/>

                    <Route path="/sopstvenici" component={Sopstvenik} />
                    <Route path="/vozila/sopstvenik/:id"  component={SopstvenikDetails}/>

                    <Route path="/komentar/add"  component={AddKomentar}/>
                    <Route path="/rezervacijaa/add"  component={AddRezervacija}/>


                    <Route path="/users"  component={User}/>
                    <Route path="/kategorija/add"  component={AddKategorija}/>

                </Switch>
            </div>
        </Router>
    );
}

const Home = () => (
    <div>
        <h1>Home Page</h1>
    </div>
);

export default App;
