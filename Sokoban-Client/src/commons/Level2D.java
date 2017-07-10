package commons;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Clock;
import java.util.ArrayList;
import javax.swing.SingleSelectionModel;
import commons.Target;
import model.data.TypeFactory;

/**
 * Defines 2D level extends LevelA
 * 
 *
 */
public class Level2D extends LevelA {
	private background[][] backG;
	private moveAble[][] moveables;
	private int sizeRow;
	private int sizeCol;	
	
	//De'f C'tor
	public Level2D() {
		super();
		this.backG = null;
		this.moveables=null;
		this.sizeRow=-1;
		this.sizeCol=-1;
	}
	//C'tor
	public Level2D(LevelA level,int sizeRow,int sizeCol,background[][] backG,moveAble[][] moveables) {
		super(level);
		this.setSizeRow(sizeRow);
		this.setSizeCol(sizeCol);
		this.backG = backG;
		this.moveables = moveables;
	}
	
	//copy c'tor
	public Level2D(Level2D level)
	{
		super(level);
		this.sizeRow= level.getSizeRow();
		this.sizeCol=level.getSizeCol();
		this.backG=level.getBackG();
		this.moveables=level.getMoveables();
	}
	
	/**
	 * Receives a board and creates a level with the data of the board
	 * @param board
	 */
	public Level2D(char[][] board){
		char[] line;
		int sizeR=0;
		int sizeC=0;
		//TwoPoint tp = new TwoPoint();
		ArrayList<char[]> arrLine = new ArrayList<>();
		
			TypeFactory typeF = new TypeFactory();
			for (int i=0; i<board.length; i++)
			{
				line=board[i];
				arrLine.add(line);
				sizeR++;
				if(line.length>sizeC)
					sizeC=line.length;
			}
			creat2Matrix(sizeR,sizeC);
			
			for(int i=0;i<arrLine.size();i++)
			{
				for(int j=0;j<arrLine.get(i).length;j++){
					TwoPoint tp = new TwoPoint();
					tp.setX(i);
					tp.setY(j);
					type t= typeF.creatType(arrLine.get(i)[j],tp);
					if(t==null){
						this.getMoveables()[i][j] = null;
						this.getBackG()[i][j] = new Path(tp);
					}
					else if(t instanceof background){
						this.getBackG()[i][j] = (background)t;
						this.getMoveables()[i][j] = null;
						if(Target.class.isAssignableFrom(this.getBackG()[i][j].getClass())){
							this.geTargets().add((Target)this.getBackG()[i][j]);
						}
					}
					else if(t instanceof moveAble){
						this.getMoveables()[i][j] =  (moveAble)t;
						this.getBackG()[i][j] = new Path(tp);
						
						if(Box.class.isAssignableFrom(this.getMoveables()[i][j].getClass())){
							this.getBoxs().add((Box)this.getMoveables()[i][j]);
						}
						else if(Player.class.isAssignableFrom(this.getMoveables()[i][j].getClass())){
							this.setPlayer((Player)this.getMoveables()[i][j]);
						}
						//check if player on target(a)/box on target($)
						if (arrLine.get(i)[j]=='a'||(arrLine.get(i)[j]=='$')){
							this.getBackG()[i][j] = new Target(tp);
							this.geTargets().add((Target)this.getBackG()[i][j]);
						}
					}
				}
			}
		
	}
		
	//gets && sets
	public int getSizeRow() {
		return sizeRow;
	}
	public void setSizeRow(int sizeRow) {
		this.sizeRow = sizeRow;
	}
	public int getSizeCol() {
		return sizeCol;
	}
	public void setSizeCol(int sizeCol) {
		this.sizeCol = sizeCol;
	}
	public background[][] getBackG() {
		return backG;
	}
	public void setBackG(background[][] backG) {
		this.backG = backG;
	}
	public moveAble[][] getMoveables() {
		return moveables;
	}
	public void setMoveables(moveAble[][] moveables) {
		this.moveables = moveables;
	}
	
	@Override
	public int getCountBoxOnTargets() {
		int count=0;
		for (Box box : this.getBoxs()) {
			for (Target target : this.geTargets()) {
				if(target.getPos().equalsPos(box.getPos()))
						count++;
			}
		}
		return count;
	}
	
	@Override
	public int getCountBoxNotOnTargets() {
		return this.getCountBoxs()-this.getCountBoxOnTargets(); 
	}

	public position getPosCharacter() {
		return this.player.getPos();
	}
	
	@Override
	public String toString() {
		String str="";
		for (int i=0;i<this.sizeRow;i++){
			for(int j=0;j<this.sizeCol;j++){
				if(this.moveables[i][j] ==null)
					str+=this.backG[i][j].toString();
				else
				{
					if (Target.class.isAssignableFrom(this.backG[i][j].getClass()))
					{
						if (Box.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							str+='$';
						else if (Player.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							str+='a';
					}
					else
						str+=this.moveables[i][j].toString();
				}
			}
			str+=System.lineSeparator();
		}
		return str;
	}
	
	/**
	 * Returns the board of the game
	 * @return the board of the game
	 */
	public char[][] getBoared(){

		char[][] boared= new char[sizeRow][sizeCol];

		for (int i=0;i<this.sizeRow;i++){
			for(int j=0;j<this.sizeCol;j++){
				if(this.moveables[i][j] ==null)
					boared[i][j]=this.backG[i][j].getC();
				else
				{
					if (Target.class.isAssignableFrom(this.backG[i][j].getClass()))
					{
						if (Box.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							boared[i][j]='$';
						else if (Player.class.isAssignableFrom(this.getMoveables()[i][j].getClass()))
							boared[i][j]='a';
					}
					else
						boared[i][j]=this.moveables[i][j].getC();
				}
			}
		}

		return boared;
		
	}
	
	/**
	 * Creates two boards for the game, one for characters that can move and one for characters that can not move like a wall
	 * @param sizeR Size of rows
	 * @param sizeC Column size
	 */
	private void creat2Matrix(int sizeR,int sizeC) {
		this.setSizeCol(sizeC);
		this.setSizeRow(sizeR);
		background backG [][] = new background[sizeR][sizeC];
		this.setBackG(backG);
		moveAble moveables [][] = new moveAble[sizeR][sizeC];
		this.setMoveables(moveables);
		
	}
}


