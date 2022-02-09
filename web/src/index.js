import React, {useState} from 'react';
import ReactDOM from 'react-dom';
import SockJsClient from 'react-stomp';


const SOCKET_URL = 'http://localhost:9001/ws-message';

const App = () => {
    const [message, setMessage] = useState('Placeholder');

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onMessageReceived = (msg) => {
        console.log(msg)
        console.log(message);
        setMessage(message + " \n " + msg)
    }

    return (
        <div>
            <SockJsClient
                url={SOCKET_URL}
                topics={['/topic/message']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={false}
            />
            <div>{message}</div>
        </div>
    );
}

ReactDOM.render(
    <App/>,
    document.querySelector('#root')
)
