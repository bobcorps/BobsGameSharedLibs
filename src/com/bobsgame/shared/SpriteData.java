package com.bobsgame.shared;

import java.io.IOException;
import java.util.ArrayList;

import com.bobsgame.shared.AssetData;
import com.bobsgame.shared.SpriteAnimationSequence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//===============================================================================================
public class SpriteData extends AssetData
{//===============================================================================================

	//---------------------------------------------------------
	//---------------------------------------------------------
	//---------------------------------------------------------
	//
	//
	//
	//	WARNING! EDITING THESE NAMES WILL BREAK JSON DECODING
	//
	//
	//
	//
	//---------------------------------------------------------
	//---------------------------------------------------------
	//---------------------------------------------------------

	private String comment = "";

	private String displayName = "";

	private int widthPixels1X = 0;
	private int heightPixels1X = 0;

	private int frames = 1;

	private boolean isNPC = false; //not used for anything yet

	private boolean isKid = false;
	private boolean isAdult = false;
	private boolean isMale = false;
	private boolean isFemale = false;
	private boolean isCar = false;
	private boolean isAnimal = false;



	private boolean hasShadow = false;
	private boolean isRandom = false;




	//TODO: handle these
	private boolean isDoor = false;
	private boolean isGame = false;
	private boolean isItem = false;

	private boolean forceHQ2X = false;
	private boolean forceMD5Export = false;

	private int eventID = -1;
	private String itemGameDescription = "";
	private float gamePrice = 0.0f;




	private int utilityOffsetXPixels1X = 0;//used for spawn point, doorknob marking, hands?
	private int utilityOffsetYPixels1X = 0;



	private String dataMD5 = null;
	private String paletteMD5 = null;





	private ArrayList<SpriteAnimationSequence> animationList = new ArrayList<SpriteAnimationSequence>();



	//=========================================================================================================================
	public SpriteData()
	{//=========================================================================================================================

	}

	//=========================================================================================================================
	public SpriteData(int id, String name, String displayName, int widthPixels1X, int heightPixels1X, int frames, boolean isNPC, boolean isKid, boolean isAdult, boolean isMale, boolean isFemale, boolean isCar, boolean isAnimal, boolean hasShadow, boolean isRandom, boolean isDoor, boolean isGame, boolean isItem, boolean forceHQ2X, boolean forceClientMD5Export, int eventID, String itemGameDescription, float gamePrice, int utilityOffsetXPixels1X, int utilityOffsetYPixels1X, String dataMD5, String paletteMD5)
	{//=========================================================================================================================


		super(id,name);

		this.displayName = displayName;

		this.widthPixels1X = widthPixels1X;
		this.heightPixels1X = heightPixels1X;


		this.frames = frames;

		this.isNPC = isNPC;

		this.isKid = isKid;
		this.isAdult = isAdult;
		this.isMale = isMale;
		this.isFemale = isFemale;
		this.isCar = isCar;
		this.isAnimal = isAnimal;

		this.hasShadow = hasShadow;
		this.isRandom = isRandom;

		this.isDoor = isDoor;
		this.isGame = isGame;
		this.isItem = isItem;

		this.forceHQ2X = forceHQ2X;
		this.forceMD5Export = forceClientMD5Export;

		this.eventID = eventID;
		this.itemGameDescription = itemGameDescription;
		this.gamePrice = gamePrice;

		this.utilityOffsetXPixels1X = utilityOffsetXPixels1X;
		this.utilityOffsetYPixels1X = utilityOffsetYPixels1X;

		this.dataMD5 = dataMD5;
		this.paletteMD5 = paletteMD5;

	}


	//=========================================================================================================================
	public SpriteData(int id, String name, int width, int height, int frames)
	{//=========================================================================================================================

		this(id,name,"",width,height,frames,false,false,false,false,false,false,false,false,false,false,false,false,false,false,-1,"",0.0f,0,0,"","");

	}


	//=========================================================================================================================
	public void addAnimation(String frameSequenceName, int frameStart, int hitBoxOffsetLeft1X, int hitBoxOffsetRight1X, int hitBoxOffsetTop1X, int hitBoxOffsetBottom1X)
	{//=========================================================================================================================
		animationList.add(new SpriteAnimationSequence(frameSequenceName, frameStart, hitBoxOffsetLeft1X, hitBoxOffsetRight1X, hitBoxOffsetTop1X, hitBoxOffsetBottom1X));
	}




