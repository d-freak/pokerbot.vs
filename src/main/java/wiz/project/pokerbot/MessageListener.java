/**
 * MessageListener.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.pokerbot;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import wiz.project.ircbot.IRCBOT;
import wiz.project.pokerbot.game.GameMaster;
import wiz.project.pokerbot.game.exception.BoneheadException;
import wiz.project.pokerbot.game.exception.PokerException;



/**
 * メッセージ受付
 * 
 * @param <T> PircBoxT、またはその継承クラス。
 */
final class MessageListener<T extends PircBotX> extends ListenerAdapter<T> {
    
    /**
     * コンストラクタ
     */
    public MessageListener() {
    }
    
    
    
    /**
     * メッセージ受信時の処理
     * 
     * @param event イベント情報。
     * @throws InterruptedException 処理に失敗。
     */
    @Override
    public void onMessage(final MessageEvent<T> event) throws Exception {
        if (event == null) {
            throw new NullPointerException("Event information is null.");
        }
        
        // メッセージ解析
        try {
            final String message = event.getMessage().replaceAll(" +", " ");  // 連続する半角スペースを削除
            if (!message.startsWith(COMMAND_PREFIX)) {
                // 「pk hoge」以外全て無視
                return;
            }
            
            final String playerName = event.getUser().getNick();
            final List<String> commandList = Arrays.asList(message.substring(COMMAND_PREFIX.length()).split(" "));  // 半角スペースで分解
            onCommandOpen(playerName, commandList);
        }
        catch (final BoneheadException e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ 反則ですぞ");
        }
        catch (final PokerException e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ " + e.getMessage());
        }
        catch (final Throwable e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * トーク受信時の処理
     * 
     * @param event イベント情報。
     * @throws Exception 処理に失敗。
     */
    @Override
    public void onPrivateMessage(final PrivateMessageEvent<T> event) throws Exception {
        if (event == null) {
            throw new NullPointerException("Event information is null.");
        }
        
        // メッセージ解析
        try {
            final String message = event.getMessage().replaceAll(" +", " ");  // 連続する半角スペースを削除
            if (!message.startsWith(COMMAND_PREFIX)) {
                // 「pk hoge」以外全て無視
                return;
            }
            
            final String playerName = event.getUser().getNick();
            final List<String> commandList = Arrays.asList(message.substring(COMMAND_PREFIX.length()).split(" "));  // 半角スペースで分解
            onCommandTalk(playerName, commandList);
        }
        catch (final BoneheadException e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ 反則ですぞ");
        }
        catch (final PokerException e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ " + e.getMessage());
        }
        catch (final Throwable e) {
            IRCBOT.getInstance().println("(  ´∀｀) ＜ " + e.getMessage());
            throw e;
        }
    }
    
    
    
    /**
     * コマンド処理 (オープン)
     * 
     * @param playerName 発言者のニックネーム。
     * @param commandList コマンドリスト。先頭要素はコマンド種類、以降はパラメータ。
     * @throws PokerException 麻雀ゲーム中の例外。
     */
    private void onCommandOpen(final String playerName, final List<String> commandList) throws PokerException {
        final String command = commandList.get(0);
        switch (command) {
        case "--close":
            if (commandList.size() == 1) {
                IRCBOT.getInstance().println("(  ；∀；)");
                IRCBOT.getInstance().disconnect();
            }
            break;
        case "s":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onStart();
            }
            break;
        case "e":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onEnd();
            }
            break;
        case "entry":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onEntry(playerName);
            }
            break;
        case "h":
        case "help":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onHelpOpen();
            }
            break;
        default:
            // 不明なコマンドは全て無視
            break;
        }
    }
    
    /**
     * コマンド処理 (トーク)
     * 
     * @param playerName 発言者のニックネーム。
     * @param commandList コマンドリスト。先頭要素はコマンド種類、以降はパラメータ。
     * @throws PokerException 麻雀ゲーム中の例外。
     */
    private void onCommandTalk(final String playerName, final List<String> commandList) throws PokerException {
        final String command = commandList.get(0);
        switch (command) {
        case "c":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onCheck(playerName);
            }
            break;
        case "h":
        case "help":
            if (commandList.size() == 1) {
                GameMaster.getInstance().onHelpTalk(playerName);
            }
            break;
        default:
            // 不明なコマンドは全て無視
            break;
        }
    }
    
    
    
    /**
     * BOTコマンドのプレフィックス
     */
    private static final String COMMAND_PREFIX = "pk ";
    
}

