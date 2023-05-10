import './Header.css';
function Header() {
    return (
        <div className={"header"}>
            <div className={"navigation"}>
                <h2>MusicMate</h2>
                <div>
                    <ul>
                        <li>Home</li>
                        <li>Explore</li>
                        <li>Log In</li>
                    </ul>
                </div>
                <div>
                    <button>Upload</button>
                </div>
            </div>
        </div>
    )
}

export default Header;