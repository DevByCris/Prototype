package Prototype;

import java.util.Scanner;

public interface Documento{
    Documento clonar();

    void mostrar();
    void editar(Scanner teclado);
    String getNombre();
}
