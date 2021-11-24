/**
 *@author Jake Soulinthavong - jakesoul
 *CIS175 - Fall 2021
 *September 23, 2021
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
@Table(name="cpu")
public class CPU {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cpu_id")
	private long id;
	@NonNull
	@Column(name="name")
	private String name;
	@NonNull
	@Column(name="brand")
	private String brand;
	@NonNull
	@Column(name="cpu_socket")
	private String cPUSocket;
	@Column(name="core_count")
	private int coreCount;
	@Column(name="thread_count")
	private int threadCount;
	@Column(name="cpu_speed")
	private double cPUSpeed;
	@Column(name="price")
	private double price;
	@OneToMany(targetEntity=CustomerQuoteFulfillment.class, mappedBy="cPU", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
