package beans;

import java.math.BigDecimal;

public class MediaRental {
	
	private String rentalName;
	private String rentalType;
	private BigDecimal price;
	private String condition;
	private int rating;
	private String category;
	
	
	public MediaRental() {
		
	}

	public MediaRental(String rentalName, String rentalType, BigDecimal price, String condition, int rating,
			String category) {
		this.rentalName = rentalName;
		this.rentalType = rentalType;
		this.price = price;
		this.condition = condition;
		this.rating = rating;
		this.category = category;
	}	
	
	public String getRentalName() {
		return rentalName;
	}
	public void setRentalName(String rentalName) {
		this.rentalName = rentalName;
	}
	public String getRentalType() {
		return rentalType;
	}
	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "MediaRental [rentalName=" + rentalName + ", rentalType=" + rentalType
				+ ", price=" + price + ", condition=" + condition + ", rating=" + rating + ", category=" + category
				+ "]";
	}
	
	
	
	

}
