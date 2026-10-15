package Prototype;

import java.util.Scanner;

public class Contrato implements Documento {
    private String numero;
    private String contratista;
    private String entidad;
    private double valor;
    private int duracion;

    public Contrato(String numero, String contratista,
                    String entidad, double valor, int duracion) {
        this.numero = numero;
        this.contratista = contratista;
        this.entidad = entidad;
        this.valor = valor;
        this.duracion = duracion;
    }

    @Override
    public Documento clonar() {
        return new Contrato(
            numero, contratista, entidad, valor, duracion
        );
    }

    @Override
    public String getNombre() {
        return "Contrato de prestación de servicios";
    }

    @Override
    public void mostrar() {
        System.out.println("\n--- " + getNombre() + " ---");
        System.out.println("Número: " + numero);
        System.out.println("Contratista: " + contratista);
        System.out.println("Entidad contratante: " + entidad);
        System.out.println("Valor: $" + valor);
        System.out.println("Duración: " + duracion + " meses");
    }

    @Override
    public void editar(Scanner teclado) {
        System.out.print("Nuevo número: ");
        numero = teclado.nextLine();

        System.out.print("Nuevo contratista: ");
        contratista = teclado.nextLine();

        System.out.print("Nueva entidad contratante: ");
        entidad = teclado.nextLine();

        System.out.print("Nuevo valor: ");
        valor = Double.parseDouble(teclado.nextLine());

        System.out.print("Nueva duración en meses: ");
        duracion = Integer.parseInt(teclado.nextLine());
    }
}

