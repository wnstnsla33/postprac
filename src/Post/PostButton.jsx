import { Link } from "react-router-dom";
export default function PostButton({ children }) {
  return (
    <>
      <Link to={"/post/new"}>
        <button>{children}</button>
      </Link>
    </>
  );
}
