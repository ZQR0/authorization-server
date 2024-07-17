import React, { Component, useState, useEffect } from "react";
import './AuthForm.css'


// const [email, setEmail] = useState('');
// const [password, setPassword] = useState('')

// function changeTitle() {
//   useEffect(() => {
//     document.title = 'Sign in with SSO';
//   }, []);
// }

export default class AuthForm extends Component {


    render() {
        return (
            <div className="formContainer">
                <p className="formTitle">SSO</p>

                <form method="post">
                  <p className="emailTitle">Email</p>
                  <input className="loginInputForm" type="text" id="email" name="username" required></input>

                  <p className="passwordTitle">Password</p>
                  <input className="passwordInputField" type="password" id="password" name="password" required></input>

                  <p></p>
                  <button className="signInButton">Sign In</button>

                  <p className="ifNotRegisteredTitle">If you're not registered</p>
                  <button className="signUpPageButton" type="submit">Sign Up</button>
                </form>

            </div>
        )
    }

}
