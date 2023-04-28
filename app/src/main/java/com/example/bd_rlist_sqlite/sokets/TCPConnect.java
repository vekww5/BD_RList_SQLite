package com.example.bd_rlist_sqlite.sokets;

public class TCPConnect implements TCPConnectionListener {
    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        // Обработка успешного установления соединения
    }

    @Override
    public void onDisconnect(TCPConnection TCPConnection) {
        // Обработка разрыва соединения
    }

    @Override
    public void onMessageReceived(TCPConnection tcpConnect, String str) {
        // Обработка получения нового сообщения
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception ex) {
        // Обработка ошибки
    }
}
