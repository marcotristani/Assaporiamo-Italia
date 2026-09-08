import { useNavigate, Link } from "react-router-dom";
import { ArrowLeft, Home, Compass } from "lucide-react";

function NotFoundPage() {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gradient-to-br from-amber-50/60 via-orange-50/30 to-slate-100 p-6 relative overflow-hidden">
      <div className="text-center max-w-lg relative z-10 space-y-6 animate-fade-in px-4">
        <div className="inline-flex items-center justify-center p-6 bg-amber-100/80 backdrop-blur-sm rounded-full border border-amber-200/50 shadow-sm text-amber-600 animate-bounce duration-1000 mb-2">
          <Compass className="h-12 w-12" />
        </div>

        <div className="space-y-2">
          <span className="text-sm font-bold tracking-widest text-amber-600 uppercase block">
            Errore 404
          </span>
          <h1 className="text-4xl md:text-5xl font-serif font-black text-slate-800 tracking-tight leading-none">
            Pagina non trovata
          </h1>
        </div>

        <p className="text-base text-slate-600 font-light leading-relaxed max-w-md mx-auto">
          La rotta che hai seguito non sembra esistere o la prelibatezza che
          stavi cercando non esiste. Esplora nuovamente la mappa o torna alla
          Home.
        </p>

        <div className="flex flex-col sm:flex-row items-center justify-center gap-3 pt-4">
          <button
            onClick={() => navigate(-1)}
            className="inline-flex items-center justify-center gap-2 w-full sm:w-auto px-5 py-3 rounded-xl text-sm font-medium text-slate-700 bg-white hover:bg-slate-50 border border-slate-200 shadow-sm transition-all duration-200 active:scale-95"
          >
            <ArrowLeft className="h-4 w-4 text-slate-500" />
            <span>Torna indietro</span>
          </button>

          <Link
            to="/"
            className="inline-flex items-center justify-center gap-2 w-full sm:w-auto px-5 py-3 rounded-xl text-sm font-medium text-white bg-amber-600 hover:bg-amber-700 shadow-md shadow-amber-600/10 hover:shadow-lg transition-all duration-200 active:scale-95"
          >
            <Home className="h-4 w-4 text-amber-200" />
            <span>Vai alla Homepage</span>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default NotFoundPage;
