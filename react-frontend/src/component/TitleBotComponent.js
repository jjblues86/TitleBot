import React, { useState, useContext, useEffect } from 'react';
import axios from 'axios';
import { Container, Row, Col, Form, Button, Table } from 'react-bootstrap';
import UrlContext from '../context/UrlContext';

function TitleBotComponent() {
  const [url, setUrl] = useState('');
  const [result, setResult] = useState(null);
  const [titles, setTitles] = useState([]);
  const [userId, setUserId] = useState(localStorage.getItem('userId') || '');

  const { addUrl } = useContext(UrlContext);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/title', { url, userId });
      console.log(response);
      setResult(response.data);
      if (Array.isArray(response.data)) {
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
  
  const fetchTitlesByUser = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/title/urls");
      console.log(response);
      console.log(response.data);
      setTitles(response.data);
    } catch (error) {
      console.error(error);
      setTitles([]);
    } setTimeout (fetchTitlesByUser, 1000);
  };

  useEffect(() => {
    fetchTitlesByUser();
    localStorage.setItem('userId', userId);
  }, [userId]);

  return (
    <Container>
      <Row className="mt-5">
        <Col xs={12} md={8} lg={6} className="mx-auto">
          <Form onSubmit={handleSubmit}>
            <Form.Group>
              <Form.Label>Please enter the URL:</Form.Label>
              <Form.Control type="text" value={url} onChange={(event) => setUrl(event.target.value)} />
            </Form.Group>
            <Form.Group>
              <Form.Label>Please enter your user ID:</Form.Label>
              <Form.Control type="text" value={userId} onChange={(event) => setUserId(event.target.value)} />
            </Form.Group>
            <Button type="submit" variant="primary" className="my-3">Fetch</Button>
          </Form>
          {result && (
            <div>
              <p>
                {result && result.faviconUrl && (
                  <img src={result.faviconUrl} alt="favicon" className="mr-2" />
                )}
                <strong>{result.title}</strong>
              </p>
            </div>
          )}

          <h3>History</h3>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Title</th>
                <th>URL</th>
              </tr>
            </thead>
            <tbody>
              {titles.map((title) => (
                <tr key={title.id}>
                  <td>{title.title}</td>
                  <td>{title.url}</td>
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