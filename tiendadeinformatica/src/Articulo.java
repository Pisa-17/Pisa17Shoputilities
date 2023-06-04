import java.util.Comparator;

public class Articulo implements Comparable<Articulo> {

    String idArticulo;
    String descripcion;
    int existencias;
    double pvp;

    public Articulo() {
    }

    public Articulo(String idArticulo, String descripcion, int existencias, double pvp) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.pvp = pvp;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public String getSeccion() {
        char primerCaracter = idArticulo.charAt(0);

        switch (primerCaracter) {
            case '1':
                return "PERIFÉRICOS";
            case '2':
                return "ALMACENAMIENTO";
            case '3':
                return "IMPRESORAS";
            case '4':
                return "MONITORES";
            case '5':
                return "COMPONENTES";
            default:
                return "OTRA SECCIÓN";
        }
    }
    @Override
    public int compareTo(Articulo otroArticulo) {
        return this.idArticulo.compareTo(otroArticulo.idArticulo);
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "idArticulo='" + idArticulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", existencias=" + existencias +
                ", pvp=" + pvp +
                '}';
    }


}
