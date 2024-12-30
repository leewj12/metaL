import React, { useState } from "react";
import axios from "axios";
import EditorComponent from "../components/EditorComponent";
import Header from "../components/Header";
import Footer from "../components/Footer";

function BoardWritePage() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("title", title);
    formData.append("content", content);

    try {
      const response = await axios.post("http://localhost:8081/api/posts", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      alert("게시글이 성공적으로 업로드되었습니다.");
    } catch (error) {
      console.error("게시글 업로드 중 오류:", error);
    }
  };

  return (
    <>
    <Header />
    <div style={{ padding: "20px" }}>
      <h1>게시판 업로드</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">제목:</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            style={{ width: "80%", padding: "8px", margin: "10px 0" }}
          />
        </div>
        <div>
          <EditorComponent value={content} onChange={setContent} />
        </div>
        <button type="submit" style={{ marginTop: "10px", padding: "10px" }}>
          제출하기기
        </button>
      </form>
    </div>
    <Footer />
    </>
  );
}

export default BoardWritePage;
