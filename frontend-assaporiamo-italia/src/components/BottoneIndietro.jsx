import { ArrowLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

function BottoneIndietro() {
  const navigate = useNavigate();
  return (
    <button
      onClick={() => navigate(-1)}
      className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl text-sm font-medium text-slate-700 bg-white/90 hover:bg-white hover:text-amber-800 backdrop-blur-sm transition-all duration-200 shadow-sm border border-slate-200/50 hover:border-amber-200/50 active:scale-95"
    >
      <ArrowLeft className="h-4 w-4" />
      <span className="hidden sm:inline">Indietro</span>
    </button>
  );
}
export default BottoneIndietro;
