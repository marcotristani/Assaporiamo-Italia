import { Link, useParams } from "react-router-dom";
import { useMainContext } from "../contexts/MainContext";
import { useState, useEffect } from "react";
import CardProdotto from "../components/CardProdotto";
import BottoneOrdinamento from "../components/BottoneOrdinamento";
import BottoneIndietro from "../components/BottoneIndietro";
import BottoneTuttiProdotti from "../components/BottoneTuttiProdotti";

function ProdottiRegione() {
  const { slugRegione } = useParams();
  const { fetchResponse, stringaQueryRimanenti, categoriaSlug } =
    useMainContext();
  const [prodottiTipici, setProdottiTipici] = useState([]);
  const [regione, setRegione] = useState({});

  let fineEndpointProdotti = "";
  if (categoriaSlug) {
    fineEndpointProdotti = `api/prodotti/regione/${slugRegione}/categoria/${categoriaSlug}${stringaQueryRimanenti}`;
  } else {
    fineEndpointProdotti = `api/prodotti/regione/${slugRegione}${stringaQueryRimanenti}`;
  }

  const fineEndpointRegione = `api/regioni/${slugRegione}`;

  useEffect(() => {
    fetchResponse(fineEndpointProdotti, setProdottiTipici);
    fetchResponse(fineEndpointRegione, setRegione);
  }, [fineEndpointProdotti, slugRegione]);

  return (
    <div className="w-full min-h-screen bg-transparent">
      <div className="max-w-7xl mx-auto px-4 md:px-8 py-10 space-y-8">
        <div className="top-6 left-4 md:left-8 z-20 flex items-center gap-3">
          <BottoneIndietro />
          <BottoneTuttiProdotti tipoProdotti={"prodotti"} />
        </div>
        <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-6 border-b border-orange-100/60 pb-6">
          <div>
            <h1 className="text-3xl md:text-4xl font-serif font-bold text-slate-800 tracking-tight">
              I Prodotti Tipici della regione {regione.nome}
            </h1>
            <p className="text-sm text-slate-500 mt-1">
              Scopri le autentiche eccellenze gastronomiche di questo territorio
            </p>
          </div>

          <div className="flex shrink-0">
            <BottoneOrdinamento />
          </div>
        </div>

        {prodottiTipici.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {prodottiTipici.map((prodotto) => (
              <CardProdotto
                key={prodotto.id}
                prodotto={prodotto}
                tipoProdotto={"prodottoTipico"}
              />
            ))}
          </div>
        ) : (
          <div className="text-center py-20">
            <p className="text-slate-500 text-lg font-medium">
              Nessun prodotto trovato.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}

export default ProdottiRegione;
