import React from 'react';
import './App.css';

function SignIn() {
    return (
        <div style={{'border':'1px solid black', 'display':'inline-block'}}>
            <br/>
            <label>UserName: </label>
            <input type="text" placeholder="UserName" name="username"/>

            <br/> <br/>
            <label>Password: </label>
            <input type="password" name="password"/>

            <br/> <br/>
            <button type="submit">Sign in</button>
            <br/>
        </div>



    );
}

export default SignIn;