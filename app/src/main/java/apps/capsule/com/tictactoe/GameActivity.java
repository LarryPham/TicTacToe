package apps.capsule.com.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = GameActivity.class.getSimpleName();
    private TicTacToe mGameController;
    private GameGridView mGameGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameController = new TicTacToe();

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x / TicTacToe.SIDE;
        final ButtonHandler handler = new ButtonHandler();

        mGameGridView = new GameGridView(this, width, TicTacToe.SIDE, handler);
        mGameGridView.setStatusText(mGameController.result());
        setContentView(mGameGridView);
    }

    private class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Log.w(TAG, "Inside Onclick, view = " + view);
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    if (mGameGridView.isButton((Button) view, row, col)) {
                        int play = mGameController.play(row, col);
                        if (play == 1) {
                            mGameGridView.setButtonText(row, col, "X");
                        } else if (play == 2) {
                            mGameGridView.setButtonText(row, col, "O");
                        }

                        if (mGameController.isGameOver()) {
                            mGameGridView.setStatusBackgroundColor(Color.parseColor("#FFE4382F"));
                            mGameGridView.enableButtons(false);
                            mGameGridView.setStatusText(mGameController.result());
                            showNewGameDialog();
                        }
                    }
                }
            }
        }
    }

    public void showNewGameDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("This is fun");
        alertBuilder.setMessage("Play again?");
        final DialogListener listener = new DialogListener();
        alertBuilder.setPositiveButton("Yes", listener);
        alertBuilder.setNegativeButton("No", listener);
        alertBuilder.setCancelable(false);
        alertBuilder.show();
    }

    private class DialogListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int id) {
            if (id == -1) {
                mGameController.resetGame();
                mGameGridView.enableButtons(true);
                mGameGridView.resetButtons();
                mGameGridView.setStatusBackgroundColor(Color.parseColor("#FF34D4A7"));
                mGameGridView.setStatusText(mGameController.result());
            } else if (id == -2) {
                GameActivity.this.finish();
            }
        }
    }
}
