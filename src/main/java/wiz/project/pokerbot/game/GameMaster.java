/**
 * GameMaster.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import wiz.project.ircbot.IRCBOT;
import wiz.project.pokerbot.game.exception.InvalidInputException;
import wiz.project.pokerbot.game.exception.InvalidStateException;
import wiz.project.pokerbot.game.exception.PokerException;



/**
 * ゲーム管理
 */
public final class GameMaster {
    
    /**
     * コンストラクタを自分自身に限定許可
     */
    private GameMaster() {
    }
    
    
    
    /**
     * インスタンスを取得
     * 
     * @return インスタンス。
     */
    public static GameMaster getInstance() {
        return INSTANCE;
    }
    
    
    
    /**
     * 内部状態を初期化
     */
    public void clear() {
        _playerNameList.clear();
        
        synchronized (_STATUS_LOCK) {
            _status = GameStatus.CLOSE;
        }
    }
    
    /**
     * ゲームの状態を取得
     * 
     * @return ゲームの状態。
     */
    public GameStatus getStatus() {
        synchronized (_STATUS_LOCK) {
            return _status;
        }
    }
    
    /**
     * チェック処理
     * 
     * @param playerName プレイヤー名。
     * @throws PokerException ゲーム処理例外。
     */
    public void onCheck(final String playerName) throws PokerException {
        if (playerName == null) {
            throw new NullPointerException("Player name is null.");
        }
        if (playerName.isEmpty()) {
            throw new NullPointerException("Player name is empty.");
        }
        if (!_playerNameList.contains(playerName)) {
            throw new IllegalArgumentException("Player is not entry : " + playerName);
        }
        
        synchronized (_STATUS_LOCK) {
            if (!_status.isIdle()) {
                throw new InvalidStateException("--- Already started ---");
            }
        }
        
        // TODO チェック
        // コントローラでやる予定
        
        
        
        // 以降はダミー処理
        final String activePlayer = _playerNameList.get(_activePlayerIndex++);
        if (_activePlayerIndex >= _playerNameList.size()) {
            _activePlayerIndex = 0;
        }
        final List<String> list = Arrays.asList(activePlayer + " のターン！", "(色々未実装なので、このトーク画面で", " 「jan d」とだけ発言してください)");
        IRCBOT.getInstance().talk(activePlayer, list);
    }
    
    /**
     * ゲーム終了処理
     */
    public void onEnd() {
        synchronized (_STATUS_LOCK) {
            if (_status.isClose()) {
                return;
            }
        }
        
        // TODO 確認後に消したい
        clear();
        IRCBOT.getInstance().println("--- 終了 ---");
    }
    
    /**
     * 参加プレイヤー登録処理
     * 
     * @param playerNameList プレイヤー名のリスト。
     * @throws PokerException ゲーム処理例外。
     */
    public void onEntry(final List<String> playerNameList) throws PokerException {
        if (playerNameList == null) {
            throw new NullPointerException("Player name list is null.");
        }
        if (playerNameList.isEmpty()) {
            throw new NullPointerException("Player name list is empty.");
        }
        
        synchronized (_STATUS_LOCK) {
            if (_status.isClose()) {
                throw new InvalidStateException("--- Not started ---");
            }
            if (!_status.isEntryable()) {
                throw new InvalidStateException("--- Already started ---");
            }
        }
        
        for (final String playerName : playerNameList) {
            if (!IRCBOT.getInstance().exists(playerName)) {
                // 存在しないプレイヤーが指定された
                throw new InvalidInputException("Player is not found : " + playerName);
            }
        }
        _playerNameList.addAll(playerNameList);
        
        // TODO 席決め
        // コントローラでやる予定
        
        synchronized (_STATUS_LOCK) {
            _status = GameStatus.IDLE;
        }
        
        // TODO 第一ゲーム
        // コントローラでやる予定
        
        
        
        // 以降はダミー処理
        final String activePlayer = _playerNameList.get(_activePlayerIndex++);
        if (_activePlayerIndex >= _playerNameList.size()) {
            _activePlayerIndex = 0;
        }
        final List<String> list = Arrays.asList(activePlayer + " のターン！", "(色々未実装なので、このトーク画面で", " 「jan d」とだけ発言してください)");
        IRCBOT.getInstance().talk(activePlayer, list);
    }
    
    /**
     * ヘルプ表示処理 (オープン)
     */
    public void onHelpOpen() {
        // TODO 内部状態によって表示内容を変えたい
        final List<String> messageList = Arrays.asList("s：開始   e：強制終了");
        IRCBOT.getInstance().println(messageList);
    }
    
    /**
     * ヘルプ表示処理 (トーク)
     * 
     * @param playerName プレイヤー名。
     */
    public void onHelpTalk(final String playerName) {
        if (playerName == null) {
            throw new NullPointerException("Player name is null.");
        }
        if (playerName.isEmpty()) {
            throw new NullPointerException("Player name is empty.");
        }
        if (!_playerNameList.contains(playerName)) {
            throw new IllegalArgumentException("Player is not entry : " + playerName);
        }
        
        // TODO 内部状態によって表示内容を変えたい
        final List<String> messageList = Arrays.asList("ダミー実装につき何もできません");
        IRCBOT.getInstance().talk(playerName, messageList);
    }
    
    /**
     * ゲーム開始処理
     * 
     * @throws PokerException ゲーム処理例外。
     */
    public void onStart() throws PokerException {
        synchronized (_STATUS_LOCK) {
            if (!_status.isClose()) {
                throw new InvalidStateException("--- Already started ---");
            }
            _status = GameStatus.PLAYER_ENTRY;
        }
        
        IRCBOT.getInstance().println("--- 参加プレイヤーを登録してください ---");
        IRCBOT.getInstance().println("----- IRCで現在使用しているニックネームで登録すること");
        IRCBOT.getInstance().println("----- 区切り文字には半角スペースを使用すること");
        IRCBOT.getInstance().println("ex.) jan entry Mr.A Mr.B Mr.C");
    }
    
    
    
    /**
     * 自分自身のインスタンス
     */
    private static final GameMaster INSTANCE = new GameMaster();
    
    
    
    /**
     * ロックオブジェクト (ゲームの状態)
     */
    private final Object _STATUS_LOCK = new Object();
    
    
    
    /**
     * ゲームの状態
     */
    private GameStatus _status = GameStatus.CLOSE;
    
    /**
     * 参加プレイヤーリスト
     */
    private final List<String> _playerNameList = Collections.synchronizedList(new ArrayList<String>());
    
    
    
    /**
     * ダミー処理用のインデックス (後で消す)
     */
    private int _activePlayerIndex = 0;
    
}

