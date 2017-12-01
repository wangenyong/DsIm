package com.dswey.dsim.im;

import com.dswey.dsim.model.LoginResult;
import com.dswey.dsim.model.User;
import com.dswey.dsim.model.UserModel;
import com.orhanobut.logger.Logger;

import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.impl.JidCreate;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author wangenyong
 * @date 2017/12/1
 */

public class SmackManager {
    private static final String TAG = "SmackManager";
    /**
     * Xmpp服务器地址
     */
    public static final String SERVER_IP = "192.168.1.108";
    /**
     * Xmpp 服务器端口
     */
    private static final int PORT = 5222;
    /**
     * 服务器名称
     */
    public static final String SERVER_NAME = "127.0.0.1";
    /**
     *
     */
    public static final String XMPP_CLIENT = "Smack";

    private static volatile SmackManager sSmackManager;
    /**
     * 连接
     */
    private XMPPTCPConnection mConnection;

    private SmackManager() {
        this.mConnection = connect();
    }

    /**
     * 获取操作实例
     *
     * @return
     */
    public static SmackManager getInstance() {

        if (sSmackManager == null) {
            synchronized (SmackManager.class) {
                if (sSmackManager == null) {
                    sSmackManager = new SmackManager();
                }
            }
        }
        return sSmackManager;
    }

    /**
     * 连接服务器
     *
     * @return
     */
    private XMPPTCPConnection connect() {
        try {
            InetAddress addr = InetAddress.getByName(SERVER_IP);
            DomainBareJid serviceName = JidCreate.domainBareFrom(SERVER_NAME);
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    //服务器IP地址
                    .setHostAddress(addr)
                    //是否开启安全模式
                    .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                    //服务器名称
                    .setXmppDomain(serviceName)
                    //服务器端口
                    .setPort(PORT)
                    //是否开启压缩
                    .setCompressionEnabled(false)
                    //开启调试模式
                    .setDebuggerEnabled(true).build();

            XMPPTCPConnection connection = new XMPPTCPConnection(config);
            ReconnectionManager reconnectionManager = ReconnectionManager.getInstanceFor(connection);
            //允许自动重连
            reconnectionManager.enableAutomaticReconnection();
            //重连间隔时间
            reconnectionManager.setFixedDelay(2);
            //连接监听
            connection.addConnectionListener(new SmackConnectionListener());
            connection.connect();
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 是否连接成功
     *
     * @return
     */
    private boolean isConnected() {

        if (mConnection == null) {
            sSmackManager = new SmackManager();
        }
        if(mConnection == null) {
            return false;
        }
        if (!mConnection.isConnected()) {
            try {
                mConnection.connect();
                return true;
            } catch (SmackException | IOException | XMPPException | InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 登陆
     *
     * @param username     用户账号
     * @param password     用户密码
     * @return
     * @throws Exception
     */
    public LoginResult login(String username, String password) {

        try {
            if (!isConnected()) {
                throw new IllegalStateException("服务器断开，请先连接服务器");
            }
            if (!mConnection.isAuthenticated()) {
                mConnection.login(username, password);
            }
            UserModel user = new UserModel(username, password);
            return new LoginResult(user, true);
        } catch (Exception e) {
            Logger.e(TAG, e, "login failure");
            return new LoginResult(false, e.getMessage());
        }
    }

    /**
     * 注销
     *
     * @return
     */
    public boolean logout() {
        if (!isConnected()) {
            return false;
        }
        try {
            mConnection.instantShutdown();
            return true;
        } catch (Exception e) {
            Logger.e(TAG, e, "logout failure");
            return false;
        }
    }

    public XMPPTCPConnection getConnection() {

        if (!isConnected() || mConnection == null) {
            throw new IllegalStateException("服务器断开，请先连接服务器");
        }
        return mConnection;
    }
}
