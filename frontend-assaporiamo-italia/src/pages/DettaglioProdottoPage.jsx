import { useParams, useNavigate, Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useMainContext } from "../contexts/MainContext";

import SliderProdotti from "../components/SliderProdotti";

import DettaglioProdotto from "../components/DettaglioProdotto";

function DettaglioProdottoPage() {
  const { slugProdotto } = useParams();
  const navigate = useNavigate();
  const { fetchResponse, setTipoProdottoCorrente } = useMainContext();

  const [prodottoTipico, setProdottoTipico] = useState({});
  const [viniCorrelati, setViniCorrelati] = useState([]);

  const fineEndpointDettaglio = `api/prodotti/${slugProdotto}`;
  const fineEndpointViniCorrelati = `api/vini/prodotto/${slugProdotto}`;

  useEffect(() => {
    fetchResponse(fineEndpointDettaglio, setProdottoTipico);
    fetchResponse(fineEndpointViniCorrelati, setViniCorrelati);
    setTipoProdottoCorrente("prodotto");
  }, [slugProdotto]);

  return (
    <>
      <DettaglioProdotto prodotto={prodottoTipico} />
      <div className="min-h-screen bg-slate-50 text-slate-800 pb-20 relative overflow-hidden">
        {viniCorrelati && viniCorrelati.length > 0 && (
          <section className="max-w-7xl mx-auto px-4 md:px-8 pt-6 border-t border-slate-200/60">
            <SliderProdotti
              listaProdotti={viniCorrelati}
              titoloSezione="Vini Correlati"
              descrizioneSezione="I migliori vini da abbinare a questo prodotto"
              scrittaLink="Vedi tutti i vini"
              urlLink="/vini/all"
              tipoProdotto={"vino"}
            />
          </section>
        )}
      </div>
    </>
  );
}

export default DettaglioProdottoPage;
