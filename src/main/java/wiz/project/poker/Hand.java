/**
 * Hand.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.poker;

import java.util.List;



/**
 * ハンド
 */
public final class Hand implements Cloneable {
    
    /**
     * コンストラクタ
     */
    public Hand() {
    }
    
    /**
     * コンストラクタ
     * 
     * @param cardList カードリスト。
     */
    public Hand(final List<Card> cardList) {
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元オブジェクト。
     */
    public Hand(final Hand source) {
    }
    
    
    
    /**
     * オブジェクトを複製 (ディープコピー)
     * 
     * @return 複製結果。
     */
    @Override
    public Hand clone() {
        return new Hand(this);
    }
    
}

