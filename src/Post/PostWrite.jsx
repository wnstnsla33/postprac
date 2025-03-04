import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
export default function PostWrite() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const navigate = useNavigate(); // ✅ 페이지 이동을 위한 useNavigate 사용

  // body: JSON.stringify({ title, content }),
  const handleSubmit = () => {
    axios.post(
      "http://localhost:8080/post/new",
      { title, content },
      {
        headers: { "Content-Type": "application/json" },
        withCredentials: true,
      }
    );
    navigate("/");
  };
  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-orange-100 rounded-lg shadow-md">
      <h2 className="text-2xl font-bold text-orange-600 mb-4">게시글 작성</h2>
      <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
        {/* 제목 입력 */}
        <input
          type="text"
          placeholder="제목을 입력하세요"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="p-3 border border-orange-300 rounded-lg focus:ring-2 focus:ring-orange-500 outline-none"
          required
        />

        {/* 내용 입력 */}
        <textarea
          placeholder="내용을 입력하세요"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          rows="5"
          className="p-3 border border-orange-300 rounded-lg focus:ring-2 focus:ring-orange-500 outline-none"
          required
        ></textarea>

        {/* 버튼 */}
        <button
          type="submit"
          className="bg-orange-500 text-white py-2 px-4 rounded-lg hover:bg-orange-600 transition"
        >
          게시글 작성
        </button>
      </form>
    </div>
  );
}
