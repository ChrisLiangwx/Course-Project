"use strict";

function _typeof(o) { "@babel/helpers - typeof"; return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) { return typeof o; } : function (o) { return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o; }, _typeof(o); }
function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }
function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, _toPropertyKey(descriptor.key), descriptor); } }
function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }
function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); Object.defineProperty(subClass, "prototype", { writable: false }); if (superClass) _setPrototypeOf(subClass, superClass); }
function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf ? Object.setPrototypeOf.bind() : function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }
function _createSuper(Derived) { var hasNativeReflectConstruct = _isNativeReflectConstruct(); return function _createSuperInternal() { var Super = _getPrototypeOf(Derived), result; if (hasNativeReflectConstruct) { var NewTarget = _getPrototypeOf(this).constructor; result = Reflect.construct(Super, arguments, NewTarget); } else { result = Super.apply(this, arguments); } return _possibleConstructorReturn(this, result); }; }
function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } else if (call !== void 0) { throw new TypeError("Derived constructors may only return object or undefined"); } return _assertThisInitialized(self); }
function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }
function _isNativeReflectConstruct() { if (typeof Reflect === "undefined" || !Reflect.construct) return false; if (Reflect.construct.sham) return false; if (typeof Proxy === "function") return true; try { Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {})); return true; } catch (e) { return false; } }
function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf.bind() : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }
function _defineProperty(obj, key, value) { key = _toPropertyKey(key); if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return _typeof(key) === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (_typeof(input) !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (_typeof(res) !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }
function start() {
  var SignInComponent = /*#__PURE__*/function (_React$Component) {
    _inherits(SignInComponent, _React$Component);
    var _super = _createSuper(SignInComponent);
    function SignInComponent(props) {
      var _this;
      _classCallCheck(this, SignInComponent);
      _this = _super.call(this, props);
      _defineProperty(_assertThisInitialized(_this), "handleSignIn", function () {
        if (_this.state.account === 'admin' && _this.state.password === '123456') {
          _this.setState({
            isSignedIn: true
          });
          document.getElementById('signin-status').textContent = 'Successful!';
        } else {
          alert('Incorrect username or password');
        }
      });
      _this.state = {
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
      return _this;
    }
    _createClass(SignInComponent, [{
      key: "render",
      value: function render() {
        var _this2 = this;
        if (this.state.isSignedIn) {
          return /*#__PURE__*/React.createElement("div", null, "You are now signed in!");
        }
        return /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("p", null, "Account: ", /*#__PURE__*/React.createElement("input", {
          type: "text",
          style: this.state.inputStyle,
          value: this.state.account,
          onChange: function onChange(e) {
            return _this2.setState({
              account: e.target.value
            });
          }
        })), /*#__PURE__*/React.createElement("p", null, "Password: ", /*#__PURE__*/React.createElement("input", {
          type: "password",
          style: this.state.inputStyle,
          value: this.state.password,
          onChange: function onChange(e) {
            return _this2.setState({
              password: e.target.value
            });
          }
        })), /*#__PURE__*/React.createElement("button", {
          style: this.state.buttonStyle,
          onClick: this.handleSignIn
        }, "Sign In"));
      }
    }]);
    return SignInComponent;
  }(React.Component);
  ReactDOM.render( /*#__PURE__*/React.createElement(SignInComponent, null), document.getElementById('react-root'));
}
