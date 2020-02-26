import React from 'react';
import './App.css';
import {Link} from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';

export default class Kategorija extends React.Component {


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

        fetch('http://localhost:8080/proizvoditel')
            .then(data => {
                data.json().then(item => {
                    this.setState({
                        items:item
                    }, () => console.log(this.state.items));
                })
            })


    };


    delete = (id) => {

        fetch(`http://localhost:8080/proizvoditel/delete/${id}`)
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



    addProizvoditel = () => {
        let proizvoditel = {
            name: this.nameInput.value,

        };

        fetch("http://localhost:8080/proizvoditel/add",{
            method: 'POST',
            body: JSON.stringify(proizvoditel),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                if(res.status === 200){
                    let items2 = this.state.items;
                    items2.push(proizvoditel);
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

                <h1>Site proizvoditeli: </h1>
                {this.state.items.map(item => (
                    <div>
                        <Link to={`/proizvoditel/${item.id}`}> <h2>{item.name}</h2> </Link>
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
                            Додај производител
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group controlId="exampleForm.ControlInput1">
                                <Form.Label>Име</Form.Label>
                                <Form.Control type="text" placeholder="Име" ref={input => this.nameInput = input} />
                            </Form.Group>

                            <Form.Group>
                                <Button className="btn btn-primary" onClick={this.addProizvoditel}>Додај</Button>
                            </Form.Group>

                        </Form>
                    </Modal.Body>
                </Modal>
            </div>


        );
    }
}




















///////////////////////////////////////////////////////////////////////////////////

/*

import React, {useState, useEffect} from 'react';
import './App.css';
import {Link} from 'react-router-dom';

function Proizvoditel() {

    useEffect( () => {
            fetchItems();
        }, []
    );

    const [items, setItems] = useState([]);

    const fetchItems = async () => {
        const data = await fetch(
            'http://localhost:8080/proizvoditel'
        );

        const items = await data.json();
        console.log(items);
        setItems(items);
    }

    return (
        <div>
            <h1>Site proizvoditeli: </h1>
            {items.map(item => (
                <div>

                    <Link to={`/proizvoditel/${item.id}`}> <h2>{item.name}</h2> </Link>
                </div>
            ))}
            <br/>
            <Link to='/shop'>Nazad kon vozila </Link>

        </div>
    );
}

export default Proizvoditel;
*/