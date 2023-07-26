package com.robsil.erommerce;

import com.robsil.erommerce.data.domain.Cart;
import com.robsil.erommerce.data.domain.User;
import com.robsil.erommerce.data.repository.CartRepository;
import com.robsil.erommerce.model.ERole;
import com.robsil.erommerce.model.Gender;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CartService;
import com.robsil.erommerce.service.impl.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    public final Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    public final CartRepository repository = mock(CartRepository.class);
    public final CartService underTest = new CartServiceImpl(repository);

    public final ArgumentCaptor<Cart> captor = ArgumentCaptor.forClass(Cart.class);

    @Test
    void findById() {
        //given
        var user = new User("rob",
                "sil",
                LocalDateTime.now(fixedClock),
                Gender.MALE,
                "robsil@gmail.com",
                true,
                "passw",
                true,
                List.of(ERole.USER));

        var cart = new Cart(user);
        Long id = 1L;
        cart.setId(id);

        //when
        when(repository.findById(id)).thenReturn(Optional.of(cart));
        var returned = underTest.findById(id);

        //then
        assertEquals(cart, returned);

    }

    @Test
    void findByIdNotFound() {
        //given
        Long id = 1L;

        //when
        when(repository.findById(1L)).thenReturn(Optional.empty());

        //then
        var e = assertThrows(EntityNotFoundException.class, () -> underTest.findById(id));
        assertEquals("Cart not found.", e.getMessage());
    }

    @Test
    void findByUserId() {
        //given
        var user = new User("rob",
                "sil",
                LocalDateTime.now(fixedClock),
                Gender.MALE,
                "robsil@gmail.com",
                true,
                "passw",
                true,
                List.of(ERole.USER));
        Long userId = 2L;
        user.setId(userId);

        var cart = new Cart(user);
        Long cartId = 1L;
        cart.setId(cartId);

        //when
        when(repository.findByUserId(userId)).thenReturn(Optional.of(cart));
        var returned = underTest.findByUserId(user);

        //then
        assertEquals(cart, returned);
    }

    @Test
    void findByUserIdCreateDefaultUser() {
        //given
        var user = new User("rob",
                "sil",
                LocalDateTime.now(fixedClock),
                Gender.MALE,
                "robsil@gmail.com",
                true,
                "passw",
                true,
                List.of(ERole.USER));
        Long userId = 2L;
        user.setId(userId);

        var toSaveCart = new Cart(user);
        Long cartId = 1L;

        var savedCart = new Cart(user);
        savedCart.setId(cartId);
        //when
        when(repository.save(captor.capture())).thenReturn(savedCart);
        when(repository.findByUserId(userId)).thenReturn(Optional.empty());

        var returned = underTest.findByUserId(user);

        //then
        assertEquals(savedCart, returned);
        assertEquals(toSaveCart, captor.getValue());
    }

}
