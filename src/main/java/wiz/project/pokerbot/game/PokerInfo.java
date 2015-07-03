/**
 * PokerInfo.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import wiz.project.poker.Card;
import wiz.project.poker.Hand;
import wiz.project.poker.Position;



/**
 * ポーカーゲームの情報
 */
final class PokerInfo extends Observable implements Cloneable {
    
    /**
     * コンストラクタ
     */
    public PokerInfo() {
        clear();
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public PokerInfo(final PokerInfo source) {
        setPlayerTable(source._playerTable);
        setActivePosition(source._activePosition);
        setDeck(source._deck);
        setDeckIndex(source._deckIndex);
        
        _handTable.clear();
        _handTable.putAll(deepCopyHandTable(source._handTable));
    }
    
    
    
    /**
     * フィールドを全消去
     */
    public void clear() {
        _activePosition = Position.UTG;
        _deck.clear();
        _deckIndex = 0;
        
        for (final Position position : Position.values()) {
            _playerTable.put(position, new Player());
            _handTable.put(position, new Hand());
        }
    }
    
    /**
     * オブジェクトを複製 (ディープコピー)
     * 
     * @return 複製結果。
     */
    @Override
    public PokerInfo clone() {
        return new PokerInfo(this);
    }
    
    /**
     * アクティブプレイヤーを取得
     * 
     * @return アクティブプレイヤー。
     */
    public Player getActivePlayer() {
        return getPlayer(_activePosition);
    }
    
    /**
     * デッキからカードを取得
     * 
     * @return インデックスの指すカード。
     */
    public Card getCardFromDeck() {
        try {
            return _deck.get(_deckIndex);
        }
        finally {
            // 補正が必要なため、通常のインクリメントで処理しない
            setDeckIndex(_deckIndex + 1);
        }
    }
    
    /**
     * デッキを取得
     * 
     * @return デッキ。
     */
    public List<Card> getDeck() {
        return deepCopyList(_deck);
    }
    
    /**
     * ハンドを取得
     * 
     * @param playerName プレイヤー名。
     * @return ハンド。
     */
    public Hand getHand(final String playerName) {
        if (playerName != null) {
            try {
                final Position position = getPosition(playerName);
                return _handTable.get(position).clone();
            }
            catch (final IllegalArgumentException e) {
                // 指定のプレイヤーが存在しなかった場合のエラーを無視
            }
        }
        return new Hand();
    }
    
    /**
     * ハンドを取得
     * 
     * @param position ポジション。
     * @return ハンド。
     */
    public Hand getHand(final Position position) {
        if (position != null) {
            return _handTable.get(position).clone();
        }
        else {
            return new Hand();
        }
    }
    
    /**
     * プレイヤーを取得
     * 
     * @param playerName プレイヤー名。
     * @return プレイヤー。
     */
    public Player getPlayer(final String playerName) {
        if (playerName != null) {
            for (final Player player : _playerTable.values()) {
                if (player.getName().equals(playerName)) {
                    return player;
                }
            }
        }
        return new Player();
    }
    
    /**
     * プレイヤーを取得
     * 
     * @param position ポジション。
     * @return プレイヤー。
     */
    public Player getPlayer(final Position position) {
        if (position != null) {
            return _playerTable.get(position);
        }
        else {
            return new Player();
        }
    }
    
    /**
     * プレイヤーテーブルを取得
     * 
     * @return プレイヤーテーブル。
     */
    public Map<Position, Player> getPlayerTable() {
        return deepCopyPlayerTable(_playerTable);
    }
    
    /**
     * ポジションを取得
     * 
     * @param playerName プレイヤー名。
     * @return ポジション。
     */
    public Position getPosition(final String playerName) {
        if (playerName != null) {
            for (final Map.Entry<Position, Player> entry : _playerTable.entrySet()) {
                if (entry.getValue().getName().equals(playerName)) {
                    return entry.getKey();
                }
            }
        }
        throw new IllegalArgumentException("Invalid player name : " + playerName);
    }
    
    /**
     * ゲームに参加中のプレイヤーか
     * 
     * @param playerName プレイヤー名。
     * @return 判定結果。
     */
    public boolean isValidPlayer(final String playerName) {
        if (playerName == null) {
            return false;
        }
        for (final Player player : _playerTable.values()) {
            if (player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * アクティブプレイヤーを設定
     * 
     * @param playerName プレイヤー名。
     */
    public void setActivePlayer(final String playerName) {
        if (playerName != null) {
            for (final Map.Entry<Position, Player> entry : _playerTable.entrySet()) {
                if (entry.getValue().getName().equals(playerName)) {
                    setActivePosition(entry.getKey());
                }
            }
        }
    }
    
    /**
     * アクティブプレイヤーのポジションを設定
     * 
     * @param position アクティブプレイヤーのポジション。
     */
    public void setActivePosition(final Position position) {
        if (position != null) {
            _activePosition = position;
        }
        else {
            _activePosition = Position.UTG;
        }
    }
    
    /**
     * アクティブプレイヤーのポジションを次に移す
     */
    public void setActivePositionToNext() {
        _activePosition = _activePosition.getNext();
    }
    
    /**
     * デッキを設定
     * 
     * @param deck デッキ。
     */
    public void setDeck(final List<Card> deck) {
        if (deck != null) {
            _deck = deepCopyList(deck);
        }
        else {
            _deck.clear();
        }
    }
    
    /**
     * デッキインデックスを設定
     * 
     * @param index デッキインデックス。
     */
    public void setDeckIndex(final int index) {
        if (index > 0) {
            final int limit = _deck.size() - 1;
            _deckIndex = Math.min(index, limit);
        }
        else {
            _deckIndex = 0;
        }
    }
    
    /**
     * ハンドを設定
     * 
     * @param position ポジション。
     * @param hand ハンド。
     */
    public void setHand(final Position position, final Hand hand) {
        if (position != null) {
            if (hand != null) {
                _handTable.put(position, hand.clone());
            }
            else {
                _handTable.put(position, new Hand());
            }
        }
    }
    
    /**
     * プレイヤーを設定
     * 
     * @param position ポジション。
     * @param player プレイヤー。
     */
    public void setPlayer(final Position position, final Player player) {
        if (position != null) {
            if (player != null) {
                _playerTable.put(position, player);
            }
            else {
                _playerTable.put(position, new Player());
            }
        }
    }
    
    /**
     * プレイヤーテーブルを設定
     * 
     * @param playerTable プレイヤーテーブル。
     */
    public void setPlayerTable(final Map<Position, Player> playerTable) {
        _playerTable.clear();
        if (playerTable != null) {
            _playerTable.putAll(deepCopyPlayerTable(playerTable));
        }
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
     * ハンドテーブルをディープコピー
     * 
     * @param source 複製元。
     * @return 複製結果。
     */
    private Map<Position, Hand> deepCopyHandTable(final Map<Position, Hand> source) {
        final Map<Position, Hand> result = new TreeMap<>();
        for (final Map.Entry<Position, Hand> entry : source.entrySet()) {
            _handTable.put(entry.getKey(), entry.getValue().clone());
        }
        return result;
    }
    
    /**
     * プレイヤーテーブルをディープコピー
     * 
     * @param source 複製元。
     * @return 複製結果。
     */
    private Map<Position, Player> deepCopyPlayerTable(final Map<Position, Player> source) {
        final Map<Position, Player> result = new TreeMap<>();
        for (final Position position : Position.values()) {
            setPlayer(position, source.get(position));
        }
        return result;
    }
    
    
    
    /**
     * プレイヤーテーブル
     */
    private final Map<Position, Player> _playerTable = new TreeMap<>();
    
    /**
     * アクティブなポジション
     */
    private Position _activePosition = Position.UTG;
    
    /**
     * デッキ
     */
    private List<Card> _deck = new ArrayList<>();
    
    /**
     * デッキインデックス
     */
    private int _deckIndex = 0;
    
    /**
     * ハンドテーブル
     */
    private final Map<Position, Hand> _handTable = new TreeMap<>();
    
}

