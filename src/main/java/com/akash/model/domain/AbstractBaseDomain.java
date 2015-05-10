package com.akash.model.domain;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

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
		if (this.id != null && other.getId() != null
				&& this.id.equals(other.getId())) {
			return true;
		}
		return false;
	}

	public int hashCode() {

		if (this.id != null)
			return this.id.hashCode();
		else
			return super.hashCode();
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