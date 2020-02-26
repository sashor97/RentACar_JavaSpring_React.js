import React from 'react';
import './App.css';
import {Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export default class Shop extends React.Component {


    constructor(props){
        super(props);
        this.state = {
            items: [],
            openModal: false,
            proizvoditeli: [],
            kategorii: [],
            sopstvenici: [],
        }
    }

    componentWillMount() {
        this.fetchItems();
        this.fetchProizvoditeli();
        this.fethKategorii();
        this.fetchSopstvenici();

    }

    fetchProizvoditeli = () => {
        fetch("http://localhost:8080/proizvoditel")
            .then(res => {
                res.json().then(item => {
                    this.setState({
                        proizvoditeli: item
                    })
                })
            })
    };

    fethKategorii = () => {
        fetch("http://localhost:8080/kategorija")
            .then(res => {
                res.json().then(item => {
                    this.setState({
                        kategorii: item
                    })
                })
            })
    };

    fetchSopstvenici = () => {
        fetch("http://localhost:8080/sopstvenik")
            .then(res => {
                res.json().then(item => {
                    this.setState({
                        sopstvenici: item
                    })
                })
            })
    };


    fetchItems =  () => {

        fetch('http://localhost:8080/vozilo')
            .then(data => {
                data.json().then(item => {
                   this.setState({
                       items:item
                   }, () => console.log(this.state.items));
                })
            })


    };

    delete = (id) => {

        fetch(`http://localhost:8080/vozilo/delete/${id}`)
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
            model: this.modelInput.value,
            imgUrl: this.urlInput.value ,
            lokacija: this.lokacijaInput.value ,
            cenaPoDen: this.cenaInput.value,
             proizvoditel: this.proizvoditelInput.value,
             sopstvenik: this.sopstvenikInput.value,
             kategorija: this.kategorijaInput.value
        };
        console.log(vehicle);
        fetch("http://localhost:8080/vozilo/add",{
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


            {this.state.items.map(item => (
    <div key={item.id} style={{'border': '1px solid black', 'display':"inline-block", 'width':'250px', 'height':'395px'}}>
        <br/>
        <img src={item.imgUrl} style={{width:200, height:100}}/>

        <h3>Model: {item.model}</h3>
        <h4>Lokacija: {item.lokacija}</h4>
        <h4>Cena: {item.cenaPoDen} den.</h4>
        <Link to={`/rezervacija/${item.id}`}><button className="kopce"> Rezervacii </button></Link> <br/><br/>

        <Link to={`/komentari/vozilo/${item.id}`}>Vidi komentari</Link> <br/>
        <button onClick={() => this.delete(item.id)}>Izbrisi</button>
        <br/>
    </div>
))}
            <br/><br/>
            <Button onClick={modalOpen} className='btn btn-primary'>Dodaj</Button>


            <Modal
                size="lg"
                show={this.state.openModal}
                onHide={modalClose}
                aria-labelledby="example-modal-sizes-title-lg"
            >
                <Modal.Header closeButton>
                    <Modal.Title id="example-modal-sizes-title-lg">
                        Додај Возило
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="exampleForm.ControlInput1">
                            <Form.Label>Модел</Form.Label>
                            <Form.Control type="text" placeholder="Модел" ref={input => this.modelInput = input} />
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlInput1">
                            <Form.Label>URL</Form.Label>
                            <Form.Control type="text" placeholder="URL" ref={input => this.urlInput = input}/>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect1">
                        <Form.Label>Производител</Form.Label>
                        <Form.Control as="select" ref={input => this.proizvoditelInput = input} name="proizvoditel" >
                            {this.state.proizvoditeli.map(item => {
                                return (<option value={item.id} key={item.id}>{item.name}</option>)
                            })}
                        </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect1">
                            <Form.Label>Категорија</Form.Label>
                            <Form.Control as="select" ref={input => this.kategorijaInput = input} name="kategorija">
                                {this.state.kategorii.map(item => {
                                    return (<option value={item.id} key={item.id}>{item.name}</option>)
                                })}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect1">
                            <Form.Label>Сопственик</Form.Label>
                            <Form.Control as="select" ref={input => this.sopstvenikInput = input} name="sopstvenik">
                                {this.state.sopstvenici.map(item => {
                                    return (<option key={item.id} value={item.id} >{item.ime}</option>)
                                })}
                            </Form.Control>
                        </Form.Group>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridCity">
                                <Form.Label>Локација</Form.Label>
                                <Form.Control type="text" ref={input => this.lokacijaInput = input}/>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGridZip">
                                <Form.Label>Цена по ден</Form.Label>
                                <Form.Control type="text" ref={input => this.cenaInput = input}/>
                            </Form.Group>
                        </Form.Row>
                        <Form.Group>
                            <Button className="btn btn-primary" onClick={this.addVehicle}>Додај</Button>
                        </Form.Group>

                    </Form>
                </Modal.Body>
            </Modal>
        </div>


        );
    }
}

