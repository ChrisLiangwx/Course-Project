const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const TerserPlugin = require("terser-webpack-plugin");
const CopyPlugin = require('copy-webpack-plugin');


module.exports = {
    mode:"development",
    devtool: "inline-source-map",
    entry: {
        index: './src/index.tsx',
        SignIn: './src/SignIn.tsx',
    },
    output: {
        filename: "[name].bundle.js",
        path: path.resolve(__dirname, "dist"),
    },
    optimization: {
        minimize: true,
        minimizer: [new TerserPlugin()],
    },
    devServer: {
        static:"./dist",
    },


    plugins: [new CopyPlugin({
        patterns: [
            { from: 'src/asset', to: 'asset' },
        ],
    }),
        new HtmlWebpackPlugin({
            template: "./src/index.html",
            /*title:"mail",*/
            filename: "index.html",
            chunks: ['index'],
        }),
        new HtmlWebpackPlugin({
            template: './src/SignIn.html',
            filename: 'SignIn.html',
            chunks: ['SignIn']
        }),

    ],


    module: {
        rules: [{
            test:/\.css$/i,
            use:["style-loader", "css-loader"],
        },{
            test:/\.(png|svg|jpg|jpeg|gif)$/i,
            type: "asset/resource",
        },{
            test:/\.js$/,
            exclude: /node_modules/,
            use: {
                loader: "babel-loader",
                options: {
                    presets:["@babel/preset-env"],
                },
            },
        },{
            test: /\.tsx?$/,
            use: 'ts-loader',
            exclude: /node_modules/,
        },
        ]
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
}