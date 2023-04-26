import './App.css';
import TitleBotComponent from './component/TitleBotComponent';
import UrlContext from './component/UrlContext';
import { useState } from 'react';

function App() {
  const [urls, setUrls] = useState([]);

  const addUrl = (url) => {
    setUrls((prevUrls) => [...prevUrls, url]);
  };

  return (
    <div className="App">
      <UrlContext.Provider value={{ urls, addUrl }}>
        <TitleBotComponent />
      </UrlContext.Provider>
    </div>
  );
}

export default App;
