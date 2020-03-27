import React, {useState} from 'react';
import './App.css';
import firebase from "firebase/app";
import "firebase/auth";

firebase.initializeApp({
});

function App() {
    const [googleFirebaseToken, setGoogleFirebaseToken] = useState(firebase.auth().currentUser && firebase.auth().currentUser.getIdToken());

    const prepareRequestOptions = async ({method = "GET", data}) => {
        const token = await firebase.auth().currentUser.getIdToken();
        const request = {method, headers: {Authorization: `Bearer ${token}`}};
        if (data) {
            request.body = JSON.stringify(data);
            request.headers["Content-Type"] = "application/json";
        }
        return request;
    };

    function login(e, setGoogleFirebaseToken) {
        var event = e || window.event;
        const provider = new firebase.auth.GoogleAuthProvider();
        firebase.auth().languageCode = 'pl';
        provider.addScope('profile');
        provider.addScope('email');
        firebase.auth().signInWithPopup(provider).then(function (result) {
            const token = result.credential.accessToken;
            const user = result.user;
            firebase.auth().currentUser.getIdToken().then(
                function (fireBaseToken) {
                    console.log(`'${fireBaseToken}'`);
                    const uid = user.uid;
                    prepareRequestOptions({method: "GET"}).then(
                        function (options) {
                            fetch(`localhost:8080/data/${uid}`, options).then(
                                function (value) {
                                    setGoogleFirebaseToken(value)
                                }
                            )
                        }
                    )

                }
            );
        }).catch(function (error) {
            const errorCode = error.code;
            const errorMessage = error.message;
            const email = error.email;
            const credential = error.credential;
            console.log(`errorCode: ${errorCode}, errorMessage: ${errorMessage}, email: ${email}, credential: ${credential}`)
        });
    }

    return (
        <div className="App">
            <header className="App-header">
                <button onClick={e => login(e, setGoogleFirebaseToken)}>Login</button>
                <p style={{"max-width": "150px"}}>{googleFirebaseToken != null && googleFirebaseToken}</p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
            </header>
        </div>
    );
}

export default App;
