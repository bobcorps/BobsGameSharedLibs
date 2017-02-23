package com.bobsgame.shared;

import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;



import com.bobsgame.shared.AssetData;
import com.google.gson.Gson;



//=========================================================================================================================
public class MapStateData extends AssetData
{//=========================================================================================================================


	private ArrayList<LightData> lightDataList = new ArrayList<LightData>();

	private ArrayList<EntityData> entityDataList = new ArrayList<EntityData>();

	//public ArrayList<EntityData> characterDataList = new ArrayList<EntityData>();

	private ArrayList<AreaData> areaDataList = new ArrayList<AreaData>();



	private int mapID = -1;


	//=========================================================================================================================
	public MapStateData()
	{//=========================================================================================================================

	}


	//===============================================================================================
	public MapStateData(int id, String name)
	{//===============================================================================================
		super(id,name);
	}


	//===============================================================================================
	public static MapStateData fromBase64ZippedJSON(String b64)
	{//===============================================================================================



		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);

		//Gson gson = new Gson();
		//MapStateData data = gson.fromJson(json,MapStateData.class);


		return fromJSON(json);
	}



	//===============================================================================================
	public static MapStateData fromJSON(String json)
	{//===============================================================================================



		Gson gson = new Gson();
		MapStateData data = gson.fromJson(json,MapStateData.class);


//		ObjectMapper mapper = new ObjectMapper();
//		MapStateData data = null;
//
//		try
//		{
//			data = mapper.readValue(json, MapStateData.class);
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
		return "STATE."+id();
	}

	public ArrayList<LightData> lightDataList(){return lightDataList;}
	public ArrayList<EntityData> entityDataList(){return entityDataList;}
	public ArrayList<AreaData> areaDataList(){return areaDataList;}


	public int mapID(){return mapID;}

	public void setMapID(int s){mapID=s;}


}
