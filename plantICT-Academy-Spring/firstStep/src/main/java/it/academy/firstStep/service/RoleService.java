package it.academy.firstStep.service;

import it.academy.firstStep.dto.RoleDto;
import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.entities.UserRoleEntity;
import it.academy.firstStep.exceptions.DuplicatedRoleNameException;
import it.academy.firstStep.exceptions.DuplicatedUserRoleException;
import it.academy.firstStep.exceptions.DuplicatedUsernameException;
import it.academy.firstStep.exceptions.RoleNotFoundException;
import it.academy.firstStep.mapper.RoleMapper;
import it.academy.firstStep.repository.RoleRepository;
import it.academy.firstStep.requestObjects.RoleRequestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);



    private boolean checkNameAvailable(List<RoleEntity> roles, String name) {
        for (RoleEntity role : roles) {
            if (role.getName().equals(name)) {
                logger.debug("Name '{}' is not available", name);
                return false;
            }
        }
        logger.debug("Name '{}' is available", name);
        return true;
    }

    public RoleEntity createRole(RoleRequestObject roleData) {
        logger.info("Creating role with name: {}", roleData.getRoleName());
        Optional<RoleEntity> duplicatedUserRole = roleRepository.findRoleEntity(roleData.getRoleName());
        if (duplicatedUserRole.isPresent()) {
            logger.warn("Attempted to create a role with duplicate name: {}", roleData.getRoleName());
            throw new DuplicatedRoleNameException("Username "
                    + roleData.getRoleName() + " already exists");
        }

        RoleEntity role = new RoleEntity();
        role.setName(roleData.getRoleName());
        role.setDescription(roleData.getRoleDescription());

        RoleEntity saved = roleRepository.save(role);
        logger.info("Role created with ID: {}", saved.getId());
        return saved;
    }

    public RoleEntity updateRole(Integer id, RoleDto roleDto) {
        logger.info("Updating role with id: {}", id);
        if (!roleRepository.existsById(id)) {
            logger.warn("Attempted to update a non existent role: {}", id);
            throw new RoleNotFoundException("Role with id: " + id + " not found");
        }

        RoleEntity role = roleMapper.toRoleEntity(roleDto);
        role.setId(id);
        RoleEntity updated = roleRepository.save(role);
        logger.info("Role with id {} updated successfully", id);
        return updated;
    }

    public List<RoleEntity> getAllRoles() {
        logger.debug("Fetching all roles");
        Iterable<RoleEntity> roles = roleRepository.findAll();
        List<RoleEntity> list = new ArrayList<>();
        roles.forEach(list::add);
        logger.info("Found {} roles", list.size());
        return list;
    }

    public RoleEntity getRoleById(Integer id) {
        logger.debug("Fetching role with id: {}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> {
                    return new RoleNotFoundException("Role with id: " + id + " not found");
                });
    }

    public void deleteRoleById(Integer id) {
        logger.info("Deleting role with id: {}", id);
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("Role with id: " + id + " not found");
        }
        roleRepository.deleteById(id);
        logger.info("Role with id {} deleted successfully", id);
    }
}
