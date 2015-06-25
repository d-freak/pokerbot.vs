/**
 * PokerException.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game.exception;



/**
 * ポーカーゲームの例外
 */
public class PokerException extends Exception {
    
    /**
     * コンストラクタ
     */
    public PokerException() {
        super("[PokerException]");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public PokerException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public PokerException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public PokerException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

