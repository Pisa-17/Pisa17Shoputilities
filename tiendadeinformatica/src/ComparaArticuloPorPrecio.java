import java.util.Comparator;

public class ComparaArticuloPorPrecio implements Comparator<Articulo> {

    @Override
    public int compare(Articulo articulo1, Articulo articulo2) {
        return Double.compare(articulo1.getPvp(), articulo2.getPvp());
    }
}

