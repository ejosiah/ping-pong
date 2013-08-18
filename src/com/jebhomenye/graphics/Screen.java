package com.jebhomenye.graphics;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice device;
	
	public Screen(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = env.getDefaultScreenDevice();
	}
	
	public DisplayMode firstCompatibleMode(DisplayMode modes[]){
		DisplayMode[] goodModes = device.getDisplayModes();
		
		for(int i = 0; i < modes.length; i++){
			for(int j = 0; j < goodModes.length; j++){
				if(matches(modes[i], goodModes[j])){
					return goodModes[j];
				}
			}
		}
		return null;
	}
	
	private boolean matches(DisplayMode displayMode1, DisplayMode displayMode2) {
		if(displayMode1 == null || displayMode2 == null){
			return false;
		}
		if(displayMode1.getWidth() == displayMode2.getWidth()
			&& displayMode1.getHeight() == displayMode2.getHeight()
			&& displayMode1.getBitDepth() == displayMode1.getBitDepth()
			){
			return true;
		}
		return false;
	}

	public void fullScreenWindow(DisplayMode mode){
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);
		
		device.setFullScreenWindow(frame);
		
		if(mode != null && device.isDisplayChangeSupported()){
			try{
				device.setDisplayMode(mode);
			}catch(IllegalArgumentException e){
				
			}
		}
		device.getFullScreenWindow().createBufferStrategy(2);
	}
	
	public int width(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			return window.getWidth();
		}
		return 0;
	}
	
	public int height(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			return window.getHeight();
		}
		return 0;
	}
	
	public void update(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			BufferStrategy strategy = window.getBufferStrategy();
			if(!strategy.contentsLost()){
				strategy.show();
			}
		}
	}
	
	public Graphics2D graphics(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			return (Graphics2D)
					window.getBufferStrategy().getDrawGraphics();
		}
		return null;
	}
	
	public void restoreScreen(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			window.dispose();
		}
		device.setFullScreenWindow(null);
	}
	
	public void background(Color color){
		Window window = device.getFullScreenWindow();
		if(window != null){
			window.setBackground(color);
		}
	}
	
	public void foreground(Color color){
		Window window = device.getFullScreenWindow();
		if(window != null){
			window.setForeground(color);
		}
	}
	
	public void font(Font font){
		Window window = device.getFullScreenWindow();
		if(window != null){
			window.setFont(font);
		}
	}
	
	public Color foreground(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			return window.getForeground();
		}
		return null;
	}
	
	public Color background(){
		Window window = device.getFullScreenWindow();
		if(window != null){
			return window.getBackground();
		}
		return null;
	}
	
	public void addKeyListener(KeyListener listener){
		Window window = device.getFullScreenWindow();
		if(window != null){
			window.addKeyListener(listener);
		}
	}

	
}
