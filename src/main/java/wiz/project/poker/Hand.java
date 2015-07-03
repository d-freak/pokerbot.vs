/**
 * Hand.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.poker;

import java.util.ArrayList;
import java.util.Collections;
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
        _cardList = deepCopyList(cardList);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元オブジェクト。
     */
    public Hand(final Hand source) {
        _cardList = deepCopyList(source._cardList);
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
    
    
    
    /**
     * カードリストを取得
     * 
     * @return カードリスト。
     */
    public List<Card> getCardList() {
        return deepCopyList(_cardList);
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
        return Collections.synchronizedList(new ArrayList<E>(sourceList));
    }
    
    
    
    /**
     * カードリスト
     */
    private List<Card> _cardList = new ArrayList<>();
    
}

