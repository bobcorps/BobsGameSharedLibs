package com.bobsgame.shared;

import java.io.IOException;
import java.util.ArrayList;

import com.bobsgame.shared.AssetData;
import com.google.gson.Gson;



//=========================================================================================================================
public class AreaData extends AssetData
{//=========================================================================================================================



	private int mapXPixels1X = 0;
	private int mapYPixels1X = 0;

	private int widthPixels1X = 0;
	private int heightPixels1X = 0;



	private boolean randomPointOfInterestOrExit = false;
	private boolean randomNPCSpawnPoint = false;
	private int standSpawnDirection = -1;
	private int waitHereTicks = 0;//see random stay here
	private boolean randomWaitTime = false;
	private boolean onlyOneAllowed = false;
	private boolean randomNPCStayHere = false;//for cars, audience//the reason there are BOTH stay here and wait here==-1 is that i might want a spawned NPC to walk away from a chair, but a new NPC come and sit down permanently. just a little more lively! :-) I don't know if i thought of this when i put them in, but i could presumably use it for that.
	private float randomSpawnChance = 1.0f;//will distribute max randoms across spawn points based on chance.
	private boolean randomSpawnOnlyTryOnce = false;//one shot spawn chance for cars, audience
	private boolean randomSpawnOnlyOffscreen = false;
	private int randomSpawnDelay = 1000;
	private boolean randomSpawnKids = true;
	private boolean randomSpawnAdults = true;
	private boolean randomSpawnMales = true;
	private boolean randomSpawnFemales = true;
	private boolean randomSpawnCars = false;

	//TODO: handle these
	private boolean autoPilot = false;//player will enable autopilot, show autopilot caption, follow connections
	private boolean playerFaceDirection = false;//for couches, chairs
	private boolean suckPlayerIntoMiddle = false;//for chairs
	private int eventID = -1;


	private String comment = "";


	private int mapID = -1;
	private int stateID = -1;




	//can pull connections from sprites to areas and doors, should add these to points of interest list inside sprite.
	//automatically connect lines from all random points to any random spawn points, when click on random spawn point, should go to other spawn points as well

	private ArrayList<String> connectionTYPEIDList = new ArrayList<String>(); //should ALWAYS be by ID






	//warp area specific

	private String destinationTYPEID = ""; //AREA.ID


	private int arrivalXPixels1X=-1;
	private int arrivalYPixels1X=-1;



	private boolean isWarpArea = false;




	//ONLY USED FOR EXPORT
	private String destinationMapName = "";
	private String destinationWarpAreaName = "";


	//=========================================================================================================================
	public AreaData()
	{//=========================================================================================================================

	}


	//=========================================================================================================================
	public AreaData(int id, String name, int mapXPixels1X, int mapYPixels1X, int widthPixels1X, int heightPixels1X, String destinationTYPEID, int arrivalXPixels1X, int arrivalYPixels1X, boolean randomPointOfInterestOrExit,boolean randomNPCSpawnPoint,int standSpawnDirection,int waitHereTicks,boolean randomWaitTime,boolean onlyOneAllowed,boolean randomNPCStayHere,	float randomSpawnChance,	boolean randomSpawnOnlyTryOnce,	boolean randomSpawnOnlyOffscreen,	int randomSpawnDelay,	boolean randomSpawnKids,	boolean randomSpawnAdults,	boolean randomSpawnMales,	boolean randomSpawnFemales,	boolean randomSpawnCars,boolean autoPilot, boolean playerFaceDirection, boolean suckPlayerIntoMiddle, int eventID, String comment)
	{//=========================================================================================================================



		this(	id,
				name,
				mapXPixels1X,
				mapYPixels1X,
				widthPixels1X,
				heightPixels1X,
				randomPointOfInterestOrExit,
				randomNPCSpawnPoint,
				standSpawnDirection,
				waitHereTicks,
				randomWaitTime,
				onlyOneAllowed,
				randomNPCStayHere,
				randomSpawnChance,
				randomSpawnOnlyTryOnce,
				randomSpawnOnlyOffscreen,
				randomSpawnDelay,
				randomSpawnKids,
				randomSpawnAdults,
				randomSpawnMales,
				randomSpawnFemales,
				randomSpawnCars,
				autoPilot,
				playerFaceDirection,
				suckPlayerIntoMiddle,
				eventID,
				comment);



		this.destinationTYPEID = destinationTYPEID;

		this.arrivalXPixels1X=arrivalXPixels1X;
		this.arrivalYPixels1X=arrivalYPixels1X;











		isWarpArea = true;


	}


