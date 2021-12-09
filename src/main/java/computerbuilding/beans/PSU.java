/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
 */
package computerbuilding.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "psu")
public class PSU {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "psu_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "brand")
	private String brand;
	@NonNull
	@Column(name = "form_factor")
	private String formFactor;
	@NonNull
	@Column(name = "modular")
	private String modular;
	@NonNull
	@Column(name = "efficiency_rating")
	private String efficiencyRating;
	@Column(name = "wattage")
	private int wattage;
	@Column(name = "price")
	private double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "pSU", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
