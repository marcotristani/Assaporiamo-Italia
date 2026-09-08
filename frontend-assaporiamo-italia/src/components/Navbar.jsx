import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import { Home, Search, X, ChevronDown, Check } from "lucide-react";
import { useState, useEffect } from "react";
import { useMainContext } from "../contexts/MainContext";
import {
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Dialog,
  DialogPanel,
} from "@headlessui/react";
import BottoneHome from "./BottoneHome";

function Navbar({ tipoProdotto }) {
  const { fetchResponse, categoriaSlug } = useMainContext();
  const { slugRegione } = useParams();
  const location = useLocation();
  const navigate = useNavigate();

  const [categorie, setCategorie] = useState([]);
  const [isCercaOpen, setIsCercaOpen] = useState(false); // Stato per aprire/chiudere la ricerca mobile

  const fineEndpointCategorie =
    tipoProdotto === "prodottoTipico"
      ? "api/prodotti/categorie"
      : "api/vini/tipologie";

  useEffect(() => {
    fetchResponse(fineEndpointCategorie, setCategorie);
  }, []);

  const queryAttuali = new URLSearchParams(location.search);
  const valoreRicerca = queryAttuali.get("ricerca") || "";

  const categoriaTrovata = categorie.find(
    (categoria) => categoria.slug === categoriaSlug,
  );
  const nomeCategoriaAttiva = categoriaTrovata
    ? categoriaTrovata.nome
    : "Tutte le categorie";

  function gestisciCambioCategoria(slugCategoria) {
    const query = new URLSearchParams(location.search);
    if (slugCategoria === "tutte") {
      query.delete("categoria");
    } else {
      query.set("categoria", slugCategoria);
    }
    navigate(`${location.pathname}?${query.toString()}`);
  }

  function gestisciCambioRicerca(e) {
    const testo = e.target.value;
    const params = new URLSearchParams(location.search);
    if (testo) {
      params.set("ricerca", testo);
    } else {
      params.delete("ricerca");
    }
    navigate(`${location.pathname}?${params.toString()}`);
  }

  function resettaRicerca() {
    const query = new URLSearchParams(location.search);
    query.delete("ricerca");
    navigate(`${location.pathname}?${query.toString()}`);
  }

  return (
    <>
      <nav className="sticky top-0 z-50 w-full border-b border-orange-100/50 bg-white/80 backdrop-blur-md shadow-sm">
        <div className="mx-auto flex h-16 max-w-7xl items-center justify-between px-4 md:px-8 gap-4">
          <div className="flex shrink-0">
            <BottoneHome />
          </div>

          <div className="flex flex-1 justify-start md:justify-center">
            <div className="block md:hidden relative z-40">
              <Menu>
                <MenuButton className="inline-flex items-center gap-2 px-3 py-2 rounded-xl text-xs sm:text-sm font-medium bg-amber-50 text-amber-900 border border-amber-200/60 shadow-sm transition-all">
                  <span className="truncate max-w-[100px] sm:max-w-[140px]">
                    {nomeCategoriaAttiva}
                  </span>
                  <ChevronDown className="h-4 w-4 text-amber-600" />
                </MenuButton>

                <MenuItems
                  transition
                  className="absolute left-0 mt-2 w-56 origin-top-left rounded-2xl border border-slate-100 bg-white p-1.5 shadow-lg outline-none transition duration-100 ease-out data-[closed]:scale-95 data-[closed]:opacity-0"
                >
                  <MenuItem>
                    <button
                      onClick={() => gestisciCambioCategoria("tutte")}
                      className={`flex w-full items-center justify-between px-3 py-2 rounded-xl text-sm font-medium transition-colors ${
                        categoriaSlug === ""
                          ? "bg-amber-50 text-amber-900"
                          : "text-slate-700 hover:bg-slate-50"
                      }`}
                    >
                      <span>Tutte le categorie</span>
                      {categoriaSlug === "" && (
                        <Check className="h-4 w-4 text-amber-600" />
                      )}
                    </button>
                  </MenuItem>

                  <div className="my-1 border-t border-slate-100" />

                  {categorie.map((categoria) => (
                    <MenuItem key={categoria.id}>
                      <button
                        onClick={() => gestisciCambioCategoria(categoria.slug)}
                        className={`flex w-full items-center justify-between px-3 py-2 rounded-xl text-sm font-medium transition-colors ${
                          categoriaSlug === categoria.slug
                            ? "bg-amber-50 text-amber-900"
                            : "text-slate-700 hover:bg-slate-50"
                        }`}
                      >
                        <span className="truncate">{categoria.nome}</span>
                        {categoriaSlug === categoria.slug && (
                          <Check className="h-4 w-4 text-amber-600" />
                        )}
                      </button>
                    </MenuItem>
                  ))}
                </MenuItems>
              </Menu>
            </div>

            <ul className="hidden md:flex items-center gap-2">
              <li>
                <button
                  onClick={() => gestisciCambioCategoria("tutte")}
                  className={`px-3 py-1.5 rounded-xl text-sm font-medium transition-all duration-200 ${
                    categoriaSlug === ""
                      ? "bg-amber-600 text-white shadow-sm"
                      : "text-slate-600 hover:bg-slate-50 hover:text-amber-700"
                  }`}
                >
                  Tutte le categorie
                </button>
              </li>

              {categorie.map((categoria) => (
                <li key={categoria.slug}>
                  <button
                    onClick={() => gestisciCambioCategoria(categoria.slug)}
                    className={`px-3 py-1.5 rounded-xl text-sm font-medium transition-all duration-200 ${
                      categoriaSlug === categoria.slug
                        ? "bg-amber-600 text-white shadow-sm"
                        : "text-slate-600 hover:bg-slate-50 hover:text-amber-700"
                    }`}
                  >
                    {categoria.nome}
                  </button>
                </li>
              ))}
            </ul>
          </div>

          <div className="flex shrink-0 items-center justify-end">
            <button
              onClick={() => setIsCercaOpen(true)}
              className="p-2.5 md:hidden rounded-xl border border-slate-100 bg-white text-slate-700 hover:text-amber-700 shadow-sm relative"
            >
              <Search className="h-5 w-5 text-slate-500" />
            </button>

            <div className="hidden md:block relative w-56">
              <input
                type="text"
                placeholder="Cerca prodotto..."
                value={valoreRicerca}
                onChange={gestisciCambioRicerca}
                className="w-full rounded-xl border border-slate-200 bg-white py-1.5 pl-9 pr-8 text-sm text-slate-800 outline-none transition-all focus:border-amber-500 focus:ring-2 focus:ring-amber-100"
              />
              <Search className="absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-slate-400" />

              {valoreRicerca && (
                <button
                  type="button"
                  onClick={resettaRicerca}
                  className="absolute right-2.5 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-600"
                >
                  <X className="h-3.5 w-3.5" />
                </button>
              )}
            </div>
          </div>
        </div>
      </nav>

      <Dialog
        open={isCercaOpen}
        onClose={() => setIsCercaOpen(false)}
        className="relative z-50 md:hidden"
      >
        <div className="fixed inset-x-0 top-0 p-4">
          <DialogPanel
            transition
            className="w-full max-w-xl mx-auto rounded-2xl bg-white p-4 shadow-xl border border-slate-100 flex items-center gap-3 transform transition duration-200 ease-out data-[closed]:-translate-y-4 data-[closed]:opacity-0"
          >
            <div className="relative flex-1">
              <input
                type="text"
                placeholder="Digita per cercare prodotti..."
                value={valoreRicerca}
                onChange={gestisciCambioRicerca}
                className="w-full rounded-xl border border-slate-200 bg-slate-50/50 py-2.5 pl-10 pr-10 text-sm text-slate-800 outline-none focus:border-amber-500 focus:bg-white focus:ring-2 focus:ring-amber-100"
              />

              {valoreRicerca && (
                <button
                  type="button"
                  onClick={resettaRicerca}
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-600"
                />
              )}
              <button
                onClick={() => setIsCercaOpen(false)}
                className="p-2.5 rounded-xl border border-slate-200 bg-white text-slate-500 hover:text-slate-800 font-medium text-xs transition-colors"
              >
                chiudi
              </button>
            </div>
          </DialogPanel>
        </div>
      </Dialog>
    </>
  );
}
export default Navbar;
