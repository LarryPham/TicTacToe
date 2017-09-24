package apps.capsule.com.tictactoe;

public class TicTacToe {
    public static final int SIDE = 3;
    private int mTurn;
    private int[][] mGame;

    public TicTacToe() {
        mGame = new int[SIDE][SIDE];
        resetGame();
    }

    public int play(int row, int col) {
        int currentTurn = mTurn;
        if (row >= 0 && col >= 0 && row < SIDE && col < SIDE && mGame[row][col] == 0) {
            mGame[row][col] = mTurn;
            if (mTurn == 1) {
                mTurn = 2;
            } else {
                mTurn = 1;
            }
            return currentTurn;
        } else {
            return 0;
        }
    }

    public int whoWon() {
        int rows = checkRows();
        if (rows > 0) {
            return rows;
        }
        int columns = checkColumns();
        if (columns > 0) {
            return columns;
        }
        int diagonals = checkDiagonals();
        if (diagonals > 0) {
            return diagonals;
        }
        return 0;
    }

    protected int checkRows() {
        for (int row = 0; row < SIDE; row++) {
            if (mGame[row][0] != 0 && mGame[row][0] == mGame[row][1] && mGame[row][1] == mGame[row][2]) {
                return mGame[row][0];
            }
        }
        return 0;
    }

    protected int checkColumns() {
        for (int col = 0; col < SIDE; col++) {
            if (mGame[0][col] != 0 && mGame[0][col] == mGame[1][col] && mGame[1][col] == mGame[2][col]) {
                return mGame[0][col];
            }
        }
        return 0;
    }

    protected int checkDiagonals() {
        if (mGame[0][0] != 0 && mGame[0][0] == mGame[1][1] && mGame[1][1] == mGame[2][2]) {
            return mGame[0][0];
        }
        if (mGame[0][2] != 0 && mGame[0][2] == mGame[1][1] && mGame[1][1] == mGame[2][0]) {
            return mGame[2][0];
        }
        return 0;
    }

    public boolean cannotPlay() {
        boolean result = true;
        for (int row = 0; row < SIDE; row++) {
            for (int col = 0; col < SIDE; col++) {
                if (mGame[row][col] == 0) {
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean isGameOver() {
        return cannotPlay() || (whoWon() > 0);
    }

    public void resetGame() {
        for (int row = 0; row < SIDE; row++) {
            for (int col = 0; col < SIDE; col++) {
                mGame[row][col] = 0;
            }
        }
        mTurn = 1;
    }

    public String result() {
        if (whoWon() > 0) {
            return "Player " + whoWon() + " won";
        } else if (cannotPlay()) {
            return "Tie Game";
        } else {
            return "Play!!";
        }
    }
}
