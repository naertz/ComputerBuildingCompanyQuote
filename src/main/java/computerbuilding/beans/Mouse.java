/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 10, 2021
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
@Table(name = "mouse")
public class Mouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mouse_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "brand")
	private String brand;
	@NonNull
	@Column(name = "color")
	private String color;
	@NonNull
	@Column(name = "tracking_method")
	private String trackingMethod;
	@NonNull
	@Column(name = "connection_type")
	private String connectionType;
	@NonNull
	@Column(name = "hand_orientation")
	private String handOrientation;
	@NonNull
	@Column(name = "maximum_dpi")
	private Integer maximumDPI;
	@NonNull
	@Column(name = "price")
	private Double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "mouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
