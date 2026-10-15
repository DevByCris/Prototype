package Prototype;


import java.util.Scanner;

public class Propuesta implements Documento {
    private String numero;
    private String cliente;
    private String descripcion;
    private double valor;
    private int vigencia;

    public Propuesta(String numero, String cliente,
                     String descripcion, double valor, int vigencia) {
        this.numero = numero;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.valor = valor;
        this.vigencia = vigencia;
    }

    @Override
    public Documento clonar() {
        return new Propuesta(
            numero, cliente, descripcion, valor, vigencia
        );
    }

    @Override
    public String getNombre() {
        return "Propuesta comercial";
    }

    @Override
    public void mostrar() {
        System.out.println("\n--- " + getNombre() + " ---");
        System.out.println("Número: " + numero);
        System.out.println("Cliente: " + cliente);
        System.out.println("Servicio: " + descripcion);
        System.out.println("Valor: $" + valor);
        System.out.println("Vigencia: " + vigencia + " días");
    }

    @Override
    public void editar(Scanner teclado) {
        System.out.print("Nuevo número: ");
        numero = teclado.nextLine();

        System.out.print("Nuevo cliente: ");
        cliente = teclado.nextLine();

        System.out.print("Nueva descripción del servicio: ");
        descripcion = teclado.nextLine();

        System.out.print("Nuevo valor: ");
        valor = Double.parseDouble(teclado.nextLine());

        System.out.print("Nueva vigencia en días: ");
        vigencia = Integer.parseInt(teclado.nextLine());
    }
}
