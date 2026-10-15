package Prototype;

import java.util.Scanner;

public class Factura implements Documento {
    private String numero;
    private String cliente;
    private String fecha;
    private double subtotal;
    private double impuestos;

    public Factura(String numero, String cliente, String fecha,
                   double subtotal, double impuestos) {
        this.numero = numero;
        this.cliente = cliente;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
    }

    @Override
    public Documento clonar() {
        return new Factura(
            numero, cliente, fecha, subtotal, impuestos
        );
    }

    @Override
    public String getNombre() {
        return "Factura electrónica";
    }

    @Override
    public void mostrar() {
        System.out.println("\n--- " + getNombre() + " ---");
        System.out.println("Número: " + numero);
        System.out.println("Cliente: " + cliente);
        System.out.println("Fecha: " + fecha);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Impuestos: $" + impuestos);
        System.out.println("Total: $" + (subtotal + impuestos));
    }

    @Override
    public void editar(Scanner teclado) {
        System.out.print("Nuevo número: ");
        numero = teclado.nextLine();

        System.out.print("Nuevo cliente: ");
        cliente = teclado.nextLine();

        System.out.print("Nueva fecha: ");
        fecha = teclado.nextLine();

        System.out.print("Nuevo subtotal: ");
        subtotal = Double.parseDouble(teclado.nextLine());

        System.out.print("Nuevos impuestos: ");
        impuestos = Double.parseDouble(teclado.nextLine());
    }
}
