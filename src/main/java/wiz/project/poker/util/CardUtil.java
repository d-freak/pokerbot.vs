/**
 * CardUtil.java
 * 
 * @author Yuki
 */

package wiz.project.poker.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import wiz.project.poker.Card;



/**
 * カードユーティリティ
 */
public final class CardUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private CardUtil() {}
    
    
    
    /**
     * 全カードリストを生成
     * 
     * @return 全カードリスト。
     */
    public static List<Card> createAllCardList() {
        return new ArrayList<>(Arrays.asList(Card.values()));
    }
    
    /**
     * シャッフル済みの全カードリストを生成
     * 
     * @return シャッフル済みの全カードリスト。
     */
    public static List<Card> createShuffledCardList() {
        final List<Card> deck = createAllCardList();
        Collections.shuffle(deck, new SecureRandom());
        return deck;
    }
    
}

