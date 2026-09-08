import { useNavigate, Link } from "react-router-dom"; // Importato Link per i bottoni
import MappaItalia from "../components/MappaItalia";
// Importazione icone Lucide coerenti con il resto del progetto
import { LayoutGrid, Wine } from "lucide-react";
import BottoneTuttiProdotti from "../components/BottoneTuttiProdotti";

function Homepage() {
  const navigate = useNavigate();

  const handleRegioneClick = (slugRegione) => {
    navigate(`/regione/${slugRegione}`);
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gradient-to-br from-amber-50/60 via-orange-50/30 to-slate-100 p-4 md:p-12 relative overflow-hidden">
      <div className="absolute top-6 right-4 md:right-12 z-20 flex flex-row items-center gap-3 w-auto">
        <BottoneTuttiProdotti tipoProdotti={"prodotti"} />
        <BottoneTuttiProdotti tipoProdotti={"vini"} />
      </div>

      <header className="text-center mb-10 max-w-2xl relative z-10 animate-fade-in mt-16 md:mt-0">
        <h1 className="text-4xl md:text-5xl lg:text-6xl font-black text-slate-800 tracking-tight leading-none mb-4">
          Assaporiamo{" "}
          <span className="text-transparent bg-clip-text bg-gradient-to-r from-orange-600 to-amber-500">
            l'Italia
          </span>
        </h1>
        <p className="text-base md:text-lg text-slate-600 font-light max-w-xl mx-auto leading-relaxed tracking-wide">
          Un'esperienza interattiva tra i prodotti tipici e i vini pregiati
          delle nostre terre.
        </p>
      </header>

      <main className="w-full max-w-2xl relative z-10">
        <div className="bg-white/80 backdrop-blur-md p-4 md:p-10 rounded-[2.5rem] shadow-[0_20px_50px_rgba(234,88,12,0.06)] border border-white/60 transition-all duration-500 hover:shadow-[0_20px_60px_rgba(234,88,12,0.1)]">
          <MappaItalia onRegioneClick={handleRegioneClick} />
        </div>
      </main>
    </div>
  );
}

export default Homepage;
