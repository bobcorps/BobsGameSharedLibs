package com.bobsgame.shared;



import java.io.IOException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;


import com.bobsgame.shared.AssetData;
import com.google.gson.Gson;



//=========================================================================================================================
public class MusicData extends AssetData
{//=========================================================================================================================



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


	private String fileName = "";
	private String fullFilePath = "";//for use in editor

	private String md5Name = "";//for use in client

	private boolean preload = false;


	//=========================================================================================================================
	public MusicData()
	{//=========================================================================================================================

	}


	//=========================================================================================================================
	public MusicData(int id, String name, String filename)
	{//=========================================================================================================================

		super(id,name);

		this.fileName = filename;

	}



	//===============================================================================================
	public static MusicData fromBase64ZippedJSON(String b64)
	{//===============================================================================================

		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);

		return fromJSON(json);
	}



	//===============================================================================================
	public static MusicData fromJSON(String json)
	{//===============================================================================================



		Gson gson = new Gson();
		MusicData data = gson.fromJson(json,MusicData.class);


//		ObjectMapper mapper = new ObjectMapper();
//		MusicData data = null;
//
//		try
//		{
//			data = mapper.readValue(json, MusicData.class);
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
	public String toString()
	{//===============================================================================================

		String s = "";

		s = super.toString();




		s += "fileName:`"+fileName+"`,";
		s += "fullFilePath:`"+fullFilePath+"`,";
		s += "md5Name:`"+md5Name+"`,";
		s += "preload:`"+preload+"`,";


		return s;
	}


	//===============================================================================================
	public static MusicData fromString(String text)
	{//===============================================================================================

		MusicData data = new MusicData();

		String t = new String(text);


		t = t.substring(t.indexOf("name:`")+1);
		data.name = t.substring(0,t.indexOf("`"));
		t = t.substring(t.indexOf("`,")+1);

		t = t.substring(t.indexOf("id:`")+1);
		data.id = Integer.parseInt(t.substring(0,t.indexOf("`")));
		t = t.substring(t.indexOf("`,")+1);

		t = t.substring(t.indexOf("fileName:`")+1);
		data.fileName = t.substring(0,t.indexOf("`"));
		t = t.substring(t.indexOf("`,")+1);

		t = t.substring(t.indexOf("fullFilePath:`")+1);
		data.fullFilePath = t.substring(0,t.indexOf("`"));
		t = t.substring(t.indexOf("`,")+1);

		t = t.substring(t.indexOf("md5Name:`")+1);
		data.md5Name = t.substring(0,t.indexOf("`"));
		t = t.substring(t.indexOf("`,")+1);

		t = t.substring(t.indexOf("preload:`")+1);
		data.preload = Boolean.parseBoolean(t.substring(0,t.indexOf("`")));
		t = t.substring(t.indexOf("`,")+1);





		return data;


	}




	//===============================================================================================
	public String getTYPEIDString()
	{//===============================================================================================
		return "MUSIC."+id();
	}


	public String fileName(){return fileName;}
	public String fullFilePath(){return fullFilePath;}
	public String md5Name(){return md5Name;}
	public boolean preload(){return preload;}


	public void setFileName(String s){fileName = s;}
	public void setFullFilePath(String s){fullFilePath = s;}
	public void setMD5Name(String s){md5Name = s;}
	public void setPreload(boolean s){preload = s;}


}
