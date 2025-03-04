import { useState, useEffect } from "react";
import axios from "axios";
import PostButton from "./PostButton";
import { Link } from "react-router-dom";
const getPostList = (setPostList) => {
  axios
    .get("http://localhost:8080/post", { withCredentials: true })
    .then((res) => setPostList(res.data))
    .catch((error) => console.log(error));
};

export default function Post() {
  const [postList, setPostList] = useState([]);
  useEffect(() => {
    getPostList(setPostList);
  }, []);
  const printList = (postList) => {
    return postList.map((post) => (
      <div key={post.postId}>
        <h3>{post.title}</h3>
        <p>{post.content}</p>
        <p>작성자: {post.userName}</p>
        <p>조회수: {post.viewCount}</p>
        <p>작성일: {post.createdDate}</p>
        <p>수정일: {post.lastModifiedDate}</p>
        <Link to={`/post/${post.postId}`} className="text-blue-500 underline">
          {post.title}
        </Link>
      </div>
    ));
  };
  const noPost = () => {
    return (
      <div>
        <PostButton>{"새 글쓰기"}</PostButton>
      </div>
    );
  };
  return (
    <>
      <div>
        <h2>{postList.length > 0 ? printList(postList) : noPost()}</h2>
      </div>
    </>
  );
}
