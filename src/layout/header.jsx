import axios from "axios";
import { useState, useEffect } from "react";
import LoginButton from "./LoginButton";
import { Outlet } from "react-router-dom";
const naverLogin = () => {
  window.location.href = "http://localhost:8080/oauth2/authorization/naver";
};
const getData = (setUserInfo) => {
  axios
    .get("http://localhost:8080/my", { withCredentials: true })
    .then((res) => {
      setUserInfo(res.data);
    })
    .catch((error) => console.log(error));
};
const showDetailData = (userInfo) => {
  alert(userInfo.name);
};
const logout = () => {
  window.location.href = "http://localhost:8080/logout";
};
export default function HeaderLayout() {
  const [userInfo, setUserInfo] = useState(undefined);

  useEffect(() => {
    if (userInfo === undefined) {
      console.log("현재널");
      getData(setUserInfo);
    }
  }, []);
  return (
    <>
      <header className="flex items-center justify-between p-4 bg-orange-300 shadow-md">
        <div className="flex items-center space-x-2">
          <p className="text-lg font-bold bg-orange-500 px-4 py-2 rounded-lg shadow">
            {userInfo
              ? userInfo.regDate + "에 로그인하였습니다."
              : "로그인 하세요~"}
          </p>
        </div>

        {/* 오른쪽 - 버튼 그룹 */}
        <div className="flex space-x-4">
          <LoginButton
            clickEvent={
              userInfo === undefined
                ? () => naverLogin(setUserInfo)
                : () => showDetailData(userInfo)
            }
          >
            {userInfo === undefined
              ? "네이버로그인"
              : userInfo.name + "계정정보 확인"}
          </LoginButton>
          <LoginButton clickEvent={logout}>로그아웃(아직 미구현)</LoginButton>
        </div>
      </header>
      <Outlet />
    </>
  );
}
