// import outlet
import { Outlet } from "react-router-dom";
//importo
import { useMainContext } from "../contexts/MainContext";

import "../styles/LoaderStyle.css";

//importo componente loader
import Loader from "../components/Loader";
import NavbarBase from "../components/NavbarBase";

function BaseLayout() {
  //definisco isloading
  const { isLoading } = useMainContext();
  return (
    <>
      <NavbarBase />
      <main>
        <Outlet />
        {isLoading && <Loader />}
      </main>
    </>
  );
}

export default BaseLayout;
