import React from 'react';

const UrlContext = React.createContext({
  urls: [],
  addUrl: () => {},
});

export default UrlContext;
