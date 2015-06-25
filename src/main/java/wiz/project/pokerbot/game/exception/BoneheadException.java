/**
 * BoneheadException.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game.exception;



/**
 * 反則例外
 */
public class BoneheadException extends PokerException {
    
    /**
     * コンストラクタ
     */
    public BoneheadException() {
        super("[BoneheadException]");
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     */
    public BoneheadException(final String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     * 
     * @param e 例外オブジェクト。
     */
    public BoneheadException(final Throwable e) {
        super(e);
    }
    
    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ。
     * @param e 例外オブジェクト。
     */
    public BoneheadException(final String message, final Throwable e) {
        super(message, e);
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
}

