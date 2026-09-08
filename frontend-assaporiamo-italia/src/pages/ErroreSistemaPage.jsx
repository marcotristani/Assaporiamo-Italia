import { Link } from "react-router-dom";
import { Home, AlertTriangle } from "lucide-react";

function ErroreSistemaPage() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gradient-to-br from-amber-50/60 via-orange-50/30 to-slate-100 p-6 relative overflow-hidden">
      <div className="text-center max-w-lg relative z-10 space-y-6 px-4 animate-fade-in">
        <div className="inline-flex items-center justify-center p-6 bg-red-50 rounded-full border border-red-100 shadow-sm text-red-600 animate-pulse mb-2">
          <AlertTriangle className="h-12 w-12" />
        </div>

        <div className="space-y-2">
          <span className="text-sm font-bold tracking-widest text-red-600 uppercase block">
            Servizio Temporaneamente Non Disponibile
          </span>
          <h1 className="text-3xl md:text-4xl font-serif font-black text-slate-800 tracking-tight leading-tight">
            Ci scusiamo per l'interruzione
          </h1>
        </div>

        <p className="text-base text-slate-600 font-light leading-relaxed max-w-md mx-auto">
          Stiamo riscontrando un problema tecnico nei nostri sistemi. Ti
          invitiamo a riprovare più tardi.
        </p>

        <Link
          to="/"
          className="inline-flex items-center justify-center gap-2 w-full sm:w-auto px-5 py-3 rounded-xl text-sm font-medium text-white bg-amber-600 hover:bg-amber-700 shadow-md shadow-amber-600/10 hover:shadow-lg transition-all duration-200 active:scale-95"
        >
          <Home className="h-4 w-4 text-amber-200" />
          <span>Ricarica Homepage</span>
        </Link>
      </div>
    </div>
  );
}

export default ErroreSistemaPage;
