import java.time.ZonedDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2","Pfirsich"));
        productRepo.addProduct(new Product("3","Banane"));
        productRepo.addProduct(new Product("4","Pflaume"));

        Order order1 = new Order("1",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("2",productRepo.getProducts(),OrderStatus.IN_DELIVERY, ZonedDateTime.now());
        Order order3 = new Order("3",productRepo.getProducts(),OrderStatus.COMPLETED, ZonedDateTime.now());
        Order order4 = new Order("4",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order5 = new Order("5",productRepo.getProducts(),OrderStatus.IN_DELIVERY, ZonedDateTime.now());
        Order order6 = new Order("6",productRepo.getProducts(),OrderStatus.COMPLETED, ZonedDateTime.now());

        OrderRepo orderRepo = new OrderMapRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);
        orderRepo.addOrder(order4);
        orderRepo.addOrder(order5);
        orderRepo.addOrder(order6);


        IdServive idService = new IdServive();

        ShopService shopService = new ShopService(productRepo,orderRepo,idService);


        HashMap oldesOrdersPerStatus = shopService.getOldestOrderPerStatus().get();
        System.out.println(oldesOrdersPerStatus.get(OrderStatus.PROCESSING));





    }
}
