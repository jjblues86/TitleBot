import './App.css';
import TitleBotComponent from './component/TitleBotComponent';
import UrlContext from './component/UrlContext';
// import UrlListComponent from './component/UrlListComponent';
import { useState } from 'react';

function App() {
  const [urls, setUrls] = useState([]);

  const addUrl = (url) => {
    console.log(url);
    setUrls((prevUrls) => [...prevUrls, url]);
  };

  return (
    <div className="App">
      <UrlContext.Provider value={{ urls, addUrl }}>
        <TitleBotComponent />
        {/* <UrlListComponent /> */}
      </UrlContext.Provider>
    </div>
  );
}

export default App;
