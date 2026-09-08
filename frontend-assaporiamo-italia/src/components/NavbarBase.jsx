import BottoneHome from "./BottoneHome";

function NavbarBase() {
  return (
    <nav className="sticky top-0 z-50 w-full border-b border-orange-100/50 bg-white/80 backdrop-blur-md shadow-sm">
      <div className="mx-auto flex h-16 max-w-7xl items-center justify-between px-4 md:px-8 gap-4">
        <div className="flex flex-1 justify-start">
          <BottoneHome />
        </div>

        <div className="flex flex-1 justify-center text-center">
          <h1 className="font-serif text-lg sm:text-xl font-bold tracking-wide text-slate-800 whitespace-nowrap">
            Assaporiamo{" "}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-orange-600 to-amber-500">
              l'Italia
            </span>
          </h1>
        </div>

        <div className="flex flex-1 justify-end" />
      </div>
    </nav>
  );
}

export default NavbarBase;
