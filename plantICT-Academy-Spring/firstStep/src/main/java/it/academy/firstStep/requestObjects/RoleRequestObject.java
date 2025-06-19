package it.academy.firstStep.requestObjects;

public class RoleRequestObject {
    private String roleName;
    private String roleDescription;

    RoleRequestObject(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
