package e196244_r176519.ft.unicamp.br.aula03.Puzzle;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class EmptyPuzzle extends AbstractPuzzle {
    Board board;

    public EmptyPuzzle(final Board board, final ArrayList<ImageView> image) {
        super(board, image);
        this.board = board;
        for (final ImageView imageView : image) {
            final String id = String.valueOf(imageView.getId());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id1 = Integer.parseInt(((String.valueOf(imageView.getId()).length() > 1 ? "" : "0") + imageView.getId()).split("")[1]);
                    int id2 = Integer.parseInt(((String.valueOf(imageView.getId()).length() > 1 ? "" : "0") + imageView.getId()).split("")[2]);
                    List<Integer> lst = board.getGameIndex();
                    int whiteInColumn = findwhiteSpaceInColumn(id2);
                    int whiteInLine = findwhiteSpaceInLine(id1);

                    if (whiteInColumn != -1) {
                        if (whiteInColumn < id1) {
                            for (int x = whiteInColumn; id1 > x; x++) {

                                int auxiliar = lst.get((x + 1) * board.getNumColumns() + id2);

                                lst.set((x + 1) * board.getNumColumns() + id2, lst.get(x * board.getNumColumns() + id2));

                                lst.set(x * board.getNumColumns() + id2, auxiliar);

                            }
                            EmptyPuzzle.super.reDraw();
                        } else {
                            for (int x = whiteInColumn; x > id1; x--) {

                                int auxiliar = lst.get((x - 1) * board.getNumColumns() + id2);

                                lst.set((x - 1) * board.getNumColumns() + id2, lst.get(x * board.getNumColumns() + id2));

                                lst.set(x * board.getNumColumns() + id2, auxiliar);

                            }
                            EmptyPuzzle.super.reDraw();
                        }
                    } else if (whiteInLine != -1) {
                        if (whiteInLine < id2) {
                            for (int x = whiteInLine; id2 > x; x++) {

                                int auxiliar = lst.get(id1 * board.getNumLines() + (x + 1));

                                lst.set(id1 * board.getNumLines() + (x + 1), lst.get(id1 * board.getNumLines() + x));

                                lst.set(id1 * board.getNumLines() + x, auxiliar);

                            }
                            EmptyPuzzle.super.reDraw();
                        } else {
                            for (int x = whiteInLine; x > id2; x--) {

                                int auxiliar = lst.get(id1 * board.getNumLines() + (x - 1));

                                lst.set(id1 * board.getNumLines() + (x - 1), lst.get(id1 * board.getNumLines() + x));

                                lst.set(id1 * board.getNumLines() + x, auxiliar);

                            }
                            EmptyPuzzle.super.reDraw();
                        }
                    }
                }

            });
        }
    }

    //line, id2
    public int findwhiteSpaceInColumn(int col) {
        for (int linha = 0; linha < board.getNumLines(); linha++) {
            if (board.getGameIndex(linha, col) == 0) {
                return linha;
            }
        }
        return -1;
    }

    public int findwhiteSpaceInLine(int line) {
        for (int column = 0; column < board.getNumColumns(); column++) {
            if (board.getGameIndex(line, column) == 0) {
                return column;
            }
        }
        return -1;
    }

    @Override
    public void addListener(ImageView imageView, final int line, final int column) {
        List<String> bla = new ArrayList<>();
    }

    @Override
    public void addListener(ImageView imageView, int line, int column, Board board) {

    }

    @Override
    public boolean endGame() {
        return false;
    }
}