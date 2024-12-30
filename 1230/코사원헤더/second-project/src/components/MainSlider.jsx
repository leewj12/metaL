import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styles from "../css/MainSlider.module.css";

function MainSlider() {
  const sliderSettings = {
    dots: true,            // 하단에 슬라이더 네비게이션 점 표시
    infinite: true,        // 무한 루프 활성화
    speed: 1000,            // 슬라이드 전환 속도 (ms)
    fade: true,            // 슬라이드 간 전환 효과를 페이드로 설정
    slidesToShow: 1,       // 한 번에 표시되는 슬라이드 개수
    slidesToScroll: 1,     // 한 번에 넘어가는 슬라이드 개수
    autoplay: true,        // 슬라이더 자동 재생
    autoplaySpeed: 5000,   // 자동 재생 속도 (ms)
    cssEase: "ease-in-out", // 부드러운 전환 효과
  };

  return (
    <section className={styles.sliderActive}>
      <Slider {...sliderSettings}>
        <div>
        <img src="/images/slider/s1.png" alt="Slider Image" className={styles.bgCover} />
        </div>
        <div>
        <img src="/images/slider/s2.png" alt="Slider Image" className={styles.bgCover} />
        </div>
        <div>
        <img src="/images/slider/s3.png" alt="Slider Image" className={styles.bgCover} />
        </div>
      </Slider>
    </section>
  );
}

export default MainSlider;
