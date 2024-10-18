import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

//@NoArgsConstructor
@RequiredArgsConstructor
public class ShopService {
    @NonNull

    private final ProductRepo productRepo; // = new ProductRepo();
    private final OrderRepo orderRepo; // = new OrderMapRepo();
    private final IdServive idServive;

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new NoSuchElementException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get());
        }
        ZonedDateTime timestamp = ZonedDateTime.now();
        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING, timestamp);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrderByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(name -> name.status() == status)
                //.peek(name -> System.out.println("Filtered: " + name))
                .collect(Collectors.toList());
    }

    public Optional<Order> updateOrder(String orderId, OrderStatus status) {
        for (Order order : orderRepo.getOrders()) {
            if (order.id().equals(orderId)) {
                Order newOrder = order.withStatus(status);
                orderRepo.removeOrder(orderId);
                orderRepo.addOrder(newOrder);
                return Optional.of(newOrder);
            }
        }

        return Optional.empty();
    }

    public Optional<HashMap<OrderStatus,Order>> getOldestOrderPerStatus(){
        HashMap<OrderStatus,Order> orders = new HashMap<>();
        for(OrderStatus orderStatus : OrderStatus.values()){
            List<Order> sortedList = getOrderByStatus(orderStatus).stream()
                    .sorted(Comparator.comparing(Order::timestamp))
                    .filter(order -> order.status() == orderStatus)
                    .toList();
            if(!sortedList.isEmpty()){
                Order olderstOrder = sortedList.get(0);
                orders.put(orderStatus,olderstOrder);
            }
        }
        return Optional.of(orders);
    }
}
