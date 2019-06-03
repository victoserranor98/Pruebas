/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author VICTOR SERRANO
 */
public class Pruebas {

     public static void Series(ArrayList<Double> n) {
        System.out.println("\n*****Prueba de Series*****");
        double x[] = new double[n.size() - 1];
        double y[] = new double[n.size() - 1];
        int aux = 0, aux2 = 0;
        for (int i = 0; i < (n.size()); i++) {
            if (i != 0) {
                y[aux] = n.get(i);
                aux++;
            }
            if (i != n.size() - 1) {
                x[aux2] = n.get(i);
                aux2++;
            }
        }
        int subintervalos = 25;
        double cont[] = new double[subintervalos];
        int x1 = 0;
        int xx = 0;
        for (double i = 0; i < 5; i++) {
            for (double k = 0; k < 5; k++) {
                for (int j = 0; j < x.length; j++) {
                    if (x[j] < ((i + 1) / 5) && y[j] < ((k + 1) / 5) && x[j] >= (i / 5) && y[j] >= (k / 5)) {
                        x1++;
                    }
                }
                cont[xx] = x1;
                xx++;
                x1 = 0;
            }
        }
        double suma = 0;
        for (int i = 0; i < cont.length; i++) {
            suma = suma + Math.pow((cont[i] - ((n.size() - 1.0) / 25.0)), 2);
        }
        double resultado = suma * (cont.length / (double) (n.size() - 1));
        System.out.println("Suma: " + suma + "\nResultado: " + resultado);
        if (suma < 36.41) {
            System.out.println("NO se rechaza la hipotesis de uniformidad de numeros pseudo aleatorios");
        } else {
            System.out.println("SE rechaza la hipotesis de uniformidad de numeros pseudo aleatorios");
        }
    }

    public static void Kolmogorov(ArrayList<Double> n) {
        System.out.println("\n *****Prueba de Kolmogorov***** ");
        int num = n.size();
        DecimalFormat df = new DecimalFormat("0.00000");
        Collections.sort(n);
        ArrayList<Double> distribucionAc = new ArrayList<>();

        String aux = "";
        double Daux = 0.00;
        System.out.println("\nNumeros Ordenados con Distribucion Acumulada\n");
        System.out.println("\tFo(x)\t\tFn(x)");
        for (int i = 0; i < n.size(); i++) {
            distribucionAc.add((double) (i + 1) / num);
            aux = aux + (i + 1) + "\t" + n.get(i) + "\t\t" + df.format(distribucionAc.get(i)) + "\n";
            double Dn = Math.abs(distribucionAc.get(i) - n.get(i));
            if (Dn > Daux) {
                Daux = Dn;
            }
        }
        System.out.println("" + aux);
        System.out.println("El estadistico Dn es: " + Daux);
        double d_alpha_n = 0.242;
        if (Daux < d_alpha_n) {
            System.out.println("No se rechazar la hipotesis de que los números generados provienen de una distribución uniforme");
        } else {
            System.out.println("Se rechaza la hipotesis de que los números generados provienen de una distribución uniforme");
        }
    }

