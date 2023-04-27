import './App.css';
import Footer from './component/Footer';
import TitleBotComponent from './component/TitleBotComponent';
import UrlContext from './context/UrlContext';
import { useState } from 'react';

function App() {
  // the urls state will be used to store the urls entered by the user
  const [urls, setUrls] = useState([]);

  // addUrl function will be used to add the url to the urls state
  const addUrl = (url) => {
    setUrls((prevUrls) => [...prevUrls, url]);
  };

  return (
    <div className="App">
      <header>
        <nav>
          <h1 className="mb-4 text-center">Welcome to the Title Bot App!</h1>
        </nav>
      </header>
      <UrlContext.Provider value={{ urls, addUrl }}>
        <TitleBotComponent />
      </UrlContext.Provider>
      <Footer />
    </div>
  );
}

export default App;
