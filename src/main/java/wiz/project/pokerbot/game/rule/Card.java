/**
 * Card.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game.rule;



/**
 * カード
 */
public enum Card {
    S_02,
    H_02,
    D_02,
    C_02,
    S_03,
    H_03,
    D_03,
    C_03,
    S_04,
    H_04,
    D_04,
    C_04,
    S_05,
    H_05,
    D_05,
    C_05,
    S_06,
    H_06,
    D_06,
    C_06,
    S_07,
    H_07,
    D_07,
    C_07,
    S_08,
    H_08,
    D_08,
    C_08,
    S_09,
    H_09,
    D_09,
    C_09,
    S_10,
    H_10,
    D_10,
    C_10,
    S_11,
    H_11,
    D_11,
    C_11,
    S_12,
    H_12,
    D_12,
    C_12,
    S_13,
    H_13,
    D_13,
    C_13,
    S_01,
    H_01,
    D_01,
    C_01;
    
    
    
    /**
     * スートを取得
     * 
     * @return スート。
     */
    public Suit getSuit() {
        switch (this) {
        case S_02:
        case S_03:
        case S_04:
        case S_05:
        case S_06:
        case S_07:
        case S_08:
        case S_09:
        case S_10:
        case S_11:
        case S_12:
        case S_13:
        case S_01:
            return Suit.SPADE;
        case H_02:
        case H_03:
        case H_04:
        case H_05:
        case H_06:
        case H_07:
        case H_08:
        case H_09:
        case H_10:
        case H_11:
        case H_12:
        case H_13:
        case H_01:
            return Suit.HEART;
        case D_02:
        case D_03:
        case D_04:
        case D_05:
        case D_06:
        case D_07:
        case D_08:
        case D_09:
        case D_10:
        case D_11:
        case D_12:
        case D_13:
        case D_01:
            return Suit.DIAMOND;
        case C_02:
        case C_03:
        case C_04:
        case C_05:
        case C_06:
        case C_07:
        case C_08:
        case C_09:
        case C_10:
        case C_11:
        case C_12:
        case C_13:
        case C_01:
            return Suit.CLUB;
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
        case S_02:
            return "[2♠]";
        case D_02:
            return "[2♢]";
        case H_02:
            return "[2♡]";
        case C_02:
            return "[2♣]";
        case S_03:
            return "[3♠]";
        case D_03:
            return "[3♢]";
        case H_03:
            return "[3♡]";
        case C_03:
            return "[3♣]";
        case S_04:
            return "[4♠]";
        case D_04:
            return "[4♢]";
        case H_04:
            return "[4♡]";
        case C_04:
            return "[4♣]";
        case S_05:
            return "[5♠]";
        case D_05:
            return "[5♢]";
        case H_05:
            return "[5♡]";
        case C_05:
            return "[5♣]";
        case S_06:
            return "[6♠]";
        case D_06:
            return "[6♢]";
        case H_06:
            return "[6♡]";
        case C_06:
            return "[6♣]";
        case S_07:
            return "[7♠]";
        case D_07:
            return "[7♢]";
        case H_07:
            return "[7♡]";
        case C_07:
            return "[7♣]";
        case S_08:
            return "[8♠]";
        case D_08:
            return "[8♢]";
        case H_08:
            return "[8♡]";
        case C_08:
            return "[8♣]";
        case S_09:
            return "[9♠]";
        case D_09:
            return "[9♢]";
        case H_09:
            return "[9♡]";
        case C_09:
            return "[9♣]";
        case S_10:
            return "[T♠]";
        case D_10:
            return "[T♢]";
        case H_10:
            return "[T♡]";
        case C_10:
            return "[T♣]";
        case S_11:
            return "[J♠]";
        case D_11:
            return "[J♢]";
        case H_11:
            return "[J♡]";
        case C_11:
            return "[J♣]";
        case S_12:
            return "[Q♠]";
        case D_12:
            return "[Q♢]";
        case H_12:
            return "[Q♡]";
        case C_12:
            return "[Q♣]";
        case S_13:
            return "[K♠]";
        case D_13:
            return "[K♢]";
        case H_13:
            return "[K♡]";
        case C_13:
            return "[K♣]";
        case S_01:
            return "[A♠]";
        case D_01:
            return "[A♢]";
        case H_01:
            return "[A♡]";
        case C_01:
            return "[A♣]";
        default:
            throw new InternalError();
        }
    }
    
}

