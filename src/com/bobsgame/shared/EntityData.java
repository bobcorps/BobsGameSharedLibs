package com.bobsgame.shared;

import java.io.IOException;
import java.util.ArrayList;

import com.bobsgame.shared.AssetData;
import com.bobsgame.shared.MapData.RenderOrder;
import com.google.gson.Gson;




//=========================================================================================================================
public class EntityData extends AssetData
{//=========================================================================================================================




	private String spriteName = null;


	private float spawnXPixels1X = 0;
	private float spawnYPixels1X = 0;



	private int initialFrame=0;

	private boolean pushable = false;
	private boolean nonWalkable = false;


	private float toAlpha = 1.0f;



	private float scale=1.0f;


	private boolean disableShadow = false;


	private boolean aboveTopLayer = false;//dont use this in client, client only uses layer, this is only for the editor to easily set a sprite to above layer.
	//eventually it will be replaced when i move to objectData save/load in editor.
	//TODO: phase this out for just "set layer" above/below


	private int layer = 0;//ground

	private RenderOrder renderOrder=RenderOrder.GROUND;

	private boolean aboveWhenEqual=false;//if two entities are on the same layer and the bottom lines up, render this one after

	private boolean alwaysOnBottom=false;//render first on whatever layer it is on
	private boolean alwaysOnTop=false;//render last on whatever layer it is on



	private boolean animateThroughFrames = false;//cannot have BOTH this and animateThroughCurrentAnimation set.

	private int ticksBetweenFrames = 100;
	private boolean randomUpToTicksBetweenFrames = false;//added in editor

	private boolean randomFrames = false;//initial frame will be randomized too. applies to both animateThroughAllFrames and animateThroughCurrentAnimation

	private int ticksBetweenAnimation = 0;//will be random up to this if checked random up to ticks between animation loop
	private boolean randomTimeBetweenAnimation = false;




	public int walkSpeed = 1;//DONE: phase this out for a ticksPerPixelMoved edit box // THIS VARIABLE IS OBSOLETE, keeping it for backwards compatibility.
	private float ticksPerPixelMoved = 10;





	private int eventID = -1;
	private boolean onlyHereDuringEvent = false;//TODO: what does this do




	private int mapID = -1;
	private int stateID = -1;









	//TODO: implement in editor:
	private boolean animateThroughCurrentAnimation = false;//TODO: implement
	private boolean loopAnimation = true;//TODO: implement

	private float voicePitch=0; //TODO: implement

	private boolean animationDisabled=false; //TODO see where this is used.

	private boolean hitLayerDisabled=false;
	private boolean ignoreHitPlayer=false;
	private boolean ignoreHitEntities=false;
	private boolean dontUsePathfinding=false;

	//public boolean ignore_fx_layer=false;
	private boolean pullPlayer=false;
	private boolean pushPlayer=false;



	private ArrayList<String> behaviorList = new ArrayList<String>();
	private ArrayList<String> connectionTYPEIDList = new ArrayList<String>();


	private String comment = "";





	private boolean isNPC = false;//only used for export from tools for now to determine whether to spawn a character or an entity. determined from the SPRITE.isNPC, which is where it should be.



	//=========================================================================================================================
	public EntityData()
	{//=========================================================================================================================

	}

	//=========================================================================================================================
	public EntityData(int id, String name, String spriteAssetName, float spawnXPixels1X, float spawnYPixels1X, int initialFrame, boolean pushable, boolean nonWalkable, int alphaByte, float scale, int ticksPerPixelMoved, boolean aboveTopLayer, boolean aboveWhenEqual, boolean alwaysOnBottom, boolean animateThroughFrames,boolean randomTimeBetweenAnimation, int ticksBetweenFrames,int ticksBetweenAnimation,boolean onlyHereDuringEvent, boolean randomFrames, boolean disableShadow, int eventID, String comment)
	{//=========================================================================================================================


		super(id,name);



		this.spriteName = spriteAssetName;


		this.spawnXPixels1X = spawnXPixels1X;
		this.spawnYPixels1X = spawnYPixels1X;


		this.initialFrame = initialFrame;

		this.pushable = pushable;
		this.nonWalkable = nonWalkable;

		this.toAlpha = ((float)alphaByte/255.0f); //NOTICE, USING toAlpha NOT alpha, alpha is wrong!


		this.scale = (float)scale;

		this.ticksPerPixelMoved = ticksPerPixelMoved;

		this.disableShadow = disableShadow;

		if(aboveTopLayer)this.renderOrder=RenderOrder.ABOVE_TOP;
		this.aboveTopLayer = aboveTopLayer;//dont use this in client, client only uses layer

		this.aboveWhenEqual = aboveWhenEqual;
		this.alwaysOnBottom = alwaysOnBottom;


		this.animateThroughFrames = animateThroughFrames;
		this.randomFrames = randomFrames;
		this.randomTimeBetweenAnimation = randomTimeBetweenAnimation;
		this.ticksBetweenFrames = ticksBetweenFrames;
		this.ticksBetweenAnimation = ticksBetweenAnimation;





		this.eventID = eventID;
		this.onlyHereDuringEvent = onlyHereDuringEvent;

		this.comment = comment;


	}


