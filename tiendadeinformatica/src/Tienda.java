import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Tienda {
    Scanner sc=new Scanner(System.in);
    private static ArrayList<Pedido> pedidos;
    private ArrayList <Articulo> articulos;
    private ArrayList <Cliente> clientes;

    private ArrayList<LineaPedido> pedidoAux = new ArrayList();

    public Tienda(){
        pedidos = new ArrayList();
        articulos= new ArrayList();
        clientes = new ArrayList();
    }
    public static void main(String[] args) {
        Tienda t=new Tienda();
        t.cargadatos();
        t.menu();
    }
    public void cargadatos() {
        clientes.add(new Cliente("11111111A","ANA","658111111"));
        clientes.add(new Cliente("22222222B","LOLA","649222222"));
        clientes.add(new Cliente("33333333C","JUAN","652333333"));

        articulos.add(new Articulo("1-001","RATON LOGITECH    ",14,15));
        articulos.add(new Articulo("1-002","TECLADO STANDARD  ",9,18));
        articulos.add(new Articulo("2-001","HDD SEAGATE 1TB   ", 16,80));
        articulos.add(new Articulo("2-002","SSD KINGSTOM 256GB",9,70));
        articulos.add(new Articulo("2-003","SSD KINGSTOM 512GB", 15,120));
        articulos.add(new Articulo("3-001","EPSON ET2800      ",25,200));
        articulos.add(new Articulo("3-002","EPSON XP200       ",15,80));
        articulos.add(new Articulo("4-001","ASUS LED 22       ",25,100));
        articulos.add(new Articulo("4-002","HP LED 28         ",35,180));
        articulos.add(new Articulo("4-003","SAMSUNG ODISSEY G5",22,580));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("11111111A-001/2023",clientes.get(0),hoy, new ArrayList<>
                (List.of(new LineaPedido("1-001",1),new LineaPedido("2-001",1)))));                                                                                                                                                               //EDU
        pedidos.add(new Pedido("22222222B-001/2023",clientes.get(1),hoy, new ArrayList<>
                (List.of(new LineaPedido("4-001",4),new LineaPedido("4-002",4),new LineaPedido("4-003",4)))));
        pedidos.add(new Pedido("22222222B-002/2023",clientes.get(1),hoy, new ArrayList<>
                (List.of(new LineaPedido("3-001",3),new LineaPedido("3-002",3)))));
        pedidos.add(new Pedido("33333333C-001/2023",clientes.get(2),hoy, new ArrayList<>
                (List.of(new LineaPedido("3-001",3),new LineaPedido("3-002",3)))));
        pedidos.add(new Pedido("22222222B-003/2023",clientes.get(1),hoy, new ArrayList<>
                (List.of(new LineaPedido("2-001",2),new LineaPedido("2-002",2),new LineaPedido("2-003",2)))));
    }

    public void menu(){
        int opcion=0;
        do{
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES\n");
            System.out.println("\t\t\t\t1 - CREAR PEDIDO");
            System.out.println("\t\t\t\t2 - LISTA ARTICULOS ORDENADOS");
            System.out.println("\t\t\t\t3 - PEDIDOS CLIENTE");
            System.out.println("\t\t\t\t4 - COPIA DE SEGURIDAD");
            System.out.println("\t\t\t\t5 - LISTAR PEDIDOS");
            System.out.println("\t\t\t\t6 - LISTAR ARTICULOS");
            System.out.println("\t\t\t\t7 - LISTAR CLIENTES");
            System.out.println("\t\t\t\t8 - Archivo Articulos");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t10 - Archivo Clientes");
            System.out.println("\t\t\t\t11 - Archivo Clientes con pedido");
            System.out.println("\t\t\t\t12 - Archivo Clientes sin pedidos");
            System.out.println("\t\t\t\t13 - Archivo Articulos");

            opcion=sc.nextInt();
            switch (opcion){
                case 1:{
                    altaPedido();
                    break;
                }
                case 2:{
                    listaArtOrd();
                    break;
                }
                case 3:{
                    pedidosCliente();
                    break;
                } case 4:{
                    backup();
                    break;
                }
                case 5:{
                    listaPedidos();
                    break;
                }
                case 6:{
                    listaArticulos();
                    break;
                }
                case 7:{
                    listaClientes();
                    break;
                }
                case 8:{
                    guardarDatosArticuloEnArchivo(articulos, "ArticulosLista.txt");
                    break;
                }
                case 10:{
                    guardarDatosClienteEnArchivo(clientes, "Clientes.txt");
                }
                case 11:{
                    guardarDatosClienteConPedidoEnArchivo(clientes, "ClientesConPedido.txt");
                    break;
                }
                case 12:{
                    guardarDatosClientesinpedidoEnArchivo(clientes, "ClientesSinPedido.txt");
                    break;
                }
            }
        }while (opcion != 9);
    }
    public int buscarDni(String dni) {
        int pos=-1;
        int i=0;
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)){
                pos=i;
                break;
            }
            i++;
        }
        return pos;
    }
    public int buscarId(String id) {
        int pos=-1;
        int i=0;
        for (Articulo a : articulos) {
            if (a.getIdArticulo().equals(id)){
                pos=i;
                break;
            }
            i++;
        }
        return pos;
    }
    private String generaIdPedido(String idCliente){
        int contador =0;
        String nuevoId;
        for (Pedido p: pedidos){
            if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)){
                contador++;
            }
        }
        contador++;
        nuevoId= idCliente + "-" + String.format("%03d", contador) + "/2023";
        return nuevoId;
    }
    public void listaPedidos() {
        for (Pedido p:pedidos){
            System.out.println("\nLISTADO PEDIDO "+ p.getIdPedido()+ " - CLIENTE: " + p.getClientePedido().getNombre() );
            for (LineaPedido l :p.getCestaCompra()){
                System.out.println(articulos.get(buscarId(l.getIdArticulo())).getDescripcion()+ "\t" + l.getUnidades());
            }
        }
    }
    public void listaArticulos(){
        System.out.println("\n\n");
        for (Articulo a:articulos){
            System.out.println(a);
        }
    }
    public void listaClientes(){
        System.out.println("\n\n");
        for (Cliente c:clientes){
            System.out.println(c);
        }
    }

    public void altaPedido(){

        LocalDate hoy;

        System.out.println("INTRODUCE EL DNI CLIENTE PARA EL PEDIDO: ");
        String dni = sc.next();

        System.out.println("SE VAN A INTRODUCIR LOS ARTÍCULOS 1 A 1: ");
        System.out.println("INTRODUCE CODIGO ARTICULO (99 PARA TERMINAR): ");
        String id=sc.next();
        while (!id.equals("99")){
            System.out.println("CUANTAS UNIDADES QUIERES ?:");
            pedidoAux.add(new LineaPedido (id,sc.nextInt()));
            articulos.get(buscarId(id)).setExistencias(articulos.get(buscarId(id)).getExistencias()-1);
            System.out.println("INTRODUCE CODIGO ARTICULO (99 PARA TERMINAR): ");
            id=sc.next();
        }
        hoy=LocalDate.now();
        //
        //LLAMADA AL MÉTODO generaIdPedido() - ALTA DEL NUEVO PEDIDO EN EL ARRAYLIST
        //
        pedidos.add(new Pedido(generaIdPedido(dni),clientes.get(buscarDni(dni)),hoy, pedidoAux));
    }
    private void listaArtOrd(){
        String[] secciones={"PERIFERICOS","ALMACENAMIENTO","IMPRESORAS","MONITORES"};
        sc.nextLine();
        System.out.println("PARA ORDENAR POR PRECIO < a >(-) > a <(+)");
        String s= sc.nextLine();

        // PREPARO EL ARRAYLIST AUXILIAR DE ORDENACIONES articulosL SEGÚN EL CRITERIO ESPECIFICADO
        if (s.charAt(0)=='-'){
            Collections.sort(articulos,new ComparaArticuloPorPrecio());
        }else if (s.charAt(0)=='+'){
            Collections.sort(articulos,new ComparaArticuloPorPrecio());
            Collections.reverse(articulos);
        }

        for (int i=0;i<=3;i++){
            System.out.println(secciones[i]);
            Iterator<Articulo> it = articulos.iterator();
            while (it.hasNext()){
                Articulo a=it.next();
                if (Character.getNumericValue(a.getIdArticulo().charAt(0))==i+1){
                    System.out.println(a);
                }
            }
        }
    }

    private void leerArchivos() {
        try (ObjectInputStream oisArtPerifericos = new ObjectInputStream(new FileInputStream("perifericos.dat")))
        {
            Articulo a=null;
            while ( (a=(Articulo)oisArtPerifericos.readObject()) != null){
                articulos.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisArtAlmacenamiento = new ObjectInputStream(new FileInputStream("almacenamiento.dat")))
        {
            Articulo a=null;
            while ( (a=(Articulo)oisArtAlmacenamiento.readObject()) != null){
                articulos.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisArtImpresoras = new ObjectInputStream(new FileInputStream("impresoras.dat")))
        {
            Articulo a=null;
            while ( (a=(Articulo)oisArtImpresoras.readObject()) != null){
                articulos.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisArtMonitores = new ObjectInputStream(new FileInputStream("monitores.dat")))
        {
            Articulo a=null;
            while ( (a=(Articulo)oisArtMonitores.readObject()) != null){
                articulos.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        System.out.println("Colecciones importadas con éxito.");
    }
    private void backup() {
        try (ObjectOutputStream oosArtPerifericos = new ObjectOutputStream(new FileOutputStream("perifericos.dat"));
             ObjectOutputStream oosArtAlmacenamiento = new ObjectOutputStream(new FileOutputStream("almacenamiento.dat"));
             ObjectOutputStream oosArtImpresoras = new ObjectOutputStream(new FileOutputStream("impresoras.dat"));
             ObjectOutputStream oosArtMonitores = new ObjectOutputStream(new FileOutputStream("monitores.dat")))
        {
            for (Articulo a:articulos){
                switch (a.getIdArticulo().charAt(0)){
                    case '1' -> {
                        oosArtPerifericos.writeObject(a);
                    }
                    case '2' -> {
                        oosArtAlmacenamiento.writeObject(a);
                    }
                    case '3' -> {
                        oosArtImpresoras.writeObject(a);
                    }
                    case '4' -> {
                        oosArtMonitores.writeObject(a);
                    }
                }
            }
            System.out.println("Copia de seguridad realizada con éxito.");
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public void pedidosCliente(){
        TreeMap <Double,Pedido> pedidosCliente=new TreeMap();

        System.out.println("INTRODUCE EL DNI CLIENTE PARA LISTAR SUS PEDIDOS: ");
        String dni = sc.next();

        //CREAMOS UN TREEMAP ORDENADO CON KEY EL TOTAL DE CADA PEDIDO - YA ESTA ORDENADO POR TOTALES
        for (Pedido p:pedidos){
            if (p.getClientePedido().getDni().equalsIgnoreCase(dni)){
                double total=0;
                for (LineaPedido l :p.getCestaCompra()){
                    total+=l.getUnidades()* articulos.get(buscarId(l.getIdArticulo())).getPvp();
                }
                pedidosCliente.put(total,p);
            }
        }
        //LISTAMOS LOS PEDIDOS DEL TREEMAP ORDENADOS CON SU TOTAL

        for (Double d: pedidosCliente.keySet())
        {
            System.out.println("PEDIDO "+ pedidosCliente.get(d).getIdPedido() + " DE " + pedidosCliente.get(d).getClientePedido().getNombre());
            for (LineaPedido l :  pedidosCliente.get(d).getCestaCompra()){
                System.out.println(articulos.get(buscarId(l.getIdArticulo())).getDescripcion()  + "\t- " + l.getUnidades());
            }
            System.out.println("TOTAL: " + d + "\n");
        }
    }

    public void ArrayListToHashMapExample() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("clave1");
            arrayList.add("clave2");
            arrayList.add("clave3");
            HashMap<String, Integer> hashMap = new HashMap<>();

            for (String key : arrayList) {
                // Asigna un valor a cada clave en el HashMap
                hashMap.put(key, 0); // Puedes asignar el valor que desees
            }

            // Imprime el HashMap resultante
            for (String key : hashMap.keySet()) {
                System.out.println("Clave: " + key + ", Valor: " + hashMap.get(key));
            }

    }
    public void Ejer4(){
        Scanner scanner = new Scanner(System.in);

        // Solicitar DNI y mostrar el cliente asociado
        System.out.print("Introduce el DNI del cliente: ");
        String dniCliente = scanner.nextLine();

        try {
            Cliente clienteEncontrado = buscarCliente(dniCliente, clientes);
            System.out.println("Cliente encontrado: " + clienteEncontrado);
        } catch (NoEncontrado e) {
            System.out.println(e.getMessage());
        }

    }
    public static Cliente buscarCliente(String dni, ArrayList<Cliente> clientes) throws NoEncontrado {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        throw new NoEncontrado("Cliente no encontrado");
    }
    public static Articulo buscarArticulo(String idArticulo, Articulo... articulos) throws NoEncontrado {
        for (Articulo articulo : articulos) {
            if (articulo.getIdArticulo().equals(idArticulo)) {
                return articulo;
            }
        }
        throw new NoEncontrado("Artículo no encontrado");
    }
    public static void guardarDatosArticuloEnArchivo(ArrayList<Articulo> articulos, String nombreArchivo) {
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            PrintWriter ArticulosLista = new PrintWriter(fileWriter);

            for (Articulo articulo : articulos) {
                ArticulosLista.println("Id: " + articulo.getIdArticulo() + ", Descripcion: " + articulo.getDescripcion() +
                        ", Existencias: " + articulo.getExistencias());
            }

            ArticulosLista.close();
            System.out.println("Se ha guardado el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    public static void guardarDatosClienteEnArchivo(ArrayList<Cliente> clientes, String nombreArchivo) {
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            PrintWriter ClientesLista = new PrintWriter(fileWriter);

            for (Cliente cliente : clientes) {
                ClientesLista.println("Nombre: " + cliente.getNombre() + " , DNI: " + cliente.getDni() +
                        " , Telefono: " + cliente.getTelefono());
            }

            ClientesLista.close();
            System.out.println("Se ha guardado el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    public static void guardarDatosClienteConPedidoEnArchivo(ArrayList<Cliente> clientes, String nombreArchivo) {
        ArrayList<Cliente> clientesConPedidos = new ArrayList<>();
            Tienda tienda = new Tienda();
        for (Pedido pedido : pedidos) {
            Cliente cliente = pedido.getClientePedido();
            if (!clientesConPedidos.contains(cliente)) {
                clientesConPedidos.add(cliente);
            }
        }
        guardarDatosClienteEnArchivo(clientesConPedidos, "ClientesconPedido.txt");

    }
    public static void guardarDatosClientesinpedidoEnArchivo(ArrayList<Cliente> clientes, String nombreArchivo) {
        ArrayList<Cliente> clientesSinPedidos = new ArrayList<>();
        Tienda tienda = new Tienda();
        for (Pedido pedido : pedidos) {
            Cliente cliente = pedido.getClientePedido();
            if (clientesSinPedidos.contains(cliente)) {
                clientesSinPedidos.add(cliente);
            }
        }
        guardarDatosClienteEnArchivo(clientesSinPedidos, "ClientesconPedido.txt");

    }
}
