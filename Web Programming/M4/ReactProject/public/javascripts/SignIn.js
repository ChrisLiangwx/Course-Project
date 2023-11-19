"use strict";
function start() {
    class Validation {
        static isValidEmail(email) {
            const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            return emailRegex.test(email);
        }
    }
    class SignInComponent extends React.Component {
        constructor(props) {
            super(props);
            this.handleSignIn = () => {
                if (!Validation.isValidEmail(this.state.account)) {
                    alert("Invalid Email Account");
                    return;
                }
                if (this.state.account === 'admin@webmail.com' && this.state.password === '123456') {
                    this.setState({ isSignedIn: true });
                    document.getElementById('signin-status').textContent = 'Successful!';
                }
                else {
                    alert('Incorrect username or password');
                }
            };
            //check whether the account is a valid email address
            this.handleEmailChange = (e) => {
                const email = e.target.value;
                this.setState({ account: email });
                //set the state of emailError
                if (!Validation.isValidEmail(email)) {
                    this.setState({ emailError: 'Invalid email address' });
                }
                else {
                    this.setState({ emailError: '' }); // 清除错误消息
                }
            };
            this.state = {
                account: '',
                password: '',
                isSignedIn: false,
                emailError: '',
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
                    color: 'red',
                    fontSize: '20px'
                }
            };
        }
        render() {
            if (this.state.isSignedIn) {
                return React.createElement("div", null, "You are now signed in!");
            }
            //an error message will appear if email is invalid
            if (this.state.emailError) {
                return (React.createElement("div", null,
                    React.createElement("p", null,
                        "Account: ",
                        React.createElement("input", { type: "text", style: this.state.inputStyle, value: this.state.account, onChange: this.handleEmailChange })),
                    React.createElement("p", { style: this.state.errorStyle }, this.state.emailError),
                    React.createElement("p", null,
                        "Password: ",
                        React.createElement("input", { type: "password", style: this.state.inputStyle, value: this.state.password, onChange: (e) => this.setState({ password: e.target.value }) })),
                    React.createElement("button", { style: this.state.buttonStyle, onClick: this.handleSignIn }, "Sign In")));
            }
            return (React.createElement("div", null,
                React.createElement("p", null,
                    "Account: ",
                    React.createElement("input", { type: "text", style: this.state.inputStyle, value: this.state.account, onChange: this.handleEmailChange })),
                React.createElement("p", null,
                    "Password: ",
                    React.createElement("input", { type: "password", style: this.state.inputStyle, value: this.state.password, onChange: (e) => this.setState({ password: e.target.value }) })),
                React.createElement("button", { style: this.state.buttonStyle, onClick: this.handleSignIn }, "Sign In")));
        }
    }
    ReactDOM.render(React.createElement(SignInComponent, null), document.getElementById('react-root'));
}
