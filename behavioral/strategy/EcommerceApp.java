package behavioral.strategy;

import java.util.Objects;

class Order {
    private int weight;
    private String destZone;

    public Order(int weight, String destZone) {
        this.weight = weight;
        this.destZone = destZone;
    }

    public int getWeight() {
        return weight;
    }

    public String getDestinationZone() {
        return destZone;
    }
}

interface ShippingStrategy {

    double calculateCost(Order order );

}

class FlatRateStrategy implements ShippingStrategy{
    private double rate;

    public FlatRateStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateCost(Order order) {
        return rate;
    }
}

class WeightBasedStrategy implements ShippingStrategy {
    private double ratePerUnitWt;

    public WeightBasedStrategy(double ratePerUnitWt) {
        this.ratePerUnitWt = ratePerUnitWt;
    }

    @Override
    public double calculateCost(Order order) {
        if(Objects.isNull(order) || Objects.isNull(order.getWeight())) {
            throw new IllegalArgumentException("Invalid objects");
        }

        return ratePerUnitWt * order.getWeight();
    }
}

class DistanceBasedStrategy implements ShippingStrategy {
    private double ratePerKm;

    public DistanceBasedStrategy(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    @Override
    public double calculateCost(Order order) {
        System.out.println("Calculating with Distance-Based strategy for zone: " + order.getDestinationZone());
        return switch (order.getDestinationZone()) {
            case "ZoneA" -> ratePerKm * 5.0;
            case "ZoneB" -> ratePerKm * 7.0;
            default -> ratePerKm * 10.0;
        };
    }
}


class ShippingManagementService {
    private ShippingStrategy shippingStrategy;

    public ShippingManagementService(ShippingStrategy shippingStrategy){
        this.shippingStrategy = shippingStrategy;
    }

    public void setShippingStrategy(ShippingStrategy shippingStrategy){
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateShippingCost(Order order) {
        if(Objects.isNull(shippingStrategy)) {
            throw new IllegalStateException("Shipping strategy is not set.");
        }
        double cost = shippingStrategy.calculateCost(order);
        String formattedCost = String.format("%.2f", cost);
        System.out.println("ShippingManagementService:  final Calculated shipping cost: " + formattedCost);
        return cost;
    }
}


public class EcommerceApp {
    public static void main(String[] args) {
        Order order1 = new Order(10, "ZoneB");
        Order order2 = new Order(15, "ZoneC");

        ShippingStrategy flatStrategy = new FlatRateStrategy(50);
        ShippingStrategy weightBasedStrategy = new WeightBasedStrategy(10.0 );
        ShippingStrategy distStrategy = new DistanceBasedStrategy(10.0 );

        ShippingManagementService shippingManagementService = new ShippingManagementService(flatStrategy);
        double cost = shippingManagementService.calculateShippingCost(order2);

        shippingManagementService.setShippingStrategy(distStrategy);
        cost = shippingManagementService.calculateShippingCost(order1);
        cost = shippingManagementService.calculateShippingCost(order2);

        shippingManagementService.setShippingStrategy(weightBasedStrategy);
        cost = shippingManagementService.calculateShippingCost(order2);
    }
}
