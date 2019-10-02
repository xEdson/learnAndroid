package e196244_r176519.ft.unicamp.br.aula03.Puzzle;

import android.widget.ImageView;

import java.util.ArrayList;

public abstract class AbstractPuzzle {

    private Board board;

    public AbstractPuzzle(Board board, ArrayList<ImageView> image) {
        this.board = board;
        this.image = image;
    }

    private ArrayList<ImageView> image;


    public void readraW() {

        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                image.get(i * board.getNumColumns() + j).setImageResource(board.getGameBlock(i, j));
            }
        }

    }

    public abstract void addListener(ImageView imageView, int line, int column);

    public abstract boolean endGame();
}
