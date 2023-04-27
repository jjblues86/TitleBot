import React, { useState, useContext, useEffect } from 'react';
import axios from 'axios';
import { Container, Row, Col, Form, Button, Table } from 'react-bootstrap';
import UrlContext from '../context/UrlContext';

function TitleBotComponent() {
  const [url, setUrl] = useState(''); // the URL is stored here (empty by default) 
  const [result, setResult] = useState(null); // the result of the title fetch is stored here (null by default) 
  const [titles, setTitles] = useState([]); // the titles are stored here (empty by default) 
  const [userId, setUserId] = useState(localStorage.getItem('userId') || ''); // the user id is stored here (empty by default) 

  // The following function is used to fetch the title of a given URL.
  const { addUrl } = useContext(UrlContext);

  // fetch title by url and user id (if user id is provided) 
  const handleSubmit = async (event) => {
    event.preventDefault(); // the following line prevents the page from refreshing when the form is submitted
    try {
      const response = await axios.post('http://localhost:8080/api/title', { url, userId });
      setResult(response.data);
      if (Array.isArray(response.data)) { // if the response is an array, then add the titles to the titles array (if user id is provided) 
        setTitles(...titles, response.data);
      } else  {
        setTitles([response.data]);
      } 
      addUrl(url);
    } catch (error) {
      console.error(error);
      setResult(null);
    }
  };

  // delete title by id 
  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/title/${id}`);
      setTitles(titles.filter((title) => title.id !== id));
    } catch (error) {
      console.error(error);
    }
  };
  
  
  // fetch title by user id (if user id is provided)
  const fetchTitlesByUser = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/title/urls");;
      setTitles(response.data);
    } catch (error) {
      console.error(error);
      setTitles([]);
    } setTimeout (fetchTitlesByUser, 1000); // fetch the title every second (if user id is provided) this helps to prevent the page from rendering only the new requests

  };

  // runs every time userId changes (whenever user changes) 
  useEffect(() => {
    fetchTitlesByUser();
    localStorage.setItem('userId', userId); // stores the user id in local storage (if provided)
  }, [userId]);

  return (
    <Container>
      <Row className="mt-5">
        <Col xs={12} md={8} lg={6} className="mx-auto">
          <Form onSubmit={handleSubmit}>
            <Form.Group>
              <Form.Label className="font-weight-bold mb-3"></Form.Label>
              <Form.Control type="text" value={url} placeholder="Enter your URL" onChange={(event) => setUrl(event.target.value)} />
            </Form.Group>
            <Form.Group>
              <Form.Label className="font-weight-bold mb-3" style={{ paddingTop: "20px" }} optional="true"></Form.Label>
              <Form.Control type="text" value={userId} placeholder="Enter your user ID (Optional)" onChange={(event) => setUserId(event.target.value)} />
            </Form.Group>
            <Button type="submit" variant="primary" className="my-3">Fetch</Button>
          </Form>
          {result && (
            <div style={{ backgroundColor: '#f2f2f2', padding: '10px' }}>
              <p>
                {result && result.faviconUrl && (
                  <img src={result.faviconUrl} alt="favicon" className="mr-2" />
                )}
                <strong>{result.title}</strong>
              </p>
            </div>
          )}

          <h3 style={{ paddingTop: '30px' }} className="font-weight-bold mb-2">History</h3>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Title</th>
                <th>URL</th>
                <th>Delete</th> 
              </tr>
            </thead>
            <tbody>
              {titles.map((title) => (
                <tr key={title.id}>
                  <td>{title.title}</td>
                  <td><a href={title.url} target="_blank">{title.url}</a></td>
                  <td>
                     <Button variant="danger" onClick={() => handleDelete(title.id)}>Delete</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Col>
      </Row>
    </Container>
  );
}

export default TitleBotComponent;