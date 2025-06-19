package it.academy.firstStep.service;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.exceptions.DuplicatedUsernameException;
import it.academy.firstStep.exceptions.UserNotFoundException;
import it.academy.firstStep.mapper.UserMapper;
import it.academy.firstStep.repository.UserRepository;
import it.academy.firstStep.repository.UserRoleRepository;
import it.academy.firstStep.requestObjects.UserRequestObject;
import it.academy.firstStep.rolesEnum.Roles;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private boolean checkNameAvailable(List<UserEntity> users, String userName) {
        logger.debug("Checking if username '{}' is available", userName);
        for (UserEntity user : users) {
            if (user.getUsername().equals(userName)) {
                logger.warn("Username '{}' is already taken", userName);
                return false;
            }
        }
        logger.debug("Username '{}' is available", userName);
        return true;
    }

    public UserEntity createUser(UserRequestObject userData, Roles role) {
        logger.info("Attempting to create user with username '{}'", userData.getUsername());
        if (!checkNameAvailable(getAllUsers(), userData.getUsername())) {
            logger.warn("Attempted to create a user with username '{}' that already exists", userData.getUsername());
            throw new DuplicatedUsernameException("Username " + userData.getUsername() + " already exists");
        }

        UserEntity user = userMapper.requestToUserEntity(userData);
        user = userRepository.save(user);
        logger.info("User created with id {}", user.getId());

        userRoleService.createUserRole(user.getId(), role);
        logger.info("Role '{}' assigned to user with id {}", role, user.getId());

        return user;
    }

    public UserEntity updateUser(Integer id, UserDto userDto) {
        logger.info("Updating user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        UserEntity updatedUser = userMapper.toUserEntity(userDto);
        updatedUser.setId(id);
        updatedUser = userRepository.save(updatedUser);
        logger.info("User with id {} updated successfully", id);
        return updatedUser;
    }

    public List<UserEntity> getAllUsers() {
        logger.debug("Fetching all users");
        Iterable<UserEntity> users = userRepository.findAll();
        List<UserEntity> list = new ArrayList<>();
        users.forEach(list::add);
        logger.info("Fetched {} users", list.size());
        return list;
    }

    public UserEntity getUserById(Integer id) {
        logger.debug("Fetching user with id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    return new UserNotFoundException("User with id: " + id + " not found");
                });
    }

    public void deleteUserById(Integer id) {
        logger.info("Deleting user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        userRepository.deleteById(id);
        logger.info("User with id {} deleted successfully", id);
    }


}
