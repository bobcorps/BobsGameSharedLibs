package com.bobsgame.shared;



import java.io.IOException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.bobsgame.shared.SpriteData;
import com.bobsgame.shared.AssetData;
import com.google.gson.Gson;



//=========================================================================================================================
public class DialogueData extends AssetData
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



/*

	//TODO: not used yet

	public boolean forAction = false;

	public boolean forCutscene = false;

	public boolean notTalkingAnyMoreToday = false;

	public boolean generic = false;



	public boolean annoyed = false;
	public boolean angry = false;
	public boolean jealous = false;
	public boolean furious = false;



	public boolean sad = false;
	public boolean beaten = false;
	public boolean suicidal = false;
	public boolean crying = false;

	public boolean happy = false;


	public boolean defensive = false;
	public boolean scared = false;


	//public boolean cocky = false;
	//public boolean horny = false;
	//public boolean desperate = false;
	//public boolean proud = false;

	public boolean bored = false;

*/



	private String caption = "";
	private String comment = "";
	private String text = "";

	//=========================================================================================================================
	public DialogueData()
	{//=========================================================================================================================

	}

	//=========================================================================================================================
	public DialogueData(int id, String name, String caption, String comment, String text)
	{//=========================================================================================================================

		super(id,name);

		this.caption = caption;
		this.comment = comment;
		this.text = text;

	}



	//===============================================================================================
	public static DialogueData fromBase64ZippedJSON(String b64)
	{//===============================================================================================


		String decode64 = Utils.decodeBase64String(b64);
		String json = Utils.unzipString(decode64);

		//Gson gson = new Gson();
		//DialogueData data = gson.fromJson(json,DialogueData.class);

		return fromJSON(json);



	}



	//===============================================================================================
	public static DialogueData fromJSON(String json)
	{//===============================================================================================

		Gson gson = new Gson();
		DialogueData data = gson.fromJson(json,DialogueData.class);

//		ObjectMapper mapper = new ObjectMapper();
//		DialogueData data = null;
//
//		try
//		{
//			data = mapper.readValue(json, DialogueData.class);
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
		return "DIALOGUE."+id();
	}





	public String caption(){return caption;}
	public String comment(){return comment;}
	public String text(){return text;}


	public void setCaption(String s){this.caption=s;}
	public void setComment(String s){this.comment=s;}
	public void setText(String s){this.text=s;}




}
