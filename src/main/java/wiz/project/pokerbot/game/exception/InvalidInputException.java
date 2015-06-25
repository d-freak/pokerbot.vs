/**
 * InvalidInputException.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game.exception;



/**
 * 不正入力例外
 */
public class InvalidInputException extends PokerException {
    
    /**
     * コンストラクタ
     */
    public InvalidInputException() {
        super("[InvalidInputException]");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public InvalidInputException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public InvalidInputException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public InvalidInputException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

