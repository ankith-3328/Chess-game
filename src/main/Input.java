package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    Board board;
    public Input(Board board){
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null){
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if(board.selectedPiece != null){
            Move move = new Move(board, board.selectedPiece, col, row);

            if(board.isValidMove(move)){
                board.makeMove(move);
            }
            else{
                board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null){
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            board.repaint();
        }
    }
}

//public class Input extends MouseAdapter {
//
//    Board board;
//
//    private boolean isPieceSelected = false;
//
//    public Input(Board board) {
//        this.board = board;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int col = e.getX() / board.tileSize;
//        int row = e.getY() / board.tileSize;
//
//        if (!isPieceSelected) {
//            // First click to select the piece
//            Piece pieceXY = board.getPiece(col, row);
//            if (pieceXY != null) {
//                // Select the piece and mark it as selected
//                board.selectedPiece = pieceXY;
//                isPieceSelected = true; // Piece is now selected
//            }
//        } else {
//            // Second click to move the selected piece to the clicked location
//            if (board.selectedPiece != null) {
//                Move move = new Move(board, board.selectedPiece, col, row);
//
//                // Only move if it's a valid move
//                if (board.isValidMove(move)) {
//                    board.makeMove(move);
//                } else {
//                    // Reset the piece's position if move is invalid
//                    board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
//                    board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
//                }
//
//                // Deselect the piece after the move
//                board.selectedPiece = null;
//                isPieceSelected = false; // Reset selection flag
//                board.repaint();
//            }
//        }
//    }
//}
