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
@Table(name="storage")
public class Storage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gpu_id")
	private long id;
	@NonNull
	@Column(name="name")
	private String name;
	@NonNull
	@Column(name="brand")
	private String brand;
	@Column(name="capacity")
	private int capacity;
	@Column(name="read")
	private String read;
	@Column(name="write")
	private int write;
	@Column(name="rpm")
	private double rpm;
	@Column(name="price")
	private double price;
	@OneToMany(targetEntity=CustomerQuoteFulfillment.class, mappedBy="sTORAGE", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}