import axios from "axios";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";

export default function PostDetail() {
  const { id } = useParams(); // URL에서 id 값 가져오기
  const [detail, setDetail] = useState(null); // 상태 관리

  useEffect(() => {
    axios
      .get(`http://localhost:8080/post/${id}`, { withCredentials: true })
      .then((res) => setDetail(res.data)) // 데이터를 상태에 저장
      .catch((error) => console.log(error));
  }, [id]); // id가 변경될 때마다 실행

  if (!detail) {
    return <p>Loading...</p>; // 데이터가 로드되지 않았을 때 로딩 표시
  }

  return (
    <>
      <h3>{detail.title}</h3>
      <p>{detail.content}</p>
      <p>작성자: {detail.userName}</p>
      <p>조회수: {detail.viewCount}</p>
      <p>작성일: {detail.createdDate}</p>
      <p>수정일: {detail.lastModifiedDate}</p>
    </>
  );
}