	//===============================================================================================
	public static SpriteData fromBase64ZippedJSON(String b64)
	{//===============================================================================================





		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);

		//Gson gson = new Gson();
		//SpriteData data = gson.fromJson(json,SpriteData.class);

		return fromJSON(json);


	}

	//===============================================================================================
	public static SpriteData fromJSON(String json)
	{//===============================================================================================

		Gson gson = new Gson();
		SpriteData data = gson.fromJson(json,SpriteData.class);

//		ObjectMapper mapper = new ObjectMapper();
//		SpriteData data = null;
//
//		try
//		{
//			data = mapper.readValue(json, SpriteData.class);
//		}
//		catch(JsonParseException e)
//		{
//			e.printStackTrace();
//		}
//		catch(JsonMappingException e)
//		{
//			e.printStackTrace();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}

		return data;

	}


	//===============================================================================================
	public String getTYPEIDString()
	{//===============================================================================================
		return "SPRITE."+id();
	}






	public String displayName(){return displayName;}
	public int widthPixels1X(){return widthPixels1X;}
	public int heightPixels1X(){return heightPixels1X;}
	public int widthPixelsHQ(){return widthPixels1X*2;}
	public int heightPixelsHQ(){return heightPixels1X*2;}
	public int frames(){return frames;}
	public boolean isNPC(){return isNPC;}
	public boolean isKid(){return isKid;}
	public boolean isAdult(){return isAdult;}
	public boolean isMale(){return isMale;}
	public boolean isFemale(){return isFemale;}
	public boolean isCar(){return isCar;}
	public boolean isAnimal(){return isAnimal;}
	public boolean hasShadow(){return hasShadow;}
	public boolean isRandom(){return isRandom;}
	public boolean isDoor(){return isDoor;}
	public boolean isGame(){return isGame;}
	public boolean isItem(){return isItem;}
	public boolean forceHQ2X(){return forceHQ2X;}
	public int eventID(){return eventID;}
	public String itemGameDescription(){return itemGameDescription;}
	public float gamePrice(){return gamePrice;}
	public int utilityOffsetXPixels1X(){return utilityOffsetXPixels1X;}
	public int utilityOffsetYPixels1X(){return utilityOffsetYPixels1X;}
	public int utilityOffsetXPixelsHQ(){return utilityOffsetXPixels1X*2;}
	public int utilityOffsetYPixelsHQ(){return utilityOffsetYPixels1X*2;}
	public String dataMD5(){return dataMD5;}
	public String paletteMD5(){return paletteMD5;}
	public ArrayList<SpriteAnimationSequence> animationList(){return animationList;}
	public String comment(){return comment;}
	public boolean forceMD5Export(){return forceMD5Export;}





	public void setComment(String s){comment = s;}
	public void setWidthPixels1X(int s){widthPixels1X = s;}
	public void setHeightPixels1X(int s){heightPixels1X = s;}
	public void setFrames(int s){frames = s;}
	public void setDisplayName(String s){displayName = s;}
	public void setIsNPC(boolean s){isNPC = s;}
	public void setIsKid(boolean s){isKid = s;}
	public void setIsAdult(boolean s){isAdult = s;}
	public void setIsMale(boolean s){isMale = s;}
	public void setIsFemale(boolean s){isFemale = s;}
	public void setIsCar(boolean s){isCar = s;}
	public void setIsAnimal(boolean s){isAnimal = s;}
	public void setHasShadow(boolean s){hasShadow = s;}
	public void setIsRandom(boolean s){isRandom = s;}
	public void setIsDoor(boolean s){isDoor = s;}
	public void setIsGame(boolean s){isGame = s;}
	public void setIsItem(boolean s){isItem = s;}
	public void setForceHQ2X(boolean s){forceHQ2X = s;}
	public void setForceMD5Export(boolean s){forceMD5Export = s;}
	public void setEventID(int s){eventID = s;}
	public void setItemGameDescription(String s){itemGameDescription = s;}
	public void setGamePrice(float s){gamePrice = s;}
	public void setUtilityOffsetXPixels1X(int s){utilityOffsetXPixels1X = s;}
	public void setUtilityOffsetYPixels1X(int s){utilityOffsetYPixels1X = s;}
	public void setDataMD5(String s){dataMD5 = s;}
	public void setPaletteMD5(String s){paletteMD5 = s;}



}
