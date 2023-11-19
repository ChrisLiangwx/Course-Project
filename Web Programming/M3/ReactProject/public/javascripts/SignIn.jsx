function start(){
    class SignInComponent extends React.Component {
        constructor(props) {
            super(props);
            this.state = {
                account: '',
                password: '',
                isSignedIn: false,
                inputStyle: {
                    padding: '10px',
                    margin: '10px 0',
                    borderRadius: '5px',
                    border: '1px solid #ddd'
                },
                buttonStyle: {
                    padding: '10px 15px',
                    margin: '10px 0',
                    borderRadius: '5px',
                    border: 'none',
                    backgroundColor: '#333',
                    color: 'white',
                    cursor: 'pointer'
                }
            };
        }

        handleSignIn = () => {
            if (this.state.account === 'admin' && this.state.password === '123456') {
                this.setState({ isSignedIn: true });
                document.getElementById('signin-status').textContent = 'Successful!';
            } else {
                alert('Incorrect username or password');
            }
        };

        render() {
            //if handleSignIn hasn't been used to change isSignedIn
            if (this.state.isSignedIn) {
                return <div>You are now signed in!</div>;
            }
            //set the input and button
            return (
                <div>
                    <p>Account: <input type="text" style={this.state.inputStyle} value={this.state.account} onChange={(e) => this.setState({ account: e.target.value })} /></p>
                    <p>Password: <input type="password" style={this.state.inputStyle} value={this.state.password} onChange={(e) => this.setState({ password: e.target.value })} /></p>
                    <button style={this.state.buttonStyle} onClick={this.handleSignIn}>Sign In</button>
                </div>
            );
        }
    }


    ReactDOM.render(<SignInComponent />, document.getElementById('react-root'));
}
