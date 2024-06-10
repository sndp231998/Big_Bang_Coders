package com.big.bang.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "waste_collector")
@NoArgsConstructor
@Getter
@Setter
public class Collector {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "collector_id", nullable = false)
	private int collectorId;
	
	 @ManyToOne
	    @JoinColumn(name = "wasteId", nullable = false)
	    private Waste waste;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	    private User user;
	    
	    private Date collectDate; 
	    
	    
	    
	    
	    
	    
	     
	   
	
}
