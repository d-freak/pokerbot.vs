/**
 * PokerController.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import java.util.List;

import wiz.project.pokerbot.game.exception.PokerException;



/**
 * ポーカーゲーム処理
 */
interface PokerController {
    
    /**
     * 次のプレイヤーのベットへ
     * 
     * @param info ゲーム情報。
     */
    public void next(final PokerInfo info);
    
    /**
     * 次のラウンドの準備
     * 
     * @param info ポーカー情報。
     * @throws PokerException 例外イベント。
     */
    public void readyToNextRound(final PokerInfo info) throws PokerException;
    
    /**
     * ゲーム開始
     * 
     * @param info ポーカー情報。
     * @param playerNameList プレイヤー名リスト。
     * @throws PokerException 例外イベント。
     */
    public void startGame(final PokerInfo info, final List<String> playerNameList) throws PokerException;
    
    /**
     * ラウンド開始
     * 
     * @param info ポーカー情報。
     * @throws PokerException 例外イベント。
     */
    public void startRound(final PokerInfo info) throws PokerException;
    
}

