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
@Table(name = "cpu_cooler")
public class CPUCooler {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpu_cooler_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "fan_size")
	private String fanSize;
	@NonNull
	@Column(name = "connector_type")
	private String connectorType;
	@NonNull
	@Column(name = "cooling_type")
	private String coolingType;
	@NonNull
	@Column(name = "socket_compatibility")
	private String socketCompatibility;
	@Column(name = "price")
	private double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "cPUCooler", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