	//=========================================================================================================================
	public EntityData(int id, String name, String spriteAssetName,float spawnXPixels1X, float spawnYPixels1X)
	{//=========================================================================================================================


		this(id, name, spriteAssetName,spawnXPixels1X,spawnYPixels1X,0,false,false,255,1.0f,12,false,false,false,false,false,0,0,false,false,false,-1, "");


	}


	//=========================================================================================================================
	public EntityData(int id,String name)
	{//=========================================================================================================================
		this(
				id,               //int id,
				name,             //String name,
				"",  //String spriteAssetName,
				0,   //int spawnXPixels1X,
				0,   //int spawnYPixels1X,
				0,                //int initialFrame,
				false,            //boolean pushable,
				true,             //boolean nonWalkable,
				255,              //int alphaByte,
				1.0f,              //float scale,
				12,
				false,            //boolean aboveTopLayer,
				false,            //boolean aboveWhenEqual,
				false,            //boolean alwaysOnBottom,
				false,            //boolean animateThroughFrames,
				false,            //boolean randomTimeBetweenAnimation,
				0,                //int ticksBetweenFrames,
				0,                //int ticksBetweenAnimation,
				false,            //boolean onlyHereDuringEvent,
				false,            //boolean randomFrames,
				true,            //boolean disableShadow,
				-1,        //int eventID,
				""
		);
	}




//	//=========================================================================================================================
//	public void addConnection(String s)
//	{//=========================================================================================================================
//		connectionTYPEIDList.add(s);
//	}


//	//=========================================================================================================================
//	public void addBehavior(String s)
//	{//=========================================================================================================================
//		behaviorList.add(s);
//	}


	//===============================================================================================
	public static EntityData fromBase64ZippedJSON(String b64)
	{//===============================================================================================

		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);


		//Gson gson = new Gson();
		//EntityData data = gson.fromJson(unzip,EntityData.class);


