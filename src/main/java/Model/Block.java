package Model;

import java.util.ArrayList;
import java.util.List;

public class Block extends BoardAdapter{
    private List<List<Integer>> block;

    public Block(int rowNum, int colNum){
        for(int i = 0; i < rowNum; i++){
            block.add(new ArrayList<>());
        }
    }
    @Override
    public boolean isCellValid(int cellRow, int cellCol){
        return false;
    }
}
