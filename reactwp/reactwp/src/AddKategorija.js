import React from 'react';
import axios from 'axios';

export default class AddKategorija extends React.Component {
    state = {
        name: 'DEfault',
    }

    handleChange = event => {
        this.setState({ name: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const user = {
            name: this.state.name
        };

        /*
        axios.post(`http://localhost:8080/kategorija/add`, { user })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
        */

        fetch(`http://localhost:8080/kategorija/add`, {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          } ,
          body: JSON.stringify({user})
        })
            .then(response => response.json());
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Kategorija Name:
                        <input type="text" name="name" onChange={this.handleChange} />
                    </label>
                    <br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}