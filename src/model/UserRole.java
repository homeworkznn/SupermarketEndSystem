package model;

import java.util.List;

public class UserRole{
	public long id;
	public String roleName;
	public List<Authority> authorities;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public void addAuthority(Authority authority){
		this.authorities.add(authority);
	}
	public void deleteAuthorityByAuthorityId(long authorityId){
		for(int i = this.authorities.size() - 1; i >= 0; i--){
            Authority item = this.authorities.get(i);
            if(authorityId==item.getId()){
            	this.authorities.remove(item);
            }
        }
	}
}
