import React from 'react';
import './App.css';
import {Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export default class AddKomentar extends React.Component {


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

        fetch('http://localhost:8080/komentar')
            .then(data => {
                data.json().then(item => {
                    this.setState({
                        items:item
                    }, () => console.log(this.state.items));
                })
            })


    };

    delete = (id) => {

        fetch(`http://localhost:8080/komentar/delete/${id}`)
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
            opis: this.opisInput.value,
            rejting: this.rejtingInput.value ,

            user: this.korisnikInput.value,
            vozilo: this.voziloInput.value,
            // kategorija: this.kategorijaInput.value
        };

        fetch("http://localhost:8080/komentar/add",{
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

                <h2>Site komentari</h2>
                {this.state.items.map(item => (
                    <div style={{'border': '1px solid black'}}>

                        <h3>Komentar: {item.opis}</h3>

                        <h3>Rejting: {item.rejting}</h3>

                        <button onClick={() => this.delete(item.id)}>Izbrisi</button> <br/>

                        <br/>
                    </div>
                ))}
                <br/><br/>
                <Button onClick={modalOpen} className='btn btn-primary'>Dodaj Komentar</Button>

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
                            Додај Коментар
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Опис</Form.Label>
                                <Form.Control type="text" placeholder="Опис" ref={input => this.opisInput = input} />
                            </Form.Group>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Рејтинг</Form.Label>
                                <Form.Control type="text" placeholder="Рејтинг" ref={input => this.rejtingInput = input}/>
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
                                <Form.Control as="select" ref={input => this.voziloInput = input}name="vozilo">
                                    {this.state.vozila.map(item => {
                                        return (<option value={item.id} key={item.id}>{item.model}</option>)
                                    })}
                                </Form.Control>
                            </Form.Group>


                            <Form.Group>
                                <Button className="btn btn-primary" onClick={this.addVehicle}>Додај Коментар</Button>
                            </Form.Group>

                        </Form>
                    </Modal.Body>
                </Modal>
            </div>


        );
    }
}
