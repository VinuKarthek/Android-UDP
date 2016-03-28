package com.benito.udpterminal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TerminalUDPActivity extends Activity {
	
	private int sendPacket(int lPorta,String ipHost, int port, String payload) throws IOException {
		
		InetAddress address = InetAddress.getByName(ipHost); //Get IP Address
				
		DatagramSocket socket = new DatagramSocket(lPorta); //Initialize UDP Packet
		
		DatagramPacket packet = new DatagramPacket(payload.getBytes(),payload.length(),address,port); //Initialize Datagram Packet
		
		socket.send(packet);
		socket.disconnect();
		socket.close();
		
		return 0;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText Localport = (EditText) findViewById(R.id.editTextLocalPort);
        final EditText IP = (EditText) findViewById(R.id.editTextIp);
        final EditText Port = (EditText) findViewById(R.id.editTextPort);
        final EditText Payload = (EditText) findViewById(R.id.editTextPayload);
        final Button btSend = (Button) findViewById(R.id.buttonSend);
        
        btSend.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		String texto = IP.getText()+":"+Port.getText()+
        		               " - Payload: "+Payload.getText();
        		Toast.makeText(TerminalUDPActivity.this, "Sending:\n"+texto ,Toast.LENGTH_LONG).show();
        		
        		int port = Integer.parseInt(Port.getText().toString());
        		int localport = Integer.parseInt(Localport.getText().toString());
        		try {
					sendPacket(localport,IP.getText().toString(), port, Payload.getText().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
    }
}