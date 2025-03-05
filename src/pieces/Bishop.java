package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece{
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row) && col>=0 && col<=7 && row>=0 && row<=7;
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //upleft
        if(this.col > col && this.row > row){
            for(int i = 1; i<Math.abs(this.col - col); i++){
                if(board.getPiece(this.col - i, this.row - i) != null){
                    return true;
                }
            }
        }
        //upright
        if(this.col < col && this.row > row){
            for(int i = 1; i<Math.abs(this.col - col); i++){
                if(board.getPiece(this.col + i, this.row - i) != null){
                    return true;
                }
            }
        }
        //downleft
        if(this.col > col && this.row < row){
            for(int i = 1; i<Math.abs(this.col - col); i++){
                if(board.getPiece(this.col - i, this.row + i) != null){
                    return true;
                }
            }
        }
        //downright
        if(this.col < col && this.row < row){
            for(int i = 1; i<Math.abs(this.col - col); i++){
                if(board.getPiece(this.col + i, this.row + i) != null){
                    return true;
                }
            }
        }

        return false;
    }
}