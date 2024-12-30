import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import MainSlider from "../components/MainSlider"; // Slider 컴포넌트 가져오기

function HomePage() {
  return (
    <div id="home">
    <Header />
    <main>
    <MainSlider />

    <section style={{ padding: "20px", textAlign: "center" }}>
      <h2>Additional Content</h2>
      <p>
        Here is some more content to demonstrate that the footer appears
        after the content ends.
      </p>
    </section>
    
    </main>

    <Footer />
    </div>
  );
}

export default HomePage;
