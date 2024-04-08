package org.fve.springbootprojects.springbootstarterapp.modules.auth.service;

import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.NotFoundException;
import org.fve.springbootprojects.springbootstarterapp.modules.internationalization.service.MessageSourceService;
import org.fve.springbootprojects.springbootstarterapp.security.models.Role;
import org.fve.springbootprojects.springbootstarterapp.security.repositories.RoleRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.function.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests for RoleService")
class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MessageSourceService messageSourceService;

    private final Role role = Instancio.create(Role.class);

    @Nested
    @DisplayName("Test class for count scenarios")
    class CountTest {
        @Test
        @DisplayName("Happy path")
        void given_whenCount_thenAssertBody() {
            // Given
            when(roleRepository.count()).thenReturn(1L);
            // When
            Long count = roleService.count();
            // Then
            assertEquals(1L, count);
        }
    }

    @Nested
    @DisplayName("Test class for findByName scenarios")
    class FindAllByNameTest {
//        @Test
//        @DisplayName("Happy path")
//        void given_whenFindByName_thenAssertBody() {
//            // Given
//            when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));
//            // When
//            Role result = roleService.findByName(role.getName());
//            // Then
//            assertNotNull(result);
//            assertEquals(role.getId(), result.getId());
//            assertEquals(role.getName(), result.getName());
//        }

//        @Test
//        @DisplayName("Not found role path")
//        void given_whenFindByName_thenThrowNotFoundException() {
//            // Given
//            when(roleRepository.findByName(role.getName())).thenReturn(Optional.empty());
//            // When
//            Executable executable = () -> roleService.findByName(role.getName());
//            // Then
//            NotFoundException notFoundException = assertThrows(NotFoundException.class, executable);
//            assertEquals(messageSourceService.get("role_not_found"), notFoundException.getMessage());
//        }
    }

    @Nested
    @DisplayName("Test class for create scenarios")
    class CreateTest {
        @Test
        @DisplayName("Happy path")
        void given_whenCreate_thenAssertBody() {
            // Given
            when(roleRepository.save(role)).thenReturn(role);
            // When
            Role result = roleService.create(role);
            // Then
            assertNotNull(result);
            assertEquals(role.getId(), result.getId());
            assertEquals(role.getName(), result.getName());
        }
    }

    @Nested
    @DisplayName("Test class for save list scenarios")
    class SaveListTest {
        @Test
        @DisplayName("Happy path")
        void given_whenSaveList_thenAssertBody() {
            // Given
            List<Role> roles = List.of(role);
            when(roleRepository.saveAll(roles)).thenReturn(roles);
            // When
            List<Role> result = roleService.saveList(List.of(role));
            // Then
            assertNotNull(result);
            assertEquals(role.getId(), result.get(0).getId());
            assertEquals(role.getName(), result.get(0).getName());
        }
    }

}