package Aufgabe2;

import java.util.Objects;

public class Polstelle {
    public static void main(String[] args) {
        int start = -10;         //Startwert der Tabelle
        int ende = 10;          //Endwert der Tabelle
        int schrittweite = 1;   //Schrittweite der Tabelle

        int spacing = 1;        //Abstand zwischen Werten und Trennstrichen

        int total = 0;  //bestimmt die Anzahl an schritten, die von Startwert bis Endwert gemacht werden müssen
        for (int i = start; i <= ende; ) {
            i += schrittweite;
            total++;
        }

        String[][] arr = new String[total][2];  //tabellen  [x][0]: Eingesetzter Wert   |   [x][1]: Funktionswert

        for (int i = 0; i < total; i++) {   //Eingesetzte Werte einfügen
            arr[i][0] = String.valueOf(i + start);
        }

        for (int i = 0; i < total; i++) {   //Funktionswerte einfügen
            try {   //"try" damit programm nicht bei exception: "Polstelle" abbricht
                arr[i][1] = calculate(Integer.parseInt(arr[i][0]));
            } catch (Exception e) {
                if(e instanceof DividierenDurchNull)
                    arr[i][1] = "Polstelle";    //falls DividierenDurchNull kommt, wird die Polstelle gespeichert
            }
        }

        int maxLength = 0;  //maximale länge der ersten beiden Zeilen speichern für formatierung (zentrierung und 3. Zeile)
        for (String[] strings : arr) {
            for (String string : strings)
                maxLength = Math.max(maxLength, string.length());
        }

        for (int i = 0; i < 2; i++) {
            System.out.print("|");
            for (int j = 0; j < total; j++) {
                int length = maxLength - arr[j][i].length() + 2;    //Anzahl der spaces für zentrierung
                int front = (length % 2 == 0 ? length / 2 : (length / 2 + 1));  //spaces vor Wert
                System.out.print(String.format("%" + (front) + "s", "")
                        + arr[j][i] + String.format("%" + ((length / 2)) + "s", "")
                        + "|");
            }
            System.out.println();
        }

        System.out.print("|");
        for (int j = 0; j < total; j++) {   //3. Zeile

            String value = arr[j][1];
            String[] valueA = value.split("/"); //Brüche teilen für float berechnung

            double doubleValue;
            boolean polstelle = false;

            if (valueA.length > 1)
                doubleValue = (Integer.parseInt(valueA[0]) / Float.parseFloat(valueA[1]));  //Wert nicht ganzzahlig
            else if (Objects.equals(valueA[0], "Polstelle")) {
                doubleValue = 0;    //Polstelle
                polstelle = true;
            } else
                doubleValue = Integer.parseInt(valueA[0]);  //Ganzzahlig


            System.out.print(String.format("%" + spacing + "s", "")
                    + (polstelle ?
                        "Polstelle" :
                        String.format("%" + maxLength + "f", doubleValue)) + String.format("%" + spacing + "s", "")
                    + "|");
        }
    }

    public static String calculate(int x) throws DividierenDurchNull {
        int i = (int) (Math.pow(x, 3) - (5 * Math.pow(x, 2)) - x + 5);
        if (i == 0) throw new DividierenDurchNull();
        return "1" + (i == 1 ? "" : ("/" + i));
    }
    static class DividierenDurchNull extends Exception{ }
}
