package com.robsil.erommerce;

import com.robsil.erommerce.data.domain.*;
import com.robsil.erommerce.data.repository.CartItemRepository;
import com.robsil.erommerce.model.ERole;
import com.robsil.erommerce.model.Gender;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CartItemService;
import com.robsil.erommerce.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// todo:
// test with embedded postgresql method with pageable fetching, and deleting.
public class CartItemServiceTest {

    public final Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    public final CartItemRepository repository = mock(CartItemRepository.class);
    public final CartItemService underTest = new CartItemServiceImpl(repository);

    public ArgumentCaptor<CartItem> captor = ArgumentCaptor.forClass(CartItem.class);

    @Test
    public void findById() {
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

        var category = new Category(null, "c1");
        var product = new Product(category,
                "p1",
                null,
                null,
                null,
                null,
                null,
                false,
                null);

        var cartItem = new CartItem(cart, product, new BigDecimal(1), new BigDecimal(1));
        Long cartItemId = 3L;
        //when

        when(repository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
        var returned = underTest.findById(cartItemId);

        //then
        assertEquals(cartItem, returned);
    }

    @Test
    public void findByIdNotFound() {
        //given
        var id = 1L;

        //when
        when(repository.findById(id)).thenReturn(Optional.empty());

        //then
        var e = assertThrows(EntityNotFoundException.class, () -> underTest.findById(id));
        assertEquals("CartItem not found.", e.getMessage());
    }

    @Test
    public void findAllByCartId() {
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

        var category = new Category(null, "c1");
        var product = new Product(category,
                "p1",
                null,
                null,
                null,
                null,
                null,
                false,
                null);

        var cartItem1 = new CartItem(cart, product, new BigDecimal(1), new BigDecimal(1));
        cartItem1.setId(11L);
        var cartItem2 = new CartItem(cart, product, new BigDecimal(2), new BigDecimal(2));
        cartItem2.setId(12L);
        var cartItem3 = new CartItem(cart, product, new BigDecimal(3), new BigDecimal(3));
        cartItem3.setId(13L);
        var cartItem4 = new CartItem(cart, product, new BigDecimal(4), new BigDecimal(4));
        cartItem4.setId(14L);

        var expected = List.of(cartItem1, cartItem2, cartItem3, cartItem4);
        //when
        when(repository.findAllByCartId(cart.getId())).thenReturn(expected);
        var returned = underTest.findAllByCartId(cart.getId());

        //then
        assertEquals(expected, returned);
    }

    @Test
    public void create() {
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

        var category = new Category(null, "c1");
        var product = new Product(category,
                "p1",
                null,
                new BigDecimal(7),
                null,
                null,
                null,
                false,
                null);

        var cartItem = new CartItem(cart, product, new BigDecimal(3), product.getPrice().multiply(new BigDecimal(3)));
        var savedCi = new CartItem(cart, product, new BigDecimal(3), product.getPrice().multiply(new BigDecimal(3)));
        Long cartItemId = 3L;
        savedCi.setId(cartItemId);

        //when
        when(repository.save(captor.capture())).thenReturn(savedCi);
        var returned = underTest.create(cart, product, new BigDecimal(3));

        //then
        assertEquals(cartItem, captor.getValue());
        assertEquals(savedCi, returned);
    }

    @Test
    public void changeQuantity() {
        //given
        var cartItemId = 2L;
        var newQuantity = new BigDecimal(5);

        //when
        underTest.changeQuantity(cartItemId, newQuantity);

        //then
        verify(repository, times(1)).changeQuantity(cartItemId, newQuantity);
    }

    @Test
    public void deleteById() {
        //given
        var cartItemId = 2L;

        //when
        underTest.deleteById(cartItemId);

        //then
        verify(repository, times(1)).deleteById(cartItemId);
    }

    @Test
    public void deleteAllByCartId() {
        //given
        var cartId = 2L;

        //when
        underTest.deleteAllByCartId(cartId);

        //then
        verify(repository, times(1)).deleteAllByCartId(cartId);
    }

}
