import { ArrowUpDown } from "lucide-react";
import { useMainContext } from "../contexts/MainContext";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function BottoneOrdinamento() {
  const navigate = useNavigate();
  const [isOrdinato, setIsOrdinato] = useState(false);

  useEffect(() => {
    const query = new URLSearchParams(location.search);
    setIsOrdinato(query.get("order") === "alfabetico");
  }, [location.search]);

  function gestisciOrdinamento() {
    const query = new URLSearchParams(location.search);

    if (query.get("order") === "alfabetico") {
      query.delete("order");
    } else {
      query.set("order", "alfabetico");
    }

    navigate(`${location.pathname}?${query.toString()}`);
  }
  return (
    <button
      onClick={gestisciOrdinamento}
      className={`inline-flex items-center justify-center px-4 py-2.5 rounded-xl text-sm font-medium transition-all duration-200 border shadow-sm shrink-0 ${
        isOrdinato
          ? "bg-amber-600 text-white border-amber-600 hover:bg-amber-700"
          : "bg-white text-slate-700 border-slate-200 hover:bg-slate-50 hover:text-amber-700"
      }`}
    >
      <ArrowUpDown
        className={`h-4 w-4 mr-2 ${isOrdinato ? "text-white" : "text-slate-400"}`}
      />
      {isOrdinato ? "Ordinato Alfabeticamente" : "Ordina Alfabeticamente"}
    </button>
  );
}
export default BottoneOrdinamento;
