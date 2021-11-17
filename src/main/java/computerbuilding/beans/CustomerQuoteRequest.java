/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */

package computerbuilding.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="customer_quote_request")
public class CustomerQuoteRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_quote_request_id")
	private long id;
	@NonNull
	@Column(name="first_name")
	private String firstName;
	@NonNull
	@Column(name="last_name")
	private String lastName;
	@NonNull
	@Column(name="email")
	private String email;
	@Column(name="phone_number")
	private String phoneNumber;
	@NonNull
	@Column(name="computer_needs_description")
	private String computerNeedsDescription;
	@Column(name="budget")
	private double budget;
}
