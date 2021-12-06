/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 6, 2021
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
@Table(name = "keyboard")
public class Keyboard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "keyboard_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "brand")
	private String brand;
	@NonNull
	@Column(name = "style")
	private String style;
	@NonNull
	@Column(name = "color")
	private String color;
	@NonNull
	@Column(name = "backlit")
	private String backlit;
	@NonNull
	@Column(name = "connection_type")
	private String connectionType;
	@NonNull
	@Column(name = "switch_type")
	private String switchType;
	@Column(name = "size_percent")
	private int sizePercent;
	@Column(name = "price")
	private double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "keyboard", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}