    public static void Poker() {
        System.out.println("\n*****Prueba de Poker*****");
        ArrayList<Double> n = new ArrayList<>();
        double valoresLista[] = {0.64138, 0.55837, 0.81593, 0.04994, 0.61265, 0.06787, 0.30465, 0.54264, 0.81159, 0.61163, 0.47681, 0.52127, 0.69239, 0.92006, 0.37913, 0.32035, 0.37248, 0.57836, 0.19180, 0.28920, 0.79302, 0.08124, 0.53401, 0.48201, 0.03268, 0.38087, 0.68054, 0.69251, 0.60284, 0.69351};
        for (int i = 0; i < valoresLista.length; i++) {
            n.add(valoresLista[i]);
        }
        int diferentes = 0, par = 0, par2 = 0, tercia = 0, full = 0, poker = 0, quintilla = 0;
        String aux, numero;
        for (int i = 0; i < (n.size()); i++) {
            aux = String.valueOf(n.get(i));
            numero = aux.substring(2, aux.length());
            String x[] = numero.split("");
            int auxnum[] = new int[10];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < x.length; k++) {
                    if (x[k].equals(String.valueOf(j))) {
                        auxnum[j]++;
                    }
                }
                if (j == 9) {
                    int paraux = 0, terciaux = 0, pokeraux = 0, quintillaaux = 0;
                    for (int k = 0; k < 10; k++) {
                        if (auxnum[k] == 2) {
                            paraux++;
                        }
                        if (auxnum[k] == 3) {
                            terciaux++;
                        }
                        if (auxnum[k] == 4) {
                            pokeraux++;
                        }
                        if (auxnum[k] == 5) {
                            quintillaaux++;
                        }
                    }
                    if (paraux != 0) {
                        if (paraux == 2) {
                            par2++;
                        } else if (terciaux == 1) {
                            full++;
                        } else {
                            par++;
                        }
                    } else if (terciaux == 1) {
                        tercia++;
                    } else if (pokeraux == 1) {
                        poker++;
                    } else if (quintillaaux == 1) {
                        quintilla++;
                    } else {
                        diferentes++;
                    }
                }
            }
        }
        double resultado = (Math.pow((diferentes - (n.size() * 0.3024)), 2) / (n.size() * 0.3024)) + (Math.pow((par - (n.size() * 0.504)), 2) / (n.size() * 0.504))
                + (Math.pow((par2 - (n.size() * 0.108)), 2) / (n.size() * 0.108)) + (Math.pow((tercia - (n.size() * 0.072)), 2) / (n.size() * 0.072))
                + (Math.pow((full - (n.size() * 0.009)), 2) / (n.size() * 0.009)) + (Math.pow((poker - (n.size() * 0.0045)), 2) / (n.size() * 0.0045))
                + (Math.pow((quintilla - (n.size() * 0.0001)), 2) / (n.size() * 0.0001));
        System.out.println("Diferentes: " + diferentes + "\nPares: " + par + "\nDoble Par:" + par2 + "\nTercia:" + tercia + "\nFull:" + full + "\nPoker:" + poker + "\nQuintilla:" + quintilla);
        System.out.println("\ndif: " + resultado);
    }

    public static void Corridas() {
        System.out.println("\n*****Prueba de Corridas*****");
        ArrayList<Double> n = new ArrayList<>();
        double valoresLista[] = {0.64138, 0.55837, 0.81593, 0.04994, 0.61265, 0.06787, 0.30465, 0.54264, 0.81159, 0.61163, 0.47681, 0.52127, 0.69239, 0.92006, 0.37913, 0.32035, 0.37248, 0.57836, 0.19180, 0.28920, 0.79302, 0.08124, 0.53401, 0.48201, 0.03268, 0.38087, 0.68054, 0.69251, 0.60284, 0.69351};
        for (int i = 0; i < valoresLista.length; i++) {
            n.add(valoresLista[i]);
        }
        double n1 = 0, n2 = 0, cont = 1;
        double varianza = 0.0, promedio = 0.0;
        int con[] = new int[n.size()];
        double suma = 0;
        for (int i = 0; i < n.size(); i++) {
            if (n.get(i) < 0.5) {
                n2++;
                con[i] = 1;
            } else {
                n1++;
                con[i] = 0;
            }
            suma = suma + n.get(i);
            if (i != 0) {
                if (con[i] != con[i - 1]) {
                    cont++;
                }
            }
        }
        promedio = ((2 * n1 * n2) / n.size()) + 1;
        varianza = (((2 * n1 * n2) * (2 * n1 * n2 - n.size())) / ((Math.pow(n.size(), 2)) * (n.size() - 1)));
        System.out.println("N1: " + n1 + "\nN2: " + n2 + "\nSumatoris: " + suma + "\nCorridas: " + cont + "\nPromedio de Corridas: " + promedio + "\nVarianza: " + varianza);
    }
    
}
