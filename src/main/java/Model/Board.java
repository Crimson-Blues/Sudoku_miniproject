package Model;

import java.util.ArrayList;
import java.util.List;

public class Board extends BoardAdapter{
    private List<List<Cell>> board;


    public Board(){
        board = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            board.add(new ArrayList<>());
        }
    }

    public void fillBoard(){

    }

    @Override
    public boolean isCellValid(int cellRow, int cellCol){
        int cell = board.get(cellRow).get(cellCol).getValue();
        //Check Column and Row validity first
        //Row:
        for(int i = 0; i < 6; i++){
            if(cell == board.get(i).get(cellCol).getValue()){
                return false;
            }
        }
        //Column:
        for(int i = 0; i < 6; i++){
            if(cell == board.get(cellRow).get(i).getValue()){
                return false;
            }
        }

        return true;
    }

}
