import React from 'react';
import './App.css';
import {Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export default class User extends React.Component {


    constructor(props){
        super(props);
        this.state = {
            items: [],
            openModal: false

        }
    }

    componentWillMount() {
        this.fetchItems();

    }


    fetchItems =  () => {

        fetch('http://localhost:8080/user')
            .then(data => {
                data.json().then(item => {
                    this.setState({
                        items:item
                    }, () => console.log(this.state.items));
                })
            })


    };


    delete = (id) => {

        fetch(`http://localhost:8080/user/delete/${id}`)
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



    addUser = () => {
        let user = {
            ime: this.nameInput.value,
            prezime: this.surNameInput.value,
            adresa: this.adresaInput.value,
            godini: this.godiniInput.value,

        };

        fetch("http://localhost:8080/user/add",{
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                if(res.status === 200){
                    let items2 = this.state.items;
                    items2.push(user);
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

                <h1>Site users: </h1>
                {this.state.items.map(item => (
                    <div style={{'border': '1px solid darkgrey'}}>
                        <h4>Ime: {item.ime}</h4>
                        <h4>Prezime: {item.prezime}</h4>
                        <h4>Adresa: {item.adresa}</h4>
                        <h4>Godini: {item.godini}</h4>

                        <Link to={`/rezervacii/user/${item.id}`}> <h4>Vidi rezervacii</h4> </Link>

                        <Link to={`/komentari/user/${item.id}`}> <h4>Vidi komentari</h4> </Link>

                        <button onClick={() => this.delete(item.id)}>Izbrisi</button>
                    </div>
                ))}

                <br/><br/>
                <Button onClick={modalOpen} className='btn btn-primary'>Dodaj</Button>

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
                            Додај сопственик
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Име</Form.Label>
                                <Form.Control type="text" placeholder="Име" ref={input => this.nameInput = input} />
                            </Form.Group>

                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Презиме</Form.Label>
                                <Form.Control type="text" placeholder="Презиме" ref={input => this.surNameInput = input} />
                            </Form.Group>

                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Адреса</Form.Label>
                                <Form.Control type="text" placeholder="Адреса" ref={input => this.adresaInput = input} />
                            </Form.Group>

                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Години</Form.Label>
                                <Form.Control type="text" placeholder="Години" ref={input => this.godiniInput = input} />
                            </Form.Group>

                            <Form.Group>
                                <Button className="btn btn-primary" onClick={this.addUser}>Додај</Button>
                            </Form.Group>

                        </Form>
                    </Modal.Body>
                </Modal>
            </div>


        );
    }
}


