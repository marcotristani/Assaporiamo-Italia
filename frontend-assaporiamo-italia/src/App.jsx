import { BrowserRouter, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import { MainProvider } from "./contexts/MainContext";
import DefaultLayout from "./layouts/DefaultLayout";
import RegioneDetail from "./pages/RegioneDetail";
import ProdottiRegione from "./pages/ProdottiRegione";
import ListaProdottiLayout from "./layouts/ListaProdottiLayout";
import TuttiProdotti from "./pages/TuttiProdotti";
import DettaglioProdottoPage from "./pages/DettaglioProdottoPage";
import BaseLayout from "./layouts/BaseLayout";
import ListaViniiLayout from "./layouts/ListaViniLayout";
import TuttiVini from "./pages/TuttiVini";
import ViniRegione from "./pages/ViniRegione";
import DettaglioVinoPage from "./pages/DettaglioVinoPage";
import NotFoundPage from "./pages/NotFoundPage";
import ErroreSistemaPage from "./pages/ErroreSistemaPage";

export default function App() {
  return (
    <BrowserRouter>
      <MainProvider>
        <Routes>
          <Route element={<DefaultLayout />}>
            <Route path="/" element={<Homepage />} />
            <Route element={<BaseLayout />}>
              <Route path="/regione/:slugRegione" element={<RegioneDetail />} />
              <Route
                path="/prodotti/dettaglio/:slugProdotto"
                element={<DettaglioProdottoPage />}
              />
              <Route
                path="/vini/dettaglio/:slugVino"
                element={<DettaglioVinoPage />}
              />
            </Route>
            <Route element={<ListaProdottiLayout />}>
              <Route
                path="/prodotti/regione/:slugRegione"
                element={<ProdottiRegione />}
              />
              <Route path="/prodotti/all" element={<TuttiProdotti />} />
            </Route>
            <Route element={<ListaViniiLayout />}>
              <Route path="/vini/all" element={<TuttiVini />} />
              <Route
                path="/vini/regione/:slugRegione"
                element={<ViniRegione />}
              />
            </Route>
          </Route>
          <Route path="*" element={<NotFoundPage />} />
          <Route path="/error-system" element={<ErroreSistemaPage />} />
        </Routes>
      </MainProvider>
    </BrowserRouter>
  );
}
