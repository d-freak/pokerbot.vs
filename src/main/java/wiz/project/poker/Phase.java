/**
 * Phase.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.poker;



/**
 * フェイズ
 */
public enum Phase {
    
    /**
     * プリフロップ
     */
    PRE_FLOP,
    
    /**
     * フロップ
     */
    FLOP,
    
    /**
     * ターン
     */
    TURN,
    
    /**
     * リバー
     */
    RIVER;
    
    
    
    /**
     * 次のフェイズを取得
     * 
     * @return 次のフェイズ。
     */
    public Phase getNext() {
        switch (this) {
        case PRE_FLOP:
            return FLOP;
        case FLOP:
            return TURN;
        case TURN:
            return RIVER;
        case RIVER:
            return RIVER;
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        switch (this) {
        case PRE_FLOP:
            return "Preflop";
        case FLOP:
            return "Flop";
        case TURN:
            return "Turn";
        case RIVER:
            return "River";
        default:
            throw new InternalError();
        }
    }
}

