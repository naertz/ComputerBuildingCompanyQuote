/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
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
@Table(name = "motherboard")
public class Motherboard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "motherboard_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "form_factor")
	private String formFactor;
	@NonNull
	@Column(name = "socket")
	private String socket;
	@NonNull
	@Column(name = "chipset")
	private String chipset;
	@Column(name = "memory_slots")
	private int memorySlots;
	@Column(name = "pcie_slots")
	private int pCIESlots;
	@Column(name = "sata_ports")
	private int sATAPorts;
	@Column(name = "m2_slots")
	private int m2Slots;
	@Column(name = "price")
	private double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "motherboard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
