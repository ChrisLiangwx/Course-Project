const express = require('express');
const path = require('path');
const requestModule = require("request");
const app = express();

//set default directory for static resources for express
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public/html/homepage.html'));
});

const PORT = 80;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
