/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 23, 2021
 */
package computerbuilding.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "customer_quote_fulfillment")
public class CustomerQuoteFulfillment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_quote_fulfillment_id")
	private long id;
	@NonNull
	@ManyToOne(targetEntity = CustomerQuoteRequest.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_quote_request_id_fk", referencedColumnName = "customer_quote_request_id")
	private CustomerQuoteRequest customerQuoteRequest;
	@NonNull
	@ManyToOne(targetEntity = Motherboard.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "motherboard_id_fk", referencedColumnName = "motherboard_id")
	private Motherboard motherboard;
	@NonNull
	@ManyToOne(targetEntity = CPU.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cpu_id_fk", referencedColumnName = "cpu_id")
	private CPU cPU;
	@NonNull
	@ManyToOne(targetEntity = RAM.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "ram_id_fk", referencedColumnName = "ram_id")
	private RAM rAM;
	@NonNull
	@ManyToOne(targetEntity = GPU.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "gpu_id_fk", referencedColumnName = "gpu_id")
	private GPU gPU;
	@NonNull
	@ManyToOne(targetEntity = ThermalCompound.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "thermal_compound_id_fk", referencedColumnName = "thermal_compound_id")
	private ThermalCompound thermalCompound;
	@NonNull
	@ManyToOne(targetEntity = StorageDevice.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "storage_device_id_fk", referencedColumnName = "storage_device_id")
	private StorageDevice storageDevice;
	@NonNull
	@ManyToOne(targetEntity = PSU.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "psu_id_fk", referencedColumnName = "psu_id")
	private PSU pSU;
	@NonNull
	@ManyToOne(targetEntity = Monitor.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "monitor_id_fk", referencedColumnName = "monitor_id")
	private Monitor monitor;
	@NonNull
	@ManyToOne(targetEntity = Keyboard.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "keyboard_id_fk", referencedColumnName = "keyboard_id")
	private Keyboard keyboard;
	@Column(name = "total_cost")
	private double totalCost;
	@Column(name = "customer_confirmation")
	private Boolean customerConfirmation;
}
