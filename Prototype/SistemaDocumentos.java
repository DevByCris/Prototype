package Prototype;


import java.util.ArrayList;
import java.util.Scanner;

public class SistemaDocumentos {

    private static final Scanner teclado = new Scanner(System.in);

    private static final ArrayList<Documento> plantillas =
            new ArrayList<>();

    private static final ArrayList<Documento> documentos =
            new ArrayList<>();

    public static void main(String[] args) {
        cargarPlantillas();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA PROTOTYPE =====");
            System.out.println("1. Registrar plantilla");
            System.out.println("2. Consultar plantillas");
            System.out.println("3. Clonar una plantilla");
            System.out.println("4. Consultar documentos generados");
            System.out.println("5. Editar un documento generado");
            System.out.println("6. Prueba de independencia");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPlantilla();
                    break;
                case 2:
                    consultar(plantillas);
                    break;
                case 3:
                    clonarPlantilla();
                    break;
                case 4:
                    consultar(documentos);
                    break;
                case 5:
                    editarDocumento();
                    break;
                case 6:
                    probarIndependencia();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        teclado.close();
    }

    private static void cargarPlantillas() {
        plantillas.add(new Contrato(
            "CON-001", "Carlos Pérez", "Empresa ABC",
            5000000, 6
        ));

        plantillas.add(new Factura(
            "FAC-001", "Cliente original", "2026-10-14",
            1000000, 190000
        ));

        plantillas.add(new Propuesta(
            "PROP-001", "Empresa XYZ",
            "Desarrollo de software", 3500000, 30
        ));
    }

    private static void registrarPlantilla() {
        System.out.println("\nTipo de plantilla:");
        System.out.println("1. Contrato");
        System.out.println("2. Factura");
        System.out.println("3. Propuesta");
        System.out.print("Seleccione: ");

        int tipo = leerEntero();
        Documento nuevo = null;

        switch (tipo) {
            case 1:
                nuevo = new Contrato(
                    leerTexto("Número: "),
                    leerTexto("Contratista: "),
                    leerTexto("Entidad contratante: "),
                    leerDouble("Valor: "),
                    leerInt("Duración en meses: ")
                );
                break;

            case 2:
                nuevo = new Factura(
                    leerTexto("Número: "),
                    leerTexto("Cliente: "),
                    leerTexto("Fecha: "),
                    leerDouble("Subtotal: "),
                    leerDouble("Impuestos: ")
                );
                break;

            case 3:
                nuevo = new Propuesta(
                    leerTexto("Número: "),
                    leerTexto("Cliente: "),
                    leerTexto("Descripción del servicio: "),
                    leerDouble("Valor: "),
                    leerInt("Vigencia en días: ")
                );
                break;

            default:
                System.out.println("Tipo no válido.");
                return;
        }

        plantillas.add(nuevo);
        System.out.println("Plantilla registrada correctamente.");
    }

    private static void clonarPlantilla() {
        if (plantillas.isEmpty()) {
            System.out.println("No hay plantillas disponibles.");
            return;
        }

        consultar(plantillas);
        int indice = leerInt("Número de plantilla que desea clonar: ");

        if (indice < 1 || indice > plantillas.size()) {
            System.out.println("Índice no válido.");
            return;
        }

        Documento copia = plantillas.get(indice - 1).clonar();
        documentos.add(copia);

        System.out.println("Documento clonado correctamente.");
        System.out.println("La copia se registró con el número "
                + documentos.size() + ".");
        copia.mostrar();
    }

    private static void consultar(ArrayList<Documento> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay documentos registrados.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("\nÍndice: " + (i + 1));
            lista.get(i).mostrar();
        }
    }

    private static void editarDocumento() {
        if (documentos.isEmpty()) {
            System.out.println("Primero debe clonar una plantilla.");
            return;
        }

        consultar(documentos);
        int indice = leerInt("Número del documento que desea editar: ");

        if (indice < 1 || indice > documentos.size()) {
            System.out.println("Índice no válido.");
            return;
        }

        documentos.get(indice - 1).editar(teclado);
        System.out.println("Documento actualizado.");
    }

    private static void probarIndependencia() {
        if (plantillas.isEmpty()) {
            System.out.println("No hay plantillas para probar.");
            return;
        }

        int indice = leerInt(
            "Seleccione una plantilla para probar (1 a "
            + plantillas.size() + "): "
        );

        if (indice < 1 || indice > plantillas.size()) {
            System.out.println("Índice no válido.");
            return;
        }

        Documento original = plantillas.get(indice - 1);
        Documento copia1 = original.clonar();
        Documento copia2 = original.clonar();

        System.out.println("\nESTADO ORIGINAL:");
        original.mostrar();

        System.out.println("\nEdite la primera copia:");
        copia1.editar(teclado);

        System.out.println("\nVERIFICACIÓN DEL ORIGINAL:");
        original.mostrar();

        System.out.println("\nSEGUNDA COPIA SIN MODIFICAR:");
        copia2.mostrar();

        System.out.println(
            "\nPrueba terminada. Compare los tres documentos."
        );
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private static int leerInt(String mensaje) {
        System.out.print(mensaje);
        return leerEntero();
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numérico válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }
}
