package model.data;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

import commons.level;

public interface levelSaver {
	/**
	 * Saving level
	 * 
	 * @param level The level you want to save
	 * @param out - output stream
	 * @throws IOException
	 */
	public void seveLevel (level level, OutputStream out)throws IOException;

}
