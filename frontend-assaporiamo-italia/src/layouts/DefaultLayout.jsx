import { Outlet, Link } from "react-router-dom"; // Aggiunto Link per l'eventuale ritorno alla Home o privacy
import { useMainContext } from "../contexts/MainContext";
import "../styles/LoaderStyle.css";
import Loader from "../components/Loader";
import NavbarBase from "../components/NavbarBase";

function DefaultLayout() {
  const { isLoading } = useMainContext();

  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <Outlet />
        {isLoading && <Loader />}
      </main>

      <footer className="w-full bg-slate-900 border-t border-slate-800 py-6 mt-auto">
        <div className="mx-auto max-w-7xl px-4 md:px-8 flex flex-col sm:flex-row items-center justify-between gap-4 text-xs font-light text-slate-400">
          <div className="flex items-center gap-1.5">
            <span className="font-serif font-bold tracking-wide text-amber-500 text-sm">
              Assaporiamo Italia
            </span>
            <span>&copy; 2026. Tutti i diritti riservati.</span>
          </div>

          <div className="flex items-center gap-6">
            <Link
              to="/"
              className="hover:text-amber-400 transition-colors duration-200"
            >
              Home
            </Link>
            <a
              href="#privacy"
              className="hover:text-amber-400 transition-colors duration-200"
            >
              Privacy Policy
            </a>
            <a
              href="#contatti"
              className="hover:text-amber-400 transition-colors duration-200"
            >
              Contatti
            </a>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default DefaultLayout;