	//=========================================================================================================================
	public AreaData(int id,String name)
	{//=========================================================================================================================
		super(id,name);


	}





	//=========================================================================================================================
	public AreaData(int id, String name, int mapXPixels1X, int mapYPixels1X, int widthPixels1X, int heightPixels1X, boolean randomPointOfInterestOrExit, boolean randomNPCSpawnPoint,int standSpawnDirection, int waitHereTicks,	boolean randomWaitTime,	boolean onlyOneAllowed,	boolean randomNPCStayHere,	float randomSpawnChance,	boolean randomSpawnOnlyTryOnce,	boolean randomSpawnOnlyOffscreen,	int randomSpawnDelay,	boolean randomSpawnKids,	boolean randomSpawnAdults,	boolean randomSpawnMales,	boolean randomSpawnFemales,	boolean randomSpawnCars,boolean autoPilot, boolean playerFaceDirection, boolean suckPlayerIntoMiddle, int eventID, String comment)
	{//=========================================================================================================================


		super(id,name);


		this.mapXPixels1X = mapXPixels1X;
		this.mapYPixels1X = mapYPixels1X;

		this.widthPixels1X=widthPixels1X;
		this.heightPixels1X=heightPixels1X;



		this.randomPointOfInterestOrExit = randomPointOfInterestOrExit;
		this.randomNPCSpawnPoint = randomNPCSpawnPoint;
		this.standSpawnDirection = standSpawnDirection;
		this.waitHereTicks = waitHereTicks;
		this.randomWaitTime = randomWaitTime;
		this.onlyOneAllowed = onlyOneAllowed;
		this.randomNPCStayHere = randomNPCStayHere;
		this.randomSpawnChance = randomSpawnChance;
		this.randomSpawnOnlyTryOnce = randomSpawnOnlyTryOnce;
		this.randomSpawnOnlyOffscreen = randomSpawnOnlyOffscreen;
		this.randomSpawnDelay = randomSpawnDelay;
		this.randomSpawnKids = randomSpawnKids;
		this.randomSpawnAdults = randomSpawnAdults;
		this.randomSpawnMales = randomSpawnMales;
		this.randomSpawnFemales = randomSpawnFemales;
		this.randomSpawnCars = randomSpawnCars;

		this.autoPilot = autoPilot;
		this.playerFaceDirection = playerFaceDirection;
		this.suckPlayerIntoMiddle = suckPlayerIntoMiddle;
		this.eventID = eventID;


	}









	//=========================================================================================================================
	public void addConnectionString(String s)
	{//=========================================================================================================================
		connectionTYPEIDList.add(s);
	}

	//===============================================================================================
	public static AreaData fromBase64ZippedJSON(String b64)
	{//===============================================================================================

		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);

		//Gson gson = new Gson();
		//AreaData data = gson.fromJson(json,AreaData.class);


