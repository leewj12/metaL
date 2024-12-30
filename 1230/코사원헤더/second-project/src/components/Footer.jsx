import React from "react";
import styles from "../css/Footer.module.css";
import logo from "../assets/images/logo-2.png";

const Footer = () => {
  return (
    <footer className={styles.footer}>
      {/* Footer Top */}
      <div className={`${styles.footerTop} pt-40 pb-40`}>
        <div className="container">
            <div className={`${styles.footerContent}`}>
            {/* About Section */}
            <div className="col-lg-4 col-md-6">
              <div className="footer-about mt-40">
                <div className={styles.logo}>
                  <a href="#">
                    <img src={logo} alt="Logo" className={styles.logoImage} />
                  </a>
                </div>
                <p>
                  Komon
                </p>
                <ul className={`${styles.socialIcons} mt-20`}>
                  <li>
                    <a href="#">
                      <i className="fa fa-facebook-f"></i>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="fa fa-twitter"></i>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="fa fa-google-plus"></i>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="fa fa-instagram"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            {/* Sitemap Section */}
            <div className="col-lg-2 col-md-6 col-sm-6 ">
              <div className="footer-link mt-40">
                <div className={styles.footerTitle}>
                    <h6 className={styles.sectionTitle}>Sitemap</h6>
                </div>
                <ul className={styles.listUnstyled}>
                  <li>
                    <a href="#">
                      <i className="bi bi-house-door-fill"></i> Home
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-info-circle-fill"></i> About Us
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-book-fill"></i> Courses
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-newspaper"></i> News
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-calendar-event-fill"></i> Event
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            {/* Support Section */}
            <div className="col-lg-2 col-md-6 col-sm-6">
              <div className="footer-link mt-40">
                <div className={styles.footerTitle}>
                <h6 className={styles.sectionTitle}>Support</h6>
                </div>
                <ul className={styles.listUnstyled}>
                  <li>
                    <a href="#">
                      <i className="bi bi-question-circle-fill"></i> FAQS
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-shield-lock-fill"></i> Privacy
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-card-checklist"></i> Policy
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-chat-dots-fill"></i> Support
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="bi bi-file-earmark-text-fill"></i> Documentation
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            {/* Contact Us Section */}
            <div className="col-lg-3 col-md-6">
              <div className="footer-address mt-40">
                <div className={styles.footerTitle}>
                <h6 className={styles.sectionTitle}>Contact Us</h6>
                </div>
                <ul className={styles.contactUs}>
                  <li>
                      <i className="bi bi-geo-alt-fill"></i>{" "}
                      서울 서초구 동작대로 132, 안석빌딩 9층
                  </li>
                  <li>
                    <i className="bi bi-telephone-fill"></i> 02-599-8697
                  </li>
                  <li>
                    <i className="bi bi-envelope-fill"></i> komon@naver.com
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* Footer Copyright */}
      <div className={styles.footerCopyright}>
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <div className="copyright text-center pt-10">
                    copyright@KOMON
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
