import { Wine } from "lucide-react";
import { LayoutGrid } from "lucide-react";
import { Link } from "react-router-dom";

function BottoneTuttiProdotti({ tipoProdotti }) {
  return (
    <Link
      to={`/${tipoProdotti}/all`}
      className="inline-flex items-center gap-2 px-4 py-2.5 rounded-xl text-sm font-medium text-amber-900 bg-amber-50/90 hover:bg-amber-100 hover:text-amber-950 backdrop-blur-sm transition-all duration-200 shadow-sm border border-amber-200/40 active:scale-95"
    >
      {tipoProdotti === "prodotti" && (
        <>
          <LayoutGrid className="h-4 w-4 text-amber-700" />
          <span>Tutti i {tipoProdotti}</span>
        </>
      )}
      {tipoProdotti === "vini" && (
        <>
          <Wine className="h-4 w-4 text-amber-200" />
          <span>Tutti i vini</span>
        </>
      )}
    </Link>
  );
}
export default BottoneTuttiProdotti;
