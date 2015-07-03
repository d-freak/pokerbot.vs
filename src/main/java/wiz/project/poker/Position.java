/**
 * Position.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.poker;



/**
 * ポジション
 */
public enum Position {
    
    SB,
    BB,
    UTG,
    EP2,
    MP1,
    MP2,
    HJ,
    CO,
    BTN;
    
    
    
    /**
     * 次のポジションを取得
     * 
     * @return 次のポジション。
     */
    public Position getNext() {
        switch (this) {
        case SB:
            return BB;
        case BB:
            return UTG;
        case UTG:
            return EP2;
        case EP2:
            return MP1;
        case MP1:
            return MP2;
        case MP2:
            return HJ;
        case HJ:
            return CO;
        case CO:
            return BTN;
        case BTN:
            return SB;
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
        case SB:
            return "[SB ]";
        case BB:
            return "[BB ]";
        case UTG:
            return "[UTG]";
        case EP2:
            return "[EP2]";
        case MP1:
            return "[MP1]";
        case MP2:
            return "[MP2]";
        case HJ:
            return "[HJ ]";
        case CO:
            return "[CO ]";
        case BTN:
            return "[BTN]";
        default:
            throw new InternalError();
        }
    }
    
}

