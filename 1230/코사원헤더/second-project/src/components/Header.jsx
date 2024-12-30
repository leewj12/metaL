import React from "react";
import styles from "../css/Header.module.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css"; // Bootstrap Icons
import logo from "../assets/images/logo.png";

const Header = () => {
  return (
    <header>
      {/* Top Bar */}
      <div className={styles.topBar}>
        <div className="container d-flex justify-content-between align-items-center">
          <div className="d-flex align-items-center gap-3">
            <div>
              <i className="bi bi-geo-alt-fill"></i> 서울 서초구 동작대로 132, 안석빌딩 9층
            </div>
            <div>
              <i className="bi bi-envelope-fill"></i> komon@naver.com
            </div>
          </div>
          <div>Opening Hours: Monday to Saturday - 8 AM to 5 PM</div>
        </div>
      </div>

      {/* Main Header */}
      <div className="container py-3 d-flex justify-content-between align-items-center">
      <div className="d-flex align-items-center gap-3">
          <img src={logo} alt="Logo" style={{ height: "50px" }} />
          <h1 className={styles.logoText}>KOMON</h1>
        </div>
        <div className="d-flex align-items-center gap-3">
          <div className="d-flex align-items-center gap-2">
            <i className="bi bi-headset"></i>
            <div>
              <p className="mb-0">Need Help? Call us free</p>
              <strong>02-599-8697</strong>
            </div>
          </div>
          <button className="btn btn-warning">Apply Now</button>
        </div>
      </div>

      {/* Navigation */}
      <nav className={`navbar navbar-expand-lg bg-white border-top border-bottom ${styles.navbar}`}>
        <div className="container">
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link active" href="#">
                  Home
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  About Us
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  Courses
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  Events
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  Blog
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  Shop
                </a>
              </li>
              <li className={`nav-item ${styles.navItem}`}>
                <a className="nav-link" href="#">
                  Contact
                </a>
              </li>
            </ul>
          </div>
          <div className="d-flex align-items-center gap-3">
            <i className={`bi bi-search ${styles.navLink}`}></i>
            <i className={`bi bi-cart-fill ${styles.navLink}`}></i>
            <span className={`badge ${styles.badgeHighlight}`}>0</span>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Header;
