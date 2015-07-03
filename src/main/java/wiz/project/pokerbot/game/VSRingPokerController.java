/**
 * VSRingPokerController.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.poker.Card;
import wiz.project.poker.Hand;
import wiz.project.poker.Position;
import wiz.project.poker.util.CardUtil;
import wiz.project.pokerbot.game.exception.PokerException;



/**
 * リングゲーム対戦用ポーカーゲーム処理
 */
class VSRingPokerController implements PokerController {
    
    /**
     * コンストラクタ
     */
    public VSRingPokerController() {
    }
    
    
    
    /**
     * 次のプレイヤーのベットへ
     * 
     * @param info ゲーム情報。
     */
    public void next(final PokerInfo info) {
        if (info == null) {
            throw new NullPointerException("Poker info is null.");
        }
        
        // TODO 次のフェイズに進んでいいかどうか(全員のベット額が揃ったか)の判定
        // Utilで判定ルーチンを作った方がいい
        
        info.setActivePositionToNext();
        onPhase(info);
    }
    
    /**
     * 次のラウンドの準備
     */
    public void readyToNextRound(final PokerInfo info) throws PokerException {
        // TODO プレイヤーテーブルのポジションをずらす
        // TODO 途中参加プレイヤーをテーブルに含める
    }
    
    /**
     * 開始
     */
    public void startGame(final PokerInfo info, final List<String> playerNameList) throws PokerException {
        if (info == null) {
            throw new NullPointerException("Poker info is null.");
        }
        if (playerNameList == null) {
            throw new NullPointerException("Player name list is null.");
        }
        if (playerNameList.isEmpty()) {
            throw new IllegalArgumentException("Player name list is empty.");
        }
        
        // 現状席決めのみ
        
        // ポジションをシャッフル
        final List<Position> positionList = new ArrayList<>(Arrays.asList(Position.values()));
        Collections.shuffle(positionList, new SecureRandom());
        
        // プレイヤーを格納 (空席はPokerInfoが自動で埋めるので放置)
        final Map<Position, Player> playerTable = new TreeMap<>();
        for (final String playerName : playerNameList) {
            playerTable.put(positionList.remove(0), new Player(playerName, PlayerType.HUMAN));
        }
        
        info.setPlayerTable(playerTable);
    }
    
    /**
     * ラウンド開始
     */
    public void startRound(final PokerInfo info) throws PokerException {
        if (info == null) {
            throw new NullPointerException("Poker info is null.");
        }
        
        // デッキシャッフル
        final List<Card> deck = CardUtil.createShuffledCardList();
        info.setDeck(deck);
        
        // ハンドを配る
        for (final Position position : Position.values()) {
            // 空席にも配って自動フォールドさせる
            final Hand hand = new Hand(Arrays.asList(info.getCardFromDeck(), info.getCardFromDeck()));
            info.setHand(position, hand);
        }
        
        // TODO アナウンサー
        
        // TODO ブラインド徴収
        
        // TODO HUの際の特殊挙動対応
        
        info.setActivePosition(Position.UTG);
        onPhase(info);
    }
    
    
    
    /**
     * フェイズごとの処理
     * 
     * @param info ポーカー情報。
     */
    private void onPhase(final PokerInfo info) {
        final Player activePlayer = info.getActivePlayer();
        switch (activePlayer.getType()) {
        case HUMAN:
            info.notifyObservers(new AnnounceParam(activePlayer, AnnounceFlag.HAND_TALK));
            break;
        default:
            // 空席は無視
            next(info);
            break;
        }
    }
    
}

