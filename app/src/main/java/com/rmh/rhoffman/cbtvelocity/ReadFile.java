package com.rmh.rhoffman.cbtvelocity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Opens a file hosted on Google Drive and reads the lines of text from it. Each line is a URL for an image, which will be used as the images
 * in each of the cards in the Activities Fragment.
 */
public class ReadFile{

	String path;

	public ReadFile(String filePath){
		path = filePath;
	}

	public String[] openFile(){

		BufferedReader bufferedReader = null;
		int numberOfLines = getNumberOfLines();
		String[] urls = new String[numberOfLines];
		try{
			URL url = new URL(path);
			bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

			for(int i = 0; i < numberOfLines; i++){
				urls[i] = bufferedReader.readLine();
				Log.d("Input Stream: ", urls[i]);
			}
			bufferedReader.close();
		} catch(IOException e){
			e.printStackTrace();
		}

		return urls;
	}

	private int getNumberOfLines(){

		String line;
		int numberOfLines = 0;
		try{
			URL tempURL = new URL(path);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(tempURL.openStream()));

			while((line = buffer.readLine()) != null){
				numberOfLines++;
				Log.d("File Operation: ", line);
			}
			buffer.close();
		} catch(IOException e){
			e.printStackTrace();
		}

		return numberOfLines;
	}

}