		return fromJSON(json);
	}



	//===============================================================================================
	public static EntityData fromJSON(String json)
	{//===============================================================================================


		Gson gson = new Gson();
		EntityData data = gson.fromJson(json,EntityData.class);



//		ObjectMapper mapper = new ObjectMapper();
//		EntityData data = null;
//
//		try
//		{
//			data=mapper.readValue(json, EntityData.class);
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
		return "ENTITY."+id();
	}






	public float spawnXPixels1X(){return spawnXPixels1X;}
	public float spawnYPixels1X(){return spawnYPixels1X;}
	public float spawnXPixelsHQ(){return spawnXPixels1X*2;}
	public float spawnYPixelsHQ(){return spawnYPixels1X*2;}

	public String spriteName(){return spriteName;}
	public String comment(){return comment;}
	public int initialFrame(){return initialFrame;}
	public boolean pushable(){return pushable;}
	public boolean nonWalkable(){return nonWalkable;}
	public float toAlpha(){return toAlpha;}
	public float scale(){return scale;}
	public boolean disableShadow(){return disableShadow;}
	public int layerrr(){return layer;}
	public RenderOrder renderOrder(){return renderOrder;}
	public boolean aboveWhenEqual(){return aboveWhenEqual;}
	public boolean aboveTopLayer(){return aboveTopLayer;}
	public boolean alwaysOnBottom(){return alwaysOnBottom;}
	public boolean alwaysOnTop(){return alwaysOnTop;}
	public boolean isNPC(){return isNPC;}
	public boolean animatingThroughAllFrames(){return animateThroughFrames;}
	public boolean animatingThroughCurrentAnimation(){return animateThroughCurrentAnimation;}
	public boolean loopAnimation(){return loopAnimation;}
	public boolean randomFrames(){return randomFrames;}
	public boolean randomUpToTicksBetweenAnimationLoop(){return randomTimeBetweenAnimation;}
	public int ticksBetweenFrames(){return ticksBetweenFrames;}
	public boolean randomUpToTicksBetweenFrames(){return randomUpToTicksBetweenFrames;}
	public int ticksBetweenAnimationLoop(){return ticksBetweenAnimation;}

	public float ticksPerPixelMoved(){return ticksPerPixelMoved;}
	public boolean onlyHereDuringEvent(){return onlyHereDuringEvent;}
	public float voicePitch(){return voicePitch;}
	public boolean movementAnimationDisabled(){return animationDisabled;}
	public boolean hitLayerDisabled(){return hitLayerDisabled;}
	public boolean ignoreHitPlayer(){return ignoreHitPlayer;}
	public boolean ignoreHitEntities(){return ignoreHitEntities;}
	public boolean dontUsePathfinding(){return dontUsePathfinding;}
	public boolean pullPlayer(){return pullPlayer;}
	public boolean pushPlayer(){return pushPlayer;}
	public int eventID(){return eventID;}
	public int mapID(){return mapID;}
	public int stateID(){return stateID;}
	public ArrayList<String> connectionTYPEIDList(){return connectionTYPEIDList;}
	public ArrayList<String> behaviorList(){return behaviorList;}








	//set

	public void setSpriteName(String s){spriteName = s;}
	public void setInitialFrame(int s){initialFrame = s;}
	public void setPushable(boolean s){pushable = s;}
	public void setNonWalkable(boolean s){nonWalkable = s;}
	public void setToAlpha(float s){if(s>1.0f)s=1.0f;if(s<0.0f)s=0.0f;toAlpha = s;}
	public void setScale(float s){scale = s;}
	public void setDisableShadow(boolean s){disableShadow = s;}
	public void setRenderOrder(RenderOrder s){renderOrder = s;}
	public void setAboveTopLayer(boolean s){aboveTopLayer = s;}
	public void setAboveWhenEqual(boolean s){aboveWhenEqual = s;}
	public void setAlwaysOnBottom(boolean s){alwaysOnBottom = s;}
	public void setAlwaysOnTop(boolean s){alwaysOnTop = s;}
	public void setIsNPC(boolean s){isNPC = s;}
	public void setOnlyHereDuringEvent(boolean s){onlyHereDuringEvent = s;}
	public void setVoicePitch(float s){voicePitch = s;}
	public void setAnimationDisabled(boolean s){animationDisabled = s;}
	public void setHitLayerDisabled(boolean s){hitLayerDisabled = s;}
	public void setIgnoreHitPlayer(boolean s){ignoreHitPlayer = s;}
	public void setIgnoreHitEntities(boolean s){ignoreHitEntities = s;}
	public void setDontUsePathfinding(boolean s){dontUsePathfinding = s;}

	public void setRandomFrames(boolean s){randomFrames = s;}
	public void setRandomUpToTicksBetweenAnimationLoop(boolean s){randomTimeBetweenAnimation = s;}
	public void setTicksBetweenFrames(int s){ticksBetweenFrames = s;}
	public void setRandomUpToTicksBetweenFrames(boolean s){randomUpToTicksBetweenFrames = s;}
	public void setTicksBetweenAnimationLoop(int s){ticksBetweenAnimation = s;}

	public void setTicksPerPixelMoved(float s){ticksPerPixelMoved = s;}
	public void setSpawnXPixels1X(float s){spawnXPixels1X=s;}
	public void setSpawnYPixels1X(float s){spawnYPixels1X=s;}
	public void setMapID(int s){mapID = s;}
	public void setStateID(int s){stateID = s;}
	public void setEventID(int s){eventID = s;}
	public void setComment(String s){comment = s;}


	public void setAnimateThroughAllFrames(boolean s){animateThroughFrames = s;if(s==true)animateThroughCurrentAnimation = false;}
	public void setAnimateThroughCurrentAnimation(boolean s){animateThroughCurrentAnimation = s;if(s==true)animateThroughFrames = false;}
	public void setLoopAnimation(boolean s){loopAnimation = s;}


}