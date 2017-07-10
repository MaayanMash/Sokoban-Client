package model.data;

import java.io.IOException;
import java.io.InputStream;

import commons.Level2D;

public interface levelLoader {
	/**
	 * Loading level
	 * 
	 * @param in - input stream
	 * @return level
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Level2D loadLevel(InputStream in)throws IOException, ClassNotFoundException;
}
