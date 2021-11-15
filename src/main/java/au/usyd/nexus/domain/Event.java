package au.usyd.nexus.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="event")

public class Event implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column private int event_id;
	@Column private int hobby_id;
	@Column private String event_name;
	@Column private String event_desc;
	@Column private int skill_level_limit;
	@Column private int number_limit;
	@Column private int attandance;
	@Column private String location;
	@Column private Date create_time;
	@Column private Date event_date;
	
	//alt +shift +s ,then click r
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getHobby_id() {
		return hobby_id;
	}
	public void setHobby_id(int hobby_id) {
		this.hobby_id = hobby_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_desc() {
		return event_desc;
	}
	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}
	public int getSkill_level_limit() {
		return skill_level_limit;
	}
	public void setSkill_level_limit(int skill_level_limit) {
		this.skill_level_limit = skill_level_limit;
	}
	public int getNumber_limit() {
		return number_limit;
	}
	public void setNumber_limit(int number_limit) {
		this.number_limit = number_limit;
	}
	public int getAttandance() {
		return attandance;
	}
	public void setAttandance(int attandance) {
		this.attandance = attandance;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//alt +shift +s , then click s
	@Override
	public String toString() {
		return "Event [event_id=" + event_id + ", hobby_id=" + hobby_id + ", event_name=" + event_name + ", event_desc="
				+ event_desc + ", skill_level_limit=" + skill_level_limit + ", number_limit=" + number_limit
				+ ", attandance=" + attandance + ", location=" + location + ", create_time=" + create_time
				+ ", event_date=" + event_date + "]";
	}
	

	
}