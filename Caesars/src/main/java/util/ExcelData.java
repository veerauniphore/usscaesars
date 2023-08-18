package util;

import javax.persistence.Entity;

import com.poiji.annotation.ExcelCellName;

@Entity
public class ExcelData {
	
	@ExcelCellName("Associate Senior Manager")
	private String associate_senior_manager;
	
	@ExcelCellName("Senior Manager")
	private String senior_manager;
	
	@ExcelCellName("AGM")
	private String AGM;
	
	@ExcelCellName("GM")
	private String GM;
	
	@ExcelCellName("Campaign Name")
	private String campaign_name;
	
	@ExcelCellName("Director")
	private String director;
	
	

}
