package org.eagle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {
    char[][] cells = new char[5][5];
    char[][] newCells = new char[5][5];
    public void play(String line1, String line2, String line3, String line4, String line5){
        cells= cellsFinde(line1, line2, line3, line4, line5);
        int neighbouCount=0;
        char[] neighbours = new char[8];
        for(int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                neighbours = neighbourFinde(i,j);
                for (char neighbour : neighbours){
                    if (neighbour=='*') neighbouCount++;
                }
                newCells[i][j] = isLiveCell(neighbouCount,i,j);
                neighbouCount=0;
            }
        }

        System.out.println("**Seed**");
        for(char[] c : cells){
            for (char c2 : c){
                System.out.print(c2);
            }
            System.out.println();
        }

        System.out.println("**First Generation**");
        for(char[] c : newCells){
            for (char c2 : c){
                System.out.print(c2);
            }
            System.out.println();
        }
    }

    char[][] cellsFinde(String line1, String line2, String line3, String line4, String line5){
        List<String> lines = new ArrayList<>(Arrays.asList(line1,line2,line3,line4,line5));
        int lineNumber=0;
        //char[][] cells = new char[5][5];
        for (String line : lines){
            for (int i = 0 ; i<line.length(); i++){
                cells[lineNumber][i]=line.charAt(i);
            }
            lineNumber++;
        }
        return  cells;
    }

    char[] neighbourFinde(int d1, int d2){
        char[]neighbour=new char[9];
        int arrayNumber = 0;
        for (int i = d1-1; i<=d1+1;i++){
            if (i<0 || i>4){
                continue;
            } else {
                for (int j = d2-1; j<=d2+1;j++){
                    if (j<0 || j>4 || (i==d1 && j==d2)){
                        continue;
                    }else {
                        neighbour[arrayNumber] = cells[i][j];
                        arrayNumber++;
                    }
                }
            }
        }
        return neighbour;
    }

    char isLiveCell(int neighbourCount, int i, int j){
        char cell = '.';
        if ((neighbourCount<2 || neighbourCount>3) && cells[i][j]=='*') cell='.';
        if ((neighbourCount==2 || neighbourCount==3) && cells[i][j]=='*') cell=cells[i][j];
        if (neighbourCount==3  && cells[i][j]=='.') cell='*';
        return cell;
    }
}