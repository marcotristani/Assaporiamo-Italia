import { useState, useContext, createContext, useEffect } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

const MainContext = createContext();

export function MainProvider({ children }) {
  const [isLoading, setIsLoading] = useState(false);
  const endpointBase = import.meta.env.VITE_API_URL_BASE;

  const location = useLocation();
  const navigate = useNavigate();

  const [tipoProdottoCorrente, setTipoProdottoCorrente] = useState("");

  const queryAttuali = new URLSearchParams(location.search);
  const categoriaSlug = queryAttuali.get("categoria") || "";

  const querySenzaCategoria = new URLSearchParams(location.search);
  querySenzaCategoria.delete("categoria");
  const stringaQueryRimanenti = querySenzaCategoria
    ? `?${querySenzaCategoria.toString()}`
    : "";

  function fetchResponse(fineEndpoint, setter) {
    setIsLoading(true);
    const urlCompleto = `${endpointBase}${fineEndpoint}`;

    axios
      .get(urlCompleto)
      .then((res) => {
        setter(res.data);
      })
      .catch((err) => {
        console.error("Errore nel caricamento dati:", err);
        if (err.response) {
          if (err.response.status === 404) {
            return navigate("/not-found");
          }
        }

        navigate("/error-system");
      })
      .finally(() => {
        setIsLoading(false);
      });
  }

  return (
    <MainContext.Provider
      value={{
        isLoading,
        setIsLoading,
        fetchResponse,
        tipoProdottoCorrente,
        setTipoProdottoCorrente,
        categoriaSlug,
        stringaQueryRimanenti,
      }}
    >
      {children}
    </MainContext.Provider>
  );
}

export function useMainContext() {
  const context = useContext(MainContext);
  return context;
}
