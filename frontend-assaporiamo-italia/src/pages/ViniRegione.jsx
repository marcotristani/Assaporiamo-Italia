import { Link, useParams } from "react-router-dom";
import { useMainContext } from "../contexts/MainContext";
import { useState, useEffect } from "react";
import CardProdotto from "../components/CardProdotto";
import BottoneOrdinamento from "../components/BottoneOrdinamento";
import BottoneIndietro from "../components/BottoneIndietro";
import BottoneTuttiProdotti from "../components/BottoneTuttiProdotti";

function ViniRegione() {
  const { slugRegione } = useParams();
  const { fetchResponse, stringaQueryRimanenti, categoriaSlug } =
    useMainContext();
  const [vini, setVini] = useState([]);
  const [regione, setRegione] = useState({});

  let fineEndpointVini = "";
  if (categoriaSlug) {
    fineEndpointVini = `api/vini/regione/${slugRegione}/categoria/${categoriaSlug}${stringaQueryRimanenti}`;
  } else {
    fineEndpointVini = `api/vini/regione/${slugRegione}${stringaQueryRimanenti}`;
  }

  const fineEndpointRegione = `api/regioni/${slugRegione}`;

  useEffect(() => {
    fetchResponse(fineEndpointVini, setVini);
    fetchResponse(fineEndpointRegione, setRegione);
  }, [fineEndpointVini, slugRegione]);

  return (
    <div className="w-full min-h-screen bg-transparent">
      <div className="max-w-7xl mx-auto px-4 md:px-8 py-10 space-y-8">
        <div className="top-6 left-4 md:left-8 z-20 flex items-center gap-3">
          <BottoneIndietro />
          <BottoneTuttiProdotti tipoProdotti={"vini"} />
        </div>
        <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-6 border-b border-orange-100/60 pb-6">
          <div>
            <h1 className="text-3xl md:text-4xl font-serif font-bold text-slate-800 tracking-tight">
              I Vini della regione {regione.nome}
            </h1>
            <p className="text-sm text-slate-500 mt-1">
              Assapora i migliori vigneti di questo territorio
            </p>
          </div>

          <div className="flex shrink-0">
            <BottoneOrdinamento />
          </div>
        </div>

        {vini.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {vini.map((vino) => (
              <CardProdotto
                key={vino.id}
                prodotto={vino}
                tipoProdotto={"vino"}
              />
            ))}
          </div>
        ) : (
          <div className="text-center py-20">
            <p className="text-slate-500 text-lg font-medium">
              Nessun vino trovato.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}

export default ViniRegione;
