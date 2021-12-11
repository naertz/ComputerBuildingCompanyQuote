/**
 * @author Jake Soulinthavong - jakesoul
 * CIS175 - Fall 2021
 * September 23, 2021
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
@Table(name = "gpu")
public class GPU {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpu_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "brand")
	private String brand;
	@NonNull
	@Column(name = "memory_type")
	private String memoryType;
	@NonNull
	@Column(name = "video_memory")
	private Integer videoMemory;
	@NonNull
	@Column(name = "pci_express")
	private Integer pCIExpress;
	@NonNull
	@Column(name = "clock_speed")
	private Double clockSpeed;
	@NonNull
	@Column(name = "price")
	private Double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "gPU", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
