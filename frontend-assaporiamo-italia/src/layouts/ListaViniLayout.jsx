// import outlet
import { Outlet } from "react-router-dom";
//importo
import { useMainContext } from "../contexts/MainContext";

import "../styles/LoaderStyle.css";

//importo componente loader
import Loader from "../components/Loader";
import Navbar from "../components/Navbar";

function ListaViniiLayout() {
  //definisco isloading
  const { isLoading } = useMainContext();
  return (
    <>
      <Navbar tipoProdotto={"vino"} />
      <main>
        <Outlet />
        {isLoading && <Loader />}
      </main>
    </>
  );
}

export default ListaViniiLayout;
