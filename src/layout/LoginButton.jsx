export default function LoginButton({ children, clickEvent }) {
  return (
    <>
      <button
        className="bg-orange-700 text-white py-2 px-4 rounded hover:animate-grow"
        onClick={clickEvent}
      >
        {children}
      </button>
    </>
  );
}
