/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Dec 5, 2021
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
@Table(name = "monitor")
public class Monitor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "monitor_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@NonNull
	@Column(name = "brand")
	private String brand;
	@NonNull
	@Column(name = "panel_type")
	private String panelType;
	@NonNull
	@Column(name = "frame_sync")
	private String frameSync;
	@Column(name = "screen_size")
	private double screenSize;
	@Column(name = "resolution_width")
	private int resolutionWidth;
	@Column(name = "resolution_height")
	private int resolutionHeight;
	@Column(name = "refresh_rate")
	private int refreshRate;
	@Column(name = "response_time")
	private double responseTime;
	@Column(name = "price")
	private double price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "monitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
