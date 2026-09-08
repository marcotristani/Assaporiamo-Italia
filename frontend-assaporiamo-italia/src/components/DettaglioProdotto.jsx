import {
  ArrowLeft,
  ShoppingBag,
  Globe,
  Info,
  LayoutGrid,
  Sparkles,
  MapPin, // Icona per la Regione
  Folder, // Icona per la Categoria
} from "lucide-react";
import BottoneIndietro from "../components/BottoneIndietro";
import BottoneTuttiProdotti from "../components/BottoneTuttiProdotti";
import { useMainContext } from "../contexts/MainContext";
import { Award } from "lucide-react";

// Estraiamo "regione" e "categoria" direttamente dalle props del prodotto
function DettaglioProdotto({ prodotto }) {
  const {
    nome,
    descrizione,
    urlImmagine,
    linkStore,
    certificazione,
    regione,
    categoria,
  } = prodotto;

  const { tipoProdottoCorrente } = useMainContext();

  return (
    <div className="min-h-screen bg-slate-50 text-slate-800  relative overflow-hidden">
      <header className="relative w-full h-[55vh] md:h-[65vh] flex items-center justify-center overflow-hidden shadow-sm">
        <img
          src={urlImmagine}
          alt={nome}
          className="absolute inset-0 w-full h-full object-cover transform scale-100 transition-transform duration-700 hover:scale-105"
        />

        <div className="absolute top-6 left-4 md:left-8 z-20 flex items-center gap-3">
          <BottoneIndietro />
          {tipoProdottoCorrente === "vino" ? (
            <BottoneTuttiProdotti tipoProdotti={"vini"} />
          ) : (
            <BottoneTuttiProdotti tipoProdotti={"prodotti"} />
          )}
        </div>

        <div className="relative z-10 text-center text-white max-w-4xl px-6 space-y-3">
          <span className="inline-flex items-center gap-1.5 text-amber-400 font-semibold tracking-widest uppercase text-xs md:text-sm drop-shadow-sm">
            <Sparkles className="h-3.5 w-3.5" />
            Eccellenza{" "}
            {tipoProdottoCorrente === "vino"
              ? "Enologica"
              : "Gastronomica"}{" "}
            Italiana
          </span>
          <h1 className="text-3xl md:text-5xl lg:text-6xl font-serif font-bold tracking-tight leading-tight drop-shadow-md max-w-3xl mx-auto">
            {nome}
          </h1>
        </div>
      </header>

      <main className="max-w-6xl mx-auto px-4 md:px-8 -mt-20 relative z-20 mb-20">
        <div className="bg-white rounded-3xl border border-slate-100 shadow-xl shadow-slate-200/50 p-6 md:p-12 grid grid-cols-1 md:grid-cols-12 gap-8 md:gap-12 items-center">
          <div className="md:col-span-5 aspect-square rounded-2xl overflow-hidden border border-slate-100 bg-slate-50 flex items-center justify-center p-6 group shadow-inner">
            <img
              src={urlImmagine}
              alt={nome}
              className="max-h-full max-w-full object-contain rounded-xl transform transition-transform duration-500 group-hover:scale-105"
            />
            {certificazione && (
              <div className="absolute top-4 right-4 flex items-center gap-1 bg-amber-600 text-white px-3 py-1 rounded-xl text-xl font-bold tracking-wider shadow-md uppercase">
                <Award className="h-10.5 w-10.5 text-amber-200" />
                {certificazione}
              </div>
            )}
          </div>

          <div className="md:col-span-7 space-y-6 flex flex-col justify-center h-full">
            <div className="flex items-center gap-2 text-slate-800 font-serif font-bold text-xl md:text-2xl border-b border-slate-100 pb-4">
              <Info className="h-5 w-5 text-amber-600 shrink-0" />
              <h2>Informazioni </h2>
            </div>

            <div className="flex flex-wrap gap-4 text-xs font-medium text-slate-600">
              <div className="flex items-center gap-1.5 bg-slate-100/80 border border-slate-200/40 px-3 py-1.5 rounded-xl">
                <MapPin className="h-3.5 w-3.5 text-amber-600" />
                <span>
                  Regione:{" "}
                  <strong className="text-slate-800 font-semibold">
                    {regione}
                  </strong>
                </span>
              </div>

              <div className="flex items-center gap-1.5 bg-slate-100/80 border border-slate-200/40 px-3 py-1.5 rounded-xl">
                <Folder className="h-3.5 w-3.5 text-amber-600" />
                <span>
                  Categoria:{" "}
                  <strong className="text-slate-800 font-semibold">
                    {categoria}
                  </strong>
                </span>
              </div>
            </div>

            <p className="text-base text-slate-600 font-light leading-relaxed tracking-wide">
              {descrizione}
            </p>

            <div className="pt-4 border-t border-slate-50">
              <a
                href={linkStore}
                target="_blank"
                rel="noopener noreferrer"
                className="inline-flex items-center justify-center w-full sm:w-auto px-7 py-4 rounded-2xl text-sm font-semibold text-white bg-amber-600 hover:bg-amber-700 active:scale-[0.98] transition-all duration-200 shadow-md shadow-amber-600/10 hover:shadow-lg hover:shadow-amber-600/20 gap-3 group"
              >
                <ShoppingBag className="h-4 w-4 text-amber-200" />
                <span>Acquista nello Store Ufficiale</span>
                <Globe className="h-4 w-4 ml-1 opacity-50 group-hover:opacity-100 group-hover:translate-x-0.5 transition-all" />
              </a>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
export default DettaglioProdotto;
