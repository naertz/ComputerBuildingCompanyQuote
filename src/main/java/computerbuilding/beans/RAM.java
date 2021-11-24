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
@Table(name="ram")
public class RAM {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ram_id")
	private long id;
	@NonNull
	@Column(name="name")
	private String name;
	@NonNull
	@Column(name="generation")
	private String generation;
	@NonNull
	@Column(name="speed")
	private String speed;
	@NonNull
	@Column(name="capacity")
	private String capacity;
	@Column(name="sticks")
	private int sticks;
	@NonNull
	@Column(name="latency")
	private String latency;
	@Column(name="price")
	private float price;
	@OneToMany(targetEntity=CustomerQuoteFulfillment.class, mappedBy="rAM", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
