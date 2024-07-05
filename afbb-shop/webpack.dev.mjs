import {merge} from 'webpack-merge';
import common from './webpack.common.mjs';

export default merge(common, {
  mode: 'development',
  devtool: 'inline-source-map',
  devServer: {
    proxy: [{
      context: ['/webflux'],
      target: 'http://afbb-webflux:8080'
    }, {
      context: ['/cdn'],
      target: 'http://afbb-cdn:8000'
    }],
    port: 3000
  }
});
