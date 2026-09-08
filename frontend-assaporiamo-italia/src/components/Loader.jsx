import { LoaderPinwheel } from "lucide-react";

const Loader = () => {
  return (
    <div className="overlay-loader">
      <LoaderPinwheel className="spinner-loader" role="status" size={120} />
    </div>
  );
};

export default Loader;
