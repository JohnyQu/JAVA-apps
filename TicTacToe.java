import java.util.Scanner;

public class TicTacToe {
    public static void main (String[] args){
        System.out.println("Witaj w grze, podaj rozmiar planszy");
        int wymiar = new Scanner(System.in).nextInt();
        char obecnySymbol = 'X';
        
        char[][] plansza =
                new char[wymiar][wymiar];
//                {{'O', 'X', 'O'},
//                 {'O', 'X', ' '},
//                 {' ', 'O', 'O'}};

        boolean czyKontynuować = true;
        int liczbaRuchow = 0;
        while (czyKontynuować && liczbaRuchow <= wymiar * wymiar) {
            TicTacToe.drukujPlansze(plansza);
            boolean dobryRuch = wykonajRuch(plansza, obecnySymbol);
            if (dobryRuch){
                liczbaRuchow++;
                boolean wygranaWiersze = sprawdzWiersze(plansza, obecnySymbol);
                boolean wygranaKolumny = sprawdzKolumny(plansza, obecnySymbol);
                boolean wygranaSkos1 = sprawdzSkos1(plansza, obecnySymbol);
                boolean wygranaSkos2 = sprawdzSkos2(plansza, obecnySymbol);
                if(wygranaWiersze || wygranaKolumny || wygranaSkos1 || wygranaSkos2){
                    TicTacToe.drukujPlansze(plansza);
                    System.out.println("GRATULACJE, " + obecnySymbol);
                    czyKontynuować = false;
                }

                obecnySymbol = obecnySymbol == 'X' ? 'O' : 'X';
            }


//            if (obecnySymbol == 'X'){
//                obecnySymbol = 'O';
//            }
//            else {
//                obecnySymbol = 'X';
//            }
        }
    }

    public static boolean sprawdzWiersze(char [] [] plansza, char symbol){
        int wymiar = plansza.length;
        for(int wiersz = 0; wiersz < wymiar; wiersz++){
            boolean wygrana = true;
            for(int kolumna = 0; kolumna < wymiar; kolumna++){
                if (plansza[wiersz][kolumna] != symbol){
                    wygrana = false;
                    break;
                }
            }
            if (wygrana){return true;}
        }
        return false;
    }

    public static boolean sprawdzKolumny(char [] [] plansza, char symbol){
        int wymiar = plansza.length;
        for(int kolumna = 0; kolumna < wymiar; kolumna++){
            boolean wygrana = true;
            for(int wiersz = 0; wiersz < wymiar; wiersz++){
                if (plansza[wiersz][kolumna] != symbol){
                    wygrana = false;
                    break;
                }
            }
            if (wygrana){return true;}
        }
        return false;
    }

    public static boolean sprawdzSkos1(char[][] plansza, char symbol){
        int wymiar = plansza.length;
        for (int i = 0; i < wymiar; i++){
            if (plansza[i][i] != symbol){
                return false;
            }
        }
        return true;
    }

    public static boolean sprawdzSkos2(char[][] plansza, char symbol){
        int wymiar = plansza.length;
        int x = 0;
        int y = wymiar - 1;
        while (plansza[y][x] == symbol && x <= wymiar && y >= 0){
            x++;
            y--;
            if(x == wymiar){
                return true;
            }
        }

        return false;
    }

    public static boolean wykonajRuch (char[][] plansza, char symbol){
        System.out.println(symbol + ": Twój ruch");
        System.out.println("Podaj indeks wiersza");
        int wiersz = new Scanner(System.in).nextInt();
        System.out.println("Podaj indeks kolumny");
        int kolumna = new Scanner(System.in).nextInt();
        boolean dobryRuch = plansza[wiersz][kolumna] == 0;
        if (!dobryRuch){
            System.out.println("Niepoprawny ruch");
            return  false;
        }
        else{
            plansza[wiersz][kolumna] = symbol;
            return true;
        }
    }

    public static void drukujPlansze (char[][] plansza) {
        int wymiar = plansza.length;
        //nagłówki kolumn
        System.out.print("\t");
        //pętla drukująca nagłówki kolumn
        for(int i = 0; i < wymiar; i++){
            System.out.print(i + "\t");
        }
        System.out.println();

        for (int wiersz = 0; wiersz < wymiar; wiersz++){
            System.out.print(wiersz + ":\t");
            for (int kolumna = 0; kolumna < wymiar; kolumna++){
                System.out.print(plansza[wiersz][kolumna] + "\t");
            }
            System.out.println();
        }
    }
}
