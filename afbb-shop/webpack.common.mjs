import path from 'path';

const __dirname = import.meta.dirname;
const BUILD_DIR = path.resolve(__dirname, './build');
const SOURCE_DIR = path.resolve(__dirname, './src');

const rules = [{
  test: /\.(js|jsx)$/,
  loader: 'babel-loader',
  exclude: /node_modules/
}, {
  test: /\.css$/i,
  use: ['style-loader', 'css-loader']
}];

export default {
  entry: {
    index: [`${SOURCE_DIR}/App.js`]
  },
  module: {
    rules
  },
  output: {
    path: BUILD_DIR,
    filename: '[name].bundle.js',
    sourceMapFilename: '[name].map.js',
    clean: true
  },
  resolve: {
    extensions: ['', '.js', '.jsx']
  }
};
