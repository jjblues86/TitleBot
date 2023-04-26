// import React, { useContext, useEffect } from 'react';
// import UrlContext from './UrlContext';
// import axios from 'axios';
// import './UrlListComponent.css';



// function UrlListComponent() {
//     const { urls, setUrls } = useContext(UrlContext);
//     console.log(urls)

//     useEffect(() => {
//         const fetchUrls = async () => {
//           try {
//             const response = await axios.get('http://localhost:8080/api/title/urls');
//             setUrls(response.data);
//           } catch (error) {
//             console.error(error);
//           }
//         };
//         fetchUrls();
//       }, []);
  
//     return (
//       <div className="url-list">
//         <h2>History</h2>
//         <ul>
//           {urls.map((url, index) => (
//             <li key={index} className="url-item">
//               <span className="url-link">Url: {url}</span>
//             </li>
//           ))}
//         </ul>
//       </div>
//     );
//   }

// export default UrlListComponent;
