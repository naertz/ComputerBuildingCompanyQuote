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
@Table(name="customer_quote_fulfillment")
public class CustomerQuoteFulfillment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_quote_fulfillment_id")
	private long id;
	@NonNull
	@ManyToOne(targetEntity=CustomerQuoteRequest.class, fetch=FetchType.LAZY)
	@JoinColumn(name="customer_quote_request_id_fk", referencedColumnName="customer_quote_request_id")
	private CustomerQuoteRequest customerQuoteRequest;
	@NonNull
	@ManyToOne(targetEntity=Motherboard.class, fetch=FetchType.LAZY)
	@JoinColumn(name="motherboard_id_fk", referencedColumnName="motherboard_id")
	private Motherboard motherboard;
	@NonNull
	@ManyToOne(targetEntity=CPU.class, fetch=FetchType.LAZY)
	@JoinColumn(name="cpu_id_fk", referencedColumnName="cpu_id")
	private CPU cPU;
	@NonNull
	@ManyToOne(targetEntity=RAM.class, fetch=FetchType.LAZY)
	@JoinColumn(name="ram_id_fk", referencedColumnName="ram_id")
	private RAM rAM;
	@NonNull
	@ManyToOne(targetEntity=GPU.class, fetch=FetchType.LAZY)
	@JoinColumn(name="gpu_id_fk", referencedColumnName="gpu_id")
	private GPU gPU;
	@NonNull
	@ManyToOne(targetEntity=ThComp.class, fetch=FetchType.LAZY)
	@JoinColumn(name="thcomp_id_fk", referencedColumnName="thcomp_id")
	private ThComp thcomp;
	@Column(name="total_cost")
	private double totalCost;
	@Column(name="customer_confirmation")
	private Boolean customerConfirmation;
	
	
	
}
