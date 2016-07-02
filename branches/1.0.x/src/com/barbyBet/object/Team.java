package com.barbyBet.object;


public class Team {

	private int _id;
	private String _name;
	private String _img;
	private int _idWebService;
	
	public Team()
	{
		
	}
	
	public String getName() 
	{
		return _name;
	}
	
	public void setName(String name) 
	{
		this._name = name;
	}

	public String getImg() 
	{
		return _img;
	}
	
	public void setImg(String img) 
	{
		this._img = img;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public int getIdWebService() {
		return _idWebService;
	}

	public void setIdWebService(int _idWebService) {
		this._idWebService = _idWebService;
	}

}