		return fromJSON(json);
	}



	//===============================================================================================
	public static AreaData fromJSON(String json)
	{//===============================================================================================


		Gson gson = new Gson();
		AreaData data = gson.fromJson(json,AreaData.class);

//		ObjectMapper mapper = new ObjectMapper();
//		AreaData data = null;
//
//		try
//		{
//			data = mapper.readValue(json, AreaData.class);
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

		if(isWarpArea==true)return "AREA."+id();
		return "AREA."+id();
	}




















	public int mapXPixels1X(){return mapXPixels1X;}
	public int mapYPixels1X(){return mapYPixels1X;}
	public int mapXPixelsHQ(){return mapXPixels1X*2;}
	public int mapYPixelsHQ(){return mapYPixels1X*2;}
	public int widthPixels1X(){return widthPixels1X;}
	public int heightPixels1X(){return heightPixels1X;}
	public int widthPixelsHQ(){return widthPixels1X*2;}
	public int heightPixelsHQ(){return heightPixels1X*2;}
	public int arrivalXPixels1X(){return arrivalXPixels1X;}
	public int arrivalYPixels1X(){return arrivalYPixels1X;}
	public int arrivalXPixelsHQ(){return arrivalXPixels1X*2;}
	public int arrivalYPixelsHQ(){return arrivalYPixels1X*2;}

	public boolean isWarpArea(){return isWarpArea;}
	public boolean randomPointOfInterestOrExit(){return randomPointOfInterestOrExit;}
	public boolean randomNPCSpawnPoint(){return randomNPCSpawnPoint;}
	public int standSpawnDirection(){return standSpawnDirection;}
	public int waitHereTicks(){return waitHereTicks;}
	public boolean randomWaitTime(){return randomWaitTime;}
	public boolean onlyOneAllowed(){return onlyOneAllowed;}
	public boolean randomNPCStayHere(){return randomNPCStayHere;}
	public float randomSpawnChance(){return randomSpawnChance;}
	public boolean randomSpawnOnlyTryOnce(){return randomSpawnOnlyTryOnce;}
	public boolean randomSpawnOnlyOffscreen(){return randomSpawnOnlyOffscreen;}
	public int randomSpawnDelay(){return randomSpawnDelay;}
	public boolean randomSpawnKids(){return randomSpawnKids;}
	public boolean randomSpawnAdults(){return randomSpawnAdults;}
	public boolean randomSpawnMales(){return randomSpawnMales;}
	public boolean randomSpawnFemales(){return randomSpawnFemales;}
	public boolean randomSpawnCars(){return randomSpawnCars;}
	public boolean autoPilot(){return autoPilot;}
	public boolean playerFaceDirection(){return playerFaceDirection;}
	public boolean suckPlayerIntoMiddle(){return suckPlayerIntoMiddle;}
	public int eventID(){return eventID;}
	//public String stateName(){return data.stateName;}
	public ArrayList<String> connectionTYPEIDList(){return connectionTYPEIDList;}
	public String comment(){return comment;}
	public String destinationTYPEIDString(){return destinationTYPEID;}
	public String destinationMapName(){return destinationMapName;}
	public String destinationWarpAreaName(){return destinationWarpAreaName;}
	public int mapID(){return mapID;}
	public int stateID(){return stateID;}







	public void setDestinationMapName(String s){destinationMapName=s;}
	public void setDestinationWarpAreaName(String s){destinationWarpAreaName=s;}
	public void setDestinationTYPEIDString(String s){destinationTYPEID = s;}
	public void setRandomPointOfInterestOrExit(boolean s){randomPointOfInterestOrExit = s;}
	public void setRandomNPCSpawnPoint(boolean s){randomNPCSpawnPoint = s;}
	public void setStandSpawnDirection(int s){standSpawnDirection = s;}
	public void setWaitHereTicks(int s){waitHereTicks = s;}
	public void setRandomWaitTime(boolean s){randomWaitTime = s;}
	public void setOnlyOneAllowed(boolean s){onlyOneAllowed = s;}
	public void setRandomNPCStayHere(boolean s){randomNPCStayHere = s;}
	public void setRandomSpawnChance(float s){randomSpawnChance = s;}
	public void setRandomSpawnOnlyTryOnce(boolean s){randomSpawnOnlyTryOnce = s;}
	public void setRandomSpawnOnlyOffscreen(boolean s){randomSpawnOnlyOffscreen = s;}
	public void setRandomSpawnDelay(int s){randomSpawnDelay = s;}
	public void setRandomSpawnKids(boolean s){randomSpawnKids = s;}
	public void setRandomSpawnAdults(boolean s){randomSpawnAdults = s;}
	public void setRandomSpawnMales(boolean s){randomSpawnMales = s;}
	public void setRandomSpawnFemales(boolean s){randomSpawnFemales = s;}
	public void setRandomSpawnCars(boolean s){randomSpawnCars = s;}
	public void setAutoPilot(boolean s){autoPilot = s;}
	public void setPlayerFaceDirection(boolean s){playerFaceDirection = s;}
	public void setSuckPlayerIntoMiddle(boolean s){suckPlayerIntoMiddle = s;}
	public void setIsWarpArea(boolean s){isWarpArea = s;}
	public void setEventID(int s){eventID = s;}
	public void setComment(String s){comment = s;}
	public void setMapXPixels1X(int s){mapXPixels1X=s;}
	public void setMapYPixels1X(int s){mapYPixels1X=s;}
	public void setArrivalXPixels1X(int s){arrivalXPixels1X=s;}
	public void setArrivalYPixels1X(int s){arrivalYPixels1X=s;}
	public void setWidthPixels1X(int s){widthPixels1X=s;}
	public void setHeightPixels1X(int s){heightPixels1X=s;}
	public void setMapID(int s){mapID=s;}
	public void setStateID(int s){stateID=s;}



}
