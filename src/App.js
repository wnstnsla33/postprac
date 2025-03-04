import { createBrowserRouter, RouterProvider } from "react-router-dom";
import HeaderLayout from "./layout/header"; // 파일 이름 맞추기
import MyPage from "./myPage/MyPage";
import AsideMenu from "./layout/AsideMenu";
import Post from "./Post/Post";
import PostWrite from "./Post/PostWrite";
import PostDetail from "./Post/PostDetail";
const route = createBrowserRouter([
  {
    path: "/",
    element: (
      <div className="flex h-screen">
        <AsideMenu />
        <div className="flex-1 flex flex-col">
          <HeaderLayout />
        </div>
      </div>
    ),
    children: [
      { path: "/post", element: <Post /> },
      { path: "/home", element: <MyPage /> },
      { path: "/post/new", element: <PostWrite /> },
      { path: "/post/:id", element: <PostDetail /> },
    ],
  },
]);

function App() {
  return <RouterProvider router={route} />;
}

export default App;
