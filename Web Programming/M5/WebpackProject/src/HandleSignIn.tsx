import React from 'react';
import ReactDOM from 'react-dom'
export function start(){
    class Validation{

        static isValidEmail(email: string): boolean {
            const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            return emailRegex.test(email);
        }

        /*static isValidPassword(password: string): boolean {
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
            return passwordRegex.test(password);
        }*/

    }

    interface SignInComponentState {
        account: string;
        password: string;
        isSignedIn: boolean;
        emailError : string;
        inputStyle: React.CSSProperties;
        buttonStyle: React.CSSProperties;
        errorStyle: React.CSSProperties;
    }

    class SignInComponent extends React.Component<{}, SignInComponentState> {
        constructor(props: {}) {
            super(props);
            this.state = {
                account: '',
                password: '',
                isSignedIn: false,
                emailError : '',
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
                },
                errorStyle: {
                    color:'red',
                    fontSize:'20px'
                }
            };
        }

        handleSignIn = () => {
            if(!Validation.isValidEmail(this.state.account)){
                alert("Invalid Email Account");
                return;
            }
            if (this.state.account === 'admin@webmail.com' && this.state.password === '123456') {
                this.setState({ isSignedIn: true });
                document.getElementById('signin-status')!.textContent = 'Successful!';
            } else {
                alert('Incorrect username or password');
            }
        };

        //check whether the account is a valid email address
        handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
            const email = e.target.value;
            this.setState({ account: email });
            //set the state of emailError
            if (!Validation.isValidEmail(email)) {
                this.setState({ emailError: 'Invalid email address' });
            } else {
                this.setState({ emailError: '' });
            }
        };

        render() {
            if (this.state.isSignedIn) {
                return <div>You are now signed in!</div>;
            }
            //an error message will appear if email is invalid
            if(this.state.emailError){
                return(
                    <div>
                        <p>Account: <input type="text" style={this.state.inputStyle} value={this.state.account} onChange={this.handleEmailChange} /></p>
                        <p style={this.state.errorStyle}>{this.state.emailError}</p>
                        <p>Password: <input type="password" style={this.state.inputStyle} value={this.state.password} onChange={(e) => this.setState({ password: e.target.value })} /></p>
                        <button style={this.state.buttonStyle} onClick={this.handleSignIn}>Sign In</button>
                    </div>
                    );
            }
            return (
                <div>
                    <p>Account: <input type="text" style={this.state.inputStyle} value={this.state.account} onChange={this.handleEmailChange} /></p>
                    <p>Password: <input type="password" style={this.state.inputStyle} value={this.state.password} onChange={(e) => this.setState({ password: e.target.value })} /></p>
                    <button style={this.state.buttonStyle} onClick={this.handleSignIn}>Sign In</button>
                </div>
            );
        }
    }

    ReactDOM.render(<SignInComponent />, document.getElementById('react-root'));

}
