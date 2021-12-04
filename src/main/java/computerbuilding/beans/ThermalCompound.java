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
@Table(name="thermal_compound")
public class ThermalCompound {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="thermal_compound_id")
	private long id;
	@NonNull
	@Column(name="name")
	private String name;
	@NonNull
	@Column(name="amount")
	private String amount;
	@NonNull
	@Column(name="material_base")
	private String materialBase;
	@Column(name="price")
	private float price;
	@OneToMany(targetEntity=CustomerQuoteFulfillment.class, mappedBy="thermalCompound", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}