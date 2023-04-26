import React, { useState } from 'react';
import axios from 'axios';

function TitleBotComponent() {
    const [url, setUrl] = useState('');
    const [result, setResult] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
          const response = await axios.post('http://localhost:8080/api/title', { url });
          setResult(response.data);
        } catch (error) {
          console.error(error);
          setResult(null);
        }
      };

      return (
        <div>
          <form onSubmit={handleSubmit}>
            <label>
              URL:
              <input type="text" value={url} onChange={(event) => setUrl(event.target.value)} />
            </label>
            <button type="submit">Fetch</button>
          </form>
          {result && (
            <div>
              <p>
                {result && result.faviconUrl && (
                 <img src={result.faviconUrl} alt="favicon" />
                 )}
                <strong>{result.title}</strong>
              </p>
            </div>
          )}
        </div>
      );
}

export default TitleBotComponent; 

