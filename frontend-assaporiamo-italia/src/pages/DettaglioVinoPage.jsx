import { useParams, useNavigate, Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useMainContext } from "../contexts/MainContext";

import SliderProdotti from "../components/SliderProdotti";

import DettaglioProdotto from "../components/DettaglioProdotto";

function DettaglioVinoPage() {
  const { slugVino } = useParams();
  const navigate = useNavigate();
  const { fetchResponse, setTipoProdottoCorrente } = useMainContext();

  const [vino, setVino] = useState({});
  const [prodottiCorrelati, setProdottiCorrelati] = useState([]);

  const fineEndpointDettaglio = `api/vini/${slugVino}`;
  const fineEndpointProdottiCorrelati = `api/prodotti/vino/${slugVino}`;

  useEffect(() => {
    fetchResponse(fineEndpointDettaglio, setVino);
    fetchResponse(fineEndpointProdottiCorrelati, setProdottiCorrelati);
    setTipoProdottoCorrente("vino");
  }, [slugVino]);

  return (
    <>
      <DettaglioProdotto prodotto={vino} />
      <div className="min-h-screen bg-slate-50 text-slate-800 pb-20 relative overflow-hidden">
        {prodottiCorrelati && prodottiCorrelati.length > 0 && (
          <section className="max-w-7xl mx-auto px-4 md:px-8 pt-6 border-t border-slate-200/60">
            <SliderProdotti
              listaProdotti={prodottiCorrelati}
              titoloSezione="Prodotti Tipici Correlati"
              descrizioneSezione="I migliori prodotti da degustare con questo vino"
              scrittaLink="Vedi tutti i prodotti tipici"
              urlLink="/prodotti/all"
              tipoProdotto={"prodotto"}
            />
          </section>
        )}
      </div>
    </>
  );
}

export default DettaglioVinoPage;
