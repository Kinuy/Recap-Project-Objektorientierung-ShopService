import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    ProductRepo productRepo = new ProductRepo();
    OrderRepo orderRepo = new OrderMapRepo();
    IdServive idServive = new IdServive();



    void createDummyProductAndOrderRepos(){
        productRepo.addProduct(new Product("2","Pfirsich"));
        productRepo.addProduct(new Product("3","Banane"));
        productRepo.addProduct(new Product("4","Pflaume"));

        Order order1 = new Order("1",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("2",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order3 = new Order("3",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());


        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);
    }


    @Test
    void addOrderTest() {
        //GIVEN
        this.createDummyProductAndOrderRepos();
        ShopService shopService = new ShopService(productRepo, orderRepo,idServive);
        List<String> productsIds = List.of("1");
        ZonedDateTime date = ZonedDateTime.now();
        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, date);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService(productRepo, orderRepo,idServive);
        List<String> productsIds = List.of("1", "2");

        //WHEN & THEN
        //assertNull(actual);
//        assertThrows(RuntimeException.class, () -> shopService.addOrder(productsIds));
        assertThrows(NoSuchElementException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void updateOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService(productRepo, orderRepo,idServive);
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);

        //WHEN
        Optional<Order> actual = shopService.updateOrder(order.id(),OrderStatus.IN_DELIVERY);

        //THEN
        assertTrue(actual.get().status().equals(OrderStatus.IN_DELIVERY));

    }
}
