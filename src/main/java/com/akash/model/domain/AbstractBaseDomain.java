package com.akash.model.domain;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * This class is based on below blog link :
 * http://www.onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html
 * 
 * @author Akash.Sharma
 *
 */
@MappedSuperclass
public class AbstractBaseDomain implements BaseDomain {

	@Id
	private String id = IdGenerator.createId();

	@Version
	private Integer version;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean equals(Object o) {

		if (o == null)
			return false;
		else if (this == o)
			return true;
		else if (!this.getClass().equals(o.getClass())) {
			return false;
		}
		BaseDomain other = (BaseDomain) o;
		if (this.id != null && other.getId() != null && this.id.equals(other.getId())) {
			return true;
		}
		return false;
	}

	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	public String toString() {
		return this.getClass().getName() + ":" + getId();
	}

	static class IdGenerator {
		public static String createId() {
			UUID uuid = java.util.UUID.randomUUID();
			return uuid.toString();
		}
	}
}