package com.example.simpledagger.model.movies;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("status_message")
	private String statusMessage;

	@SerializedName("data")
	private Data data;

	@SerializedName("@meta")
	private Meta meta;

	@SerializedName("status")
	private String status;

	public void setStatusMessage(String statusMessage){
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage(){
		return statusMessage;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"status_message = '" + statusMessage + '\'' + 
			",data = '" + data + '\'' + 
			",@meta = '" + meta + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}