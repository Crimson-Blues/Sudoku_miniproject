package Model;

public abstract class BoardAdapter implements IBoard{
    @Override
    public boolean isCellValid(int cellRow, int cellCol) {
        return false;
    }
}
