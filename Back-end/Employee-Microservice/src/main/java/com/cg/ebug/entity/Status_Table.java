package com.cg.ebug.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status_Table {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String type;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statusTable", cascade = CascadeType.ALL)
//	@JsonManagedReference(value="statusTable")
//	private List<Ticket_Table> tickets;

	public Status_Table() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status_Table(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

}
