import { useState, useEffect } from "react";
import axios from "axios";
import { useMainContext } from "../contexts/MainContext";
const endpoint = import.meta.env.VITE_API_URL_BASE;

function MappaItalia({ onRegioneClick }) {
  const { setIsLoading, fetchResponse } = useMainContext();
  //variabile dove salvo lista regioni
  const [regioni, setRegioni] = useState([]);

  //uso una variabile di stato per definire la regione da selezionare
  const [selezionaRegione, setSelezionaRegione] = useState(null);

  //eseguo la chiamata all'inizio per caricare tutte le regioni
  useEffect(() => {
    fetchResponse("api/regioni", setRegioni);
  }, []);

  return (
    <div className="flex flex-col items-center">
      <div className="h-12 text-2xl font-bold text-orange-500 mb-6 text-center tracking-wide drop-shadow-sm">
        {selezionaRegione
          ? `Assapora la Regione ${selezionaRegione}`
          : "Seleziona una regione sulla mappa"}
      </div>

      <div className="w-full max-w-xl bg-slate-900 p-6 rounded-3xl shadow-2xl border border-slate-800 transition-all duration-300">
        <svg
          viewBox="0 0 1000 1000"
          xmlns="http://www.w3.org/2000/svg"
          className="w-full h-full select-none"
        >
          <g id="features">
            {regioni.map((regione) => (
              <path
                key={regione.id}
                d={regione.svg_coordinate}
                id={regione.id}
                name={regione.slug}
                onMouseEnter={() => setSelezionaRegione(regione.nome)}
                onMouseLeave={() => setSelezionaRegione(null)}
                onClick={() => onRegioneClick(regione.slug)}
                className="fill-slate-100 stroke-slate-900 stroke-[3] cursor-pointer transition-all duration-200 hover:fill-orange-500 hover:stroke-white"
              ></path>
            ))}
          </g>
        </svg>
      </div>
    </div>
  );
}
export default MappaItalia;
