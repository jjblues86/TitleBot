import React from 'react';

// Create a context object to hold our urls and 
// functions to manipulate them 
const UrlContext = React.createContext({
  urls: [],
  addUrl: () => {},
});

export default UrlContext;
