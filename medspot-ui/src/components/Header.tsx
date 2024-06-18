import { Link } from "react-router-dom"

function Header() {
    return (
        <div className='header'>
          <h2>MedSpot</h2>
            <Link to="/" style={{ textDecoration: 'none' }}>
            <h3>Home</h3>
            </Link>
        </div>
    )
  }
  
  export default Header