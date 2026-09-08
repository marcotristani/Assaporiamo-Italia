import { Home } from "lucide-react";
import { Link } from "react-router-dom";

function BottoneHome() {
  return (
    <Link
      to="/"
      className="p-2.5 rounded-xl border border-slate-100 bg-white text-slate-700 hover:text-amber-700 shadow-sm hover:shadow transition-all duration-200"
    >
      <Home className="h-5 w-5 text-amber-600" />
    </Link>
  );
}
export default BottoneHome;
