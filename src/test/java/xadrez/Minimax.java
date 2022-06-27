//package xadrez;
//
//import pecas.Cor;
//
//import java.math.BigDecimal;
//import java.util.Vector;
//
//public class Minimax {
//
//    private BigDecimal value = BigDecimal.valueOf(0);
//
//    public void minimax(Cor color, int depth){
//        BigDecimal val = BigDecimal.valueOf(0);
//        if(color == Cor.BRANCO) {
//            val = Max(depth); // white moves first
//        } else {
//            val = Min(depth); // black moves first
//        }
//    }
//
//    private BigDecimal Max(int depth) {
//        if (depth == 0) {
//            return value;
//        }
//
//        BigDecimal val = val.subtract(Min(depth-1));
//        if (val.compareTo(best)>0) {
//            best = val;
//            ; // Current choice of move
//        }
//        mv.undo(board);
//        return best;
//    }
//
//    /**
//     * The search algorithm for min (Black player)
//     * @param depth - The current depth
//     * @return the best evaluation
//     */
//    private BigDecimal Min(int depth) {
//        if (depth == 0)
//            return estimate();
//        int best = -INFINITY;
//        Vector v = successors(false);
//        if (v!=null) {
//            int siz = v.size();
//            while(v.size()>0) {
//                Move mv = (Move)v.remove(0);
//                mv.perform(board);
//                int val = -Max(depth-1);
//                if (val>best) {
//                    best = val;
//                    if (!this.white) {
//                        mm = mv; // Current choice of move
//                    }
//                }
//                mv.undo(board);
//            }
//        }
//        return best;
//    }
//
//}
