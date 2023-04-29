package com.example.bd_rlist_sqlite.sokets;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyTCPClient extends AsyncTask<String, Void, Void> {
    private static final String TAG = "MyTCPClient";
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    protected Void doInBackground(String... params) {
        connect(params[0], params[1]); // Connect to server
        send(params[2]); // Send message to server
        receive(); // Receive response from server
        disconnect(); // Disconnect from server
        return null;
    }

    private void connect(String SERVER_IP, String SERVER_PORT) {
        try {
            Log.d(TAG, "Connecting to server...");
            socket = new Socket(SERVER_IP, Integer.parseInt(SERVER_PORT));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            Log.d(TAG, "Connected to server");
        } catch (IOException e) {
            Log.e(TAG, "Error connecting to server: " + e.getMessage());
        }
    }

    private void send(String message) {
        try {
            Log.d(TAG,  message);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(message);
            writer.flush();
            Log.d(TAG, "Message sent");
        } catch (IOException e) {
            Log.e(TAG, "Error sending message: " + e.getMessage());
        }
    }

    private void receive() {
        try {
            Log.d(TAG, "Receiving response...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String response = reader.readLine();
            Log.d(TAG, "Response received: " + response);
        } catch (IOException e) {
            Log.e(TAG, "Error receiving response: " + e.getMessage());
        }
    }

    private void disconnect() {
        try {
            Log.d(TAG, "Disconnecting from server...");
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            Log.e(TAG, "Error disconnecting from server: " + e.getMessage());
        }
    }
}
