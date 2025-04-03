package Model;

public class Cell{
    private int value;

    public Cell(){
        value = Integer.parseInt(null);
    }

    public Cell(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }
}
