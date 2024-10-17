import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {


        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2","Pfirsich"));
        productRepo.addProduct(new Product("3","Banane"));
        productRepo.addProduct(new Product("4","Pflaume"));

        Order order1 = new Order("1",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("2",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order3 = new Order("3",productRepo.getProducts(),OrderStatus.PROCESSING, ZonedDateTime.now());

        OrderRepo orderRepo = new OrderMapRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);

        IdServive idService = new IdServive();

        ShopService shopService = new ShopService(productRepo,orderRepo,idService);
        shopService.getOrderByStatus(OrderStatus.PROCESSING);




    }
}
