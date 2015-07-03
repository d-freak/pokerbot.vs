/**
 * TalkAnnouncer.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import wiz.project.ircbot.IRCBOT;



/**
 * 個別実況
 */
public class TalkAnnouncer extends AbstractAnnouncer {
    
    /**
     * コンストラクタ
     */
    public TalkAnnouncer() {
    }
    
    /**
     * 状況更新時の処理
     * 
     * @param target 監視対象。
     * @param p 更新パラメータ。
     */
    public void update(final Observable target, final Object p) {
        if (!(target instanceof PokerInfo)) {
            return;
        }
        if (!(p instanceof AnnounceParam)) {
            return;
        }
        
        final PokerInfo info = (PokerInfo)target;
        final AnnounceParam param = (AnnounceParam)p;
        final Player player = param.getPlayer();
        
        final List<String> messageList = new ArrayList<>();
        if (param.hasFlag(AnnounceFlag.HAND_TALK)) {
            messageList.add(convertHandToString(info, player, param));
            messageList.add("※まだ何もできません。「pk c」で次のプレイヤーに移ります。");
        }
        
        IRCBOT.getInstance().talk(player.getName(), messageList);
    }
    
}

