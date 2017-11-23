package com.websys.springmvc.model;

public class Student {
	private long id;
	private String name;
	private String classe;
	private String Section;
	
	
	
	
	
	public Student() {
		id=0;
	}





	public Student(long id, String name, String classe, String section) {
		super();
		this.id = id;
		this.name = name;
		this.classe = classe;
		Section = section;
	}





	public long getId() {
		return id;
	}





	public void setId(long l) {
		this.id =  l;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getClasse() {
		return classe;
	}





	public void setClasse(String classe) {
		this.classe = classe;
	}





	public String getSection() {
		return Section;
	}





	public void setSection(String section) {
		Section = section;
	}





	





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Section == null) ? 0 : Section.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = (int) (prime * result + id);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (Section == null) {
			if (other.Section != null)
				return false;
		} else if (!Section.equals(other.Section))
			return false;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classe=" + classe
				+ ", Section=" + Section + "]";
	}
	
	
	
	
	
}
