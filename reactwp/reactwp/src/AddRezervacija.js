import React from 'react';
import './App.css';
import {Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export default class AddRezervacija extends React.Component {


    constructor(props){
        super(props);
        this.state = {
            items: [],
            openModal: false,
            vozila: [],

            korisnici: [],
        }
    }

    componentWillMount() {
        this.fetchItems();

        this.fetchVozila();
        this.fetchKorisnici();

    }

    fetchVozila = () => {
        fetch("http://localhost:8080/vozilo")
            .then(res => {
                res.json().then(item => {
                    this.setState({
                        vozila: item
                    })
                })
            })
    };

    // fethKategorii = () => {
    //     fetch("http://localhost:8080/kategorija")
    //         .then(res => {
    //             res.json().then(item => {
    //                 this.setState({
    //                     kategorii: item
    //                 })
    //             })
    //         })
    // };

    fetchKorisnici = () => {
        fetch("http://localhost:8080/user")
            .then(res => {
                res.json().then(item => {
                    this.setState({
                        korisnici: item
                    })
                })
            })
    };


    fetchItems =  () => {

        fetch('http://localhost:8080/rezervacija')
            .then(data => {
                data.json().then(item => {
                    this.setState({
                        items:item
                    }, () => console.log(this.state.items));
                })
            })


    };

    delete = (id) => {

        fetch(`http://localhost:8080/rezervacija/delete/${id}`)
            .then(res => {
                console.log(res.status);
                if(res.status === 200){
                    let items = this.state.items.filter(item => {
                        return item.id !== id;
                    });
                    this.setState({
                        items:items
                    });
                }
            });



    };

    addVehicle = () => {
        let vehicle = {
            datumOd: this.datumOdInput.value,
            denoviIznajmuvanje: this.denoviInput.value ,

            user: this.korisnikInput.value,
            vozilo: this.voziloInput.value,
            // kategorija: this.kategorijaInput.value
        };

        fetch("http://localhost:8080/rezervacija/add",{
            method: 'POST',
            body: JSON.stringify(vehicle),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                if(res.status === 200){
                    let items2 = this.state.items;
                    items2.push(vehicle);
                    this.setState({
                        items: items2,
                        openModal: false
                    })
                }
            });
    };

    render() {
        let modalClose = () =>  {this.setState({openModal:false})};
        let modalOpen = () =>  {this.setState({openModal:true})};
        return (


            <div>

                <h2>Site rezervacii</h2>
                {this.state.items.map(item => (
                    <div style={{'border': '1px solid black'}}>

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

                        <button onClick={() => this.delete(item.id)}>Izbrisi</button> <br/>

                        <br/>
                    </div>
                ))}
                <br/><br/>
                <Button onClick={modalOpen} className='btn btn-primary'>Dodaj rezervacija</Button>

                <br/>

                <Link to='/shop'>Nazad kon vozila </Link>

                <Modal
                    size="lg"
                    show={this.state.openModal}
                    onHide={modalClose}
                    aria-labelledby="example-modal-sizes-title-lg"
                >
                    <Modal.Header closeButton>
                        <Modal.Title id="example-modal-sizes-title-lg">
                            Додај резервација
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Датум од: </Form.Label>
                                <Form.Control type="text" placeholder="датумОд" ref={input => this.datumOdInput = input} />
                            </Form.Group>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Денови на изнајмување</Form.Label>
                                <Form.Control type="text" placeholder="денови" ref={input => this.denoviInput = input}/>
                            </Form.Group>
                            <Form.Group controlId="exampleForm.ControlSelect1">
                                <Form.Label>Корисник</Form.Label>
                                <Form.Control as="select" ref={input => this.korisnikInput = input} name="user">
                                    {this.state.korisnici.map(item => {
                                        return (<option value={item.id} key={item.id}>{item.ime}</option>)
                                    })}
                                </Form.Control>
                            </Form.Group>
                            <Form.Group controlId="exampleForm.ControlSelect1">
                                <Form.Label>Возило</Form.Label>
                                <Form.Control as="select" ref={input => this.voziloInput = input} name="vozilo">
                                    {this.state.vozila.map(item => {
                                        return (<option value={item.id} key={item.id}>{item.model}</option>)
                                    })}
                                </Form.Control>
                            </Form.Group>


                            <Form.Group>
                                <Button className="btn btn-primary" onClick={this.addVehicle}>Додај резервација</Button>
                            </Form.Group>

                        </Form>
                    </Modal.Body>
                </Modal>
            </div>


        );
    }
}
