package e196244_r176519.ft.unicamp.br.aula03.Puzzle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import e196244_r176519.ft.unicamp.br.aula03.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PuzzleFragment extends Fragment {

    private LinearLayout view;
    private AbstractPuzzle emptyPuzzle;


    public PuzzleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            // Inflate the layout for this fragment
            view = (LinearLayout) inflater.inflate(R.layout.fragment_puzzle, container, false);
            startPuzzle(0, view);
        }

        return view;
    }

    private void startPuzzle(int puzzle, LinearLayout view) {
        Board board = Boards.getPuzzle(puzzle);

        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();


        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            // Configurando os par^ametros
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
            for (int j = 0; j < board.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(board.getGameBlock(i,j));
                imageView.setId(Integer.parseInt(i + Integer.toString(j)));
                imageViews.add(imageView);


                // Configurando os parametros
                imageView.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                board.getWidth(),
                                board.getHeight()));

                row.addView(imageView);

            }
            emptyPuzzle = new EmptyPuzzle(board, imageViews);
            view.addView(row);

        }
    }

}
