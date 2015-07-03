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
        
        synchronized (_POKER_INFO_LOCK) {
            _pokerInfo.clear();
        }
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
            throw new IllegalArgumentException("Player name is empty.");
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
        final List<String> list = Arrays.asList(activePlayer + " のターン！", "(色々未実装なので、このトーク画面で", " 「pk c」とだけ発言してください)");
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
     * 参加登録処理
     * 
     * @param playerName プレイヤー名。
     * @throws PokerException ゲーム処理例外。
     */
    public void onEntry(final String playerName) throws PokerException {
        if (playerName == null) {
            throw new NullPointerException("Player name is null.");
        }
        if (playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name is empty.");
        }
        if (_playerNameList.contains(playerName)) {
            throw new IllegalArgumentException("Player is already entry : " + playerName);
        }
        
        _playerNameList.add(playerName);
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
            throw new IllegalArgumentException("Player name is empty.");
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
        if (_playerNameList.isEmpty()) {
            throw new IllegalArgumentException("Player name list is empty.");
        }
        if (_playerNameList.size() == 1) {
            throw new IllegalArgumentException("ぼっち");
        }
        
        synchronized (_STATUS_LOCK) {
            if (!_status.isClose()) {
                throw new InvalidStateException("--- Already started ---");
            }
            _status = GameStatus.IDLE;
        }
        
        synchronized (_POKER_INFO_LOCK) {
            final PokerController controller = createPokerController();
            controller.startGame(_pokerInfo, _playerNameList);
            controller.startRound(_pokerInfo);
        }
        
        
        
        // 以降はダミー処理
        final String activePlayer = _playerNameList.get(_activePlayerIndex++);
        if (_activePlayerIndex >= _playerNameList.size()) {
            _activePlayerIndex = 0;
        }
        final List<String> list = Arrays.asList(activePlayer + " のターン！", "(色々未実装なので、このトーク画面で", " 「pk c」とだけ発言してください)");
        IRCBOT.getInstance().talk(activePlayer, list);
    }
    
    
    
    /**
     * ポーカーコントローラを生成
     * 
     * @return ポーカーコントローラ。
     */
    private PokerController createPokerController() {
        return new VSRingPokerController();
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
     * ロックオブジェクト (ゲームの情報)
     */
    private final Object _POKER_INFO_LOCK = new Object();
    
    
    
    /**
     * ゲームの状態
     */
    private GameStatus _status = GameStatus.CLOSE;
    
    /**
     * 参加プレイヤーリスト
     */
    private final List<String> _playerNameList = Collections.synchronizedList(new ArrayList<String>());
    
    /**
     * ゲームの情報
     */
    private final PokerInfo _pokerInfo = new PokerInfo();
    
    
    
    /**
     * ダミー処理用のインデックス (後で消す)
     */
    private int _activePlayerIndex = 0;
    
}

