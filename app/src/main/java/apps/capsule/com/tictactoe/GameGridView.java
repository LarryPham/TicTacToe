package apps.capsule.com.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class GameGridView extends GridLayout {
    private int mSide;
    private Button[][] mButtons;
    private TextView mStatus;

    public GameGridView(Context context, int width, int newSide, View.OnClickListener listener) {
        super(context);
        mSide = newSide;

        setColumnCount(mSide);
        setRowCount(mSide + 1);
        mButtons = new Button[mSide][mSide];

        // Create the buttons and add them to this GridLayout
        for (int row = 0; row < mSide; row++) {
            for (int col = 0; col < mSide; col++) {
                mButtons[row][col] = new Button(context);
                mButtons[row][col].setTextSize((int) (width * .2));
                mButtons[row][col].setOnClickListener(listener);
                addView(mButtons[row][col], width, width);
            }
        }

        // Setup the layout's params of 4th row of gridlayout.
        mStatus = new TextView(context);
        final GridLayout.Spec rowSpec = GridLayout.spec(mSide, 1);
        final GridLayout.Spec colSpec = GridLayout.spec(0, mSide);
        final GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
        mStatus.setLayoutParams(params);

        mStatus.setWidth(mSide * width);
        mStatus.setHeight(width);
        mStatus.setGravity(Gravity.CENTER);
        mStatus.setTextColor(Color.WHITE);
        mStatus.setBackgroundColor(Color.parseColor("#FF34D4A7"));
        mStatus.setTextSize((int) (width * .10));
        addView(mStatus);
    }

    public void setStatusText(String text) {
        mStatus.setText(text);
    }

    public void setStatusBackgroundColor(int color) {
        mStatus.setBackgroundColor(color);
    }

    public void setButtonText(int row, int column, String text) {
        mButtons[row][column].setText(text);
    }

    public boolean isButton(Button button, int row, int column) {
        return (button == mButtons[row][column]);
    }

    public void resetButtons() {
        for (int row = 0; row < mSide; row++) {
            for (int col = 0; col < mSide; col++) {
                mButtons[row][col].setText("");
            }
        }
    }

    public void enableButtons(boolean enabled) {
        for (int row = 0; row < mSide; row++) {
            for (int col = 0; col < mSide; col++) {
                mButtons[row][col].setEnabled(enabled);
            }
        }
    }
}
