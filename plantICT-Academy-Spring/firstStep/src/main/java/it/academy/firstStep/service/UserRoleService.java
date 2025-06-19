package it.academy.firstStep.service;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.dto.UserRoleDto;
import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.entities.UserRoleEntity;
import it.academy.firstStep.exceptions.DuplicatedUserRoleException;
import it.academy.firstStep.exceptions.RoleNotFoundException;
import it.academy.firstStep.exceptions.UserNotFoundException;
import it.academy.firstStep.exceptions.UserRoleNotFoundException;
import it.academy.firstStep.mapper.UserRoleMapper;
import it.academy.firstStep.repository.UserRoleRepository;
import it.academy.firstStep.repository.UserRepository;
import it.academy.firstStep.repository.RoleRepository;

import it.academy.firstStep.requestObjects.UserRequestObject;
import it.academy.firstStep.rolesEnum.Roles;
import jakarta.persistence.NamedQuery;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    public UserRoleEntity createUserRole(int userId, Roles role) {
        logger.info("Creating UserRole for userId={} and roleId={}", userId, role.detectId());

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new UserNotFoundException("User with id: " + userId + " not found");
                });

        RoleEntity roleEntity = roleRepository.findById(role.detectId())
                .orElseThrow(() -> {
                    return new RoleNotFoundException("Role with id: " + role.detectId() + " not found");
                });

        Optional<UserRoleEntity> duplicateUserRole = userRoleRepository.findUserRoleEntity(userId, role.detectId());
        if (duplicateUserRole.isPresent()) {
            logger.warn("Attempted to give to User(id {}) a role that already has(id {})", userId, role.detectId());
            throw new DuplicatedUserRoleException("User with id: " + userId + " already has the role with id: " + role.detectId());
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setRole(roleEntity);

        UserRoleEntity saved = userRoleRepository.save(userRoleEntity);
        logger.info("UserRole created with id {}", saved.getId());
        return saved;
    }

    public UserRoleEntity updateUserRole(Integer id, UserRoleDto userRoleDto) {
        logger.info("Updating UserRole with id {}", id);
        if (!userRoleRepository.existsById(id)) {
            logger.warn("Attempted to update a non existent UserRole with id {}", id);
            throw new UserRoleNotFoundException("UserRole with id: " + id + " not found");
        }
        UserRoleEntity userRole = userRoleMapper.toUserRoleEntity(userRoleDto);
        userRole.setId(id);
        UserRoleEntity updated = userRoleRepository.save(userRole);
        logger.info("UserRole with id {} updated successfully", id);
        return updated;
    }

    public List<UserRoleEntity> getAllUserRoles() {
        logger.debug("Fetching all user roles");
        Iterable<UserRoleEntity> userRoles = userRoleRepository.findAll();
        List<UserRoleEntity> list = new ArrayList<>();
        userRoles.forEach(list::add);
        logger.info("Found {} user roles", list.size());
        return list;
    }

    public UserRoleEntity getUserRoleById(Integer id) {
        logger.debug("Fetching user role with id {}", id);
        return userRoleRepository.findById(id)
                .orElseThrow(() -> {
                    return new UserRoleNotFoundException("UserRole with id: " + id + " not found");
                });
    }

    public void deleteUserRoleById(Integer id) {
        logger.info("Deleting user role with id {}", id);
        if (!userRoleRepository.existsById(id)) {
            logger.warn("Attempted to delete, user role with id {} not found", id);
            throw new UserRoleNotFoundException("UserRole with id: " + id + " not found");
        }
        userRoleRepository.deleteById(id);
        logger.info("User role with id {} deleted successfully", id);
    }

}