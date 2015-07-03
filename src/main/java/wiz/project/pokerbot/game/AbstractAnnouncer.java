/**
 * AbstractAnnouncer.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import wiz.project.poker.Card;
import wiz.project.poker.Hand;




/**
 * ゲーム実況者の抽象クラス
 */
abstract class AbstractAnnouncer implements Announcer {
    
    /**
     * コンストラクタ
     */
    public AbstractAnnouncer() {
    }
    
    
    
    /**
     * カードを文字列に変換
     * 
     * @param card カード。
     * @return 変換結果。
     */
    protected final String convertCardToString(final Card card) {
        if (card == null) {
            throw new NullPointerException("Source card is null.");
        }
        
        final StringBuilder buf = new StringBuilder();
        buf.append(COLOR_FLAG).append(getColorCode(card));
        buf.append(card);
        buf.append(COLOR_FLAG);
        return buf.toString();
    }
    
    /**
     * 手牌を文字列に変換
     * 
     * @param info ゲーム情報。
     * @param player 対象プレイヤー。
     * @param param 実況パラメータ。
     * @return 変換結果。
     */
    protected final String convertHandToString(final PokerInfo info, final Player player, final AnnounceParam param) {
        if (info == null) {
            throw new NullPointerException("Source info is null.");
        }
        if (player == null) {
            throw new NullPointerException("Source player is null.");
        }
        if (param == null) {
            throw new NullPointerException("Source parameter is null.");
        }
        
        final Hand hand = info.getHand(player.getName());
        final StringBuilder buf = new StringBuilder();
        for (final Card pai : hand.getCardList()) {
            buf.append(convertCardToString(pai));
        }
        return buf.toString();
    }
    
    
    
    /**
     * 色コードを取得
     * 
     * @param card カード。
     * @return 対応する色コード。
     */
    private String getColorCode(final Card card) {
        switch (card.getSuit()) {
        case SPADE:
            return "12";  // 青
        case HEART:
            return "04";  // 赤
        case DIAMOND:
            return "13";  // マゼンタ
        case CLUB:
            return "03";  // 緑
        default:
            // ジョーカー
            return "01";  // 黒
        }
    }
    
    
    
    /**
     * 色付けフラグ
     */
    private static final char COLOR_FLAG = 3;
    
}

