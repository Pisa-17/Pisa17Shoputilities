import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    String idPedido;
    Cliente clientePedido;
    private ArrayList<Articulo> listArticulo = new ArrayList<Articulo>();
    LocalDate fechaPedido;

    private List<LineaPedido> LineaPedido;

    public Pedido() {
    }

    public Pedido(String idPedido, Cliente clientePedido, ArrayList<Articulo> listArticulo, LocalDate fechaPedido) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.listArticulo = listArticulo;
        this.fechaPedido = fechaPedido;
    }

    public Pedido(String idPedido, Cliente clientePedido, LocalDate fechaPedido, List<LineaPedido> lineaPedido) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fechaPedido = fechaPedido;
        LineaPedido = lineaPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public ArrayList<Articulo> getListArticulo() {
        return listArticulo;
    }

    public void setListArticulo(ArrayList<Articulo> listArticulo) {
        this.listArticulo = listArticulo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", clientePedido=" + clientePedido +
                ", listArticulo=" + listArticulo +
                ", fechaPedido=" + fechaPedido +
                '}';
    }

    public LineaPedido[] getCestaCompra() {
        return new LineaPedido[0];
    }
}